package com.task.commissions.service.strategy;

import com.task.commissions.type.TransactionTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransactionsByOneId implements TransactionsById {

    public List<TransactionTO> getTransactions(List<TransactionTO> allTransactions, List<String> customerIds) {
        List<TransactionTO> result = new ArrayList<>();
        String id = customerIds.get(0);
        for(TransactionTO transaction : allTransactions) {
            if(Objects.equals(id, transaction.getCustomerId().toString())) {
                result.add(transaction);
            }
        }
        return result;
    }

}
