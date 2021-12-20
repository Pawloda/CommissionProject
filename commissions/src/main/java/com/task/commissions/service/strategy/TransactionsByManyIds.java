package com.task.commissions.service.strategy;

import com.task.commissions.type.TransactionTO;

import java.util.ArrayList;
import java.util.List;

public class TransactionsByManyIds implements TransactionsById {

    public List<TransactionTO> getTransactions(List<TransactionTO> allTransactions, List<String> customerIds) {
        List<TransactionTO> result = new ArrayList<>();
        for(TransactionTO transaction : allTransactions) {
            if(customerIds.contains(transaction.getCustomerId().toString())) {
                result.add(transaction);
            }
        }
        return result;
    }

}
