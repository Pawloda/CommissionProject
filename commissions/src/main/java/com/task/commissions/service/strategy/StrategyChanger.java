package com.task.commissions.service.strategy;

import com.task.commissions.type.TransactionTO;
import com.task.commissions.validation.StrategyValidator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Component
public class StrategyChanger {

    TransactionsById transaction;

    /**
     * It choose the proper strategy depends on put customers' ids.
     *
     * @param allTransactions all operations from the file given in entities.
     * @param customerIds values.
     * @return list of transactions for given ids.
     */
    public List<TransactionTO> getTransactions(List<TransactionTO> allTransactions, List<String> customerIds) {
        StrategyValidator.isValid(customerIds);
        if (Objects.equals("all", customerIds.get(0).toLowerCase(Locale.ROOT))) {
            transaction = new TransactionsByAllIds();
            return transaction.getTransactions(allTransactions, customerIds);
        } else if (customerIds.size() > 1) {
            transaction = new TransactionsByManyIds();
            return transaction.getTransactions(allTransactions, customerIds);
        } else {
            transaction = new TransactionsByOneId();
            return transaction.getTransactions(allTransactions, customerIds);
        }
    }

}
