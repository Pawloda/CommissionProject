package com.task.commissions.repository;

import com.task.commissions.domain.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Repository
public class CommissionRepository {

    /**
     * It takes all data from given file.
     *
     * @return list of transactions.
     */
    public List<TransactionEntity> findAll() {
        String address = "transactions.csv";
        List<String> rows = new ArrayList<>();
        try {
            rows = takeRows(address);
        } catch(IOException exception) {
            System.out.println(exception);
        }
        return createTransactions(rows);
    }

    /**
     * It takes every row from file and put it into list of strings.
     *
     * @param address of the file from which data are taken.
     * @return list of strings with data.
     * @throws IOException if there is problem with the file.
     */
    public List<String> takeRows(String address) throws IOException {
        FileInputStream fis = new FileInputStream(address);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        List<String> result = new ArrayList<>();
        long count = countLines(address);
        for(long i = 0; i < count; i++) {
            String line = br.readLine();
            result.add(line);
        }
        result.remove(0);
        return result;
    }

    /**
     * Count lines for document.
     *
     * @param address of the file from which data are taken.
     * @return number of rows.
     * @throws IOException if there is problem with the file.
     */
    private long countLines(String address) throws IOException {
        FileInputStream fis = new FileInputStream(address);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        long result = br.lines().count();
        br.close();
        isr.close();
        fis.close();
        return result;
    }

    /**
     * It creates the list of transactions from the list of strings with values in string.
     *
     * @param rows every row contain data to single object of transaction.
     * @return list of transactions.
     */
    private List<TransactionEntity> createTransactions(List<String> rows) {
        List<TransactionEntity> result = new ArrayList<>();
        for(String row : rows) {
            StringTokenizer tokenizer = new StringTokenizer(row, ",");
            TransactionEntity transaction = TransactionEntity.builder().build();
            while(tokenizer.hasMoreTokens()) {
                transaction.setTransactionId(Long.parseLong(tokenizer.nextToken()));
                transaction.setTransactionAmount(Double.parseDouble(tokenizer.nextToken().substring(1)
                        + "." + tokenizer.nextToken().substring(0, 2)));
                transaction.setCustomerFirstName(tokenizer.nextToken());
                transaction.setCustomerId(Long.parseLong(tokenizer.nextToken()));
                transaction.setCustomerLastName(tokenizer.nextToken());
                transaction.setTransactionDate(createDate(tokenizer.nextToken()));
            }
            result.add(transaction);
        }
        return result;
    }

    /**
     * It takes from string numbers put it into local data time via tokenizer.
     *
     * @param date input in string.
     * @return local data time formatted date.
     */
    private LocalDateTime createDate(String date) {
        StringTokenizer tokenizer = new StringTokenizer(date, ". :");
        int day = 0;
        int month = 0;
        int year = 0;
        int hour = 0;
        int minute = 0;
        int second = 0;
        while(tokenizer.hasMoreTokens()) {
            day = Integer.parseInt(tokenizer.nextToken());
            month = Integer.parseInt(tokenizer.nextToken());
            year = Integer.parseInt(tokenizer.nextToken());
            hour = Integer.parseInt(tokenizer.nextToken());
            minute = Integer.parseInt(tokenizer.nextToken());
            second = Integer.parseInt(tokenizer.nextToken());
        }
        return LocalDateTime.of(year, month, day, hour, minute, second);
    }

}
