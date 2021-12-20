package com.task.commissions.service.strategy;

import com.task.commissions.type.TransactionTO;

import java.util.List;

public interface TransactionsById {

    /**
     * It takes list of ids and creates list of transactions for this ids.
     *
     * @param allTransactions from excel file.
     * @param customerId      given ids.
     * @return list of transactions.
     */
    List<TransactionTO> getTransactions(List<TransactionTO> allTransactions, List<String> customerId);

}
