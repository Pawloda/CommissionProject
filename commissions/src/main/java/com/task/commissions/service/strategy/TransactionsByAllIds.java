package com.task.commissions.service.strategy;

import com.task.commissions.type.TransactionTO;

import java.util.List;

public class TransactionsByAllIds implements TransactionsById {

    public List<TransactionTO> getTransactions(List<TransactionTO> allTransactions, List<String> customerId) {
        return allTransactions;
    }

}
