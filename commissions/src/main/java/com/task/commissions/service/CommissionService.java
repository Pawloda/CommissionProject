package com.task.commissions.service;

import com.task.commissions.mapper.TransactionMapper;
import com.task.commissions.repository.CommissionRepository;
import com.task.commissions.service.strategy.StrategyChanger;
import com.task.commissions.type.CommissionTO;
import com.task.commissions.type.TransactionTO;
import org.springframework.stereotype.Service;

import java.io.IOException;

import java.util.*;

@Service
public class CommissionService {

    private final CommissionRepository commissionRepository;
    private final StrategyChanger strategyChanger;

    public CommissionService(CommissionRepository commissionRepository, StrategyChanger strategyChanger) {
        this.commissionRepository = commissionRepository;
        this.strategyChanger = strategyChanger;
    }

    /**
     * Method takes customers' ids, creates list of transactions for given ids and calculate commissions.
     *
     * @param customerIds given value.
     * @return list of commissions.
     */
    public List<CommissionTO> findCommissionByCustomerId(String customerIds) {
        List<String> ids = createIds(customerIds);
        List<TransactionTO> allTransactions = TransactionMapper.map2TOs(commissionRepository.findAll());
        List<TransactionTO> transactions = strategyChanger.getTransactions(allTransactions, ids);
        return getCommissions(transactions);
    }

    /**
     * Method tokenizes string for a list of strings.
     *
     * @param ids given ids in string.
     * @return list of strings.
     */
    private List<String> createIds(String ids) {
        StringTokenizer tokenizer = new StringTokenizer(ids, ",");
        List<String> results = new ArrayList<>();
        while(tokenizer.hasMoreTokens()) {
            results.add(tokenizer.nextToken());
        }
        return results;
    }

    /**
     * Method creates commissions.
     *
     * @param allTransactions given values.
     * @return list of commissions.
     */
    private List<CommissionTO> getCommissions(List<TransactionTO> allTransactions) {
        allTransactions.sort(Comparator.comparing(TransactionTO::getCustomerId)
                .thenComparing(TransactionTO::getTransactionDate));
        List<List<TransactionTO>> sorted = sortTransactionsByCustomer(allTransactions);
        List<CommissionTO> results = new ArrayList<>();
        for(List<TransactionTO> transactions : sorted) {
            CommissionTO commission = CommissionTO.builder()
                    .customerFirstName(transactions.get(0).getCustomerFirstName())
                    .customerLastName(transactions.get(0).getCustomerLastName())
                    .customerId(transactions.get(0).getCustomerId())
                    .numberOfTransactions((long) transactions.size())
                    .lastTransactionDate(transactions.get(0).getTransactionDate()).build();
            double sum = 0d;
            for(TransactionTO transaction : transactions) {
                sum = sum + transaction.getTransactionAmount();
            }
            commission.setValueOfTransactions(sum);
            commission.setFeeValue(countFeeValue(sum));
            results.add(commission);
        }
        return results;
    }

    /**
     * Method creates sorted lists of transactions.
     *
     * @param transactions given values.
     * @return list of commissions.
     */
    private List<List<TransactionTO>> sortTransactionsByCustomer(List<TransactionTO> transactions) {
        List<List<TransactionTO>> results = new ArrayList<>();
        TransactionTO previous = TransactionTO.builder().customerId(0L).build();
        for(TransactionTO current : transactions) {
            if(!previous.getCustomerId().equals(current.getCustomerId())) {
                List<TransactionTO> list = new ArrayList<>();
                results.add(list);
            }
            results.get(results.size() - 1).add(current);
            previous = current;
        }
        return results;
    }

    /**
     * Method creates fee value from given sum of transactions.
     *
     * @param sum given values.
     * @return fee value.
     */
    private double countFeeValue(double sum) {
        String address = "fee_wages.csv";
        List<String> rows = new ArrayList<>();
        try {
            rows = commissionRepository.takeRows(address);
        } catch(IOException exception) {
            System.out.println(exception);
        }
        Map<Long, List<Double>> map = createFeeMap(rows);
        double result = 0;
        for(int i = map.size(); i > 0; i--) {
            if(sum < map.get(Long.parseLong(String.valueOf(i))).get(0)) {
                result = map.get(Long.parseLong(String.valueOf(i))).get(1);
            }
        }
        return result;
    }

    /**
     * Method creates amp with fee values.
     *
     * @param rows given values.
     * @return fee value map.
     */
    private Map<Long, List<Double>> createFeeMap(List<String> rows) {
        Map<Long, List<Double>> map = new HashMap<>();
        Long i = 1L;
        for(String row : rows) {
            StringTokenizer tokenizer = new StringTokenizer(row, ",");
            List<Double> list = new ArrayList<>();
            String value = tokenizer.nextToken();
            String fee = tokenizer.nextToken() + "." + tokenizer.nextToken();
            list.add(Double.parseDouble(value));
            list.add(Double.parseDouble(fee.substring(1, 4)));
            map.put(i, list);
            i++;
        }
        return map;
    }

}
