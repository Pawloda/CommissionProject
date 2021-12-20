package com.task.commissions.service.strategy;

import com.task.commissions.exception.BusinessException;
import com.task.commissions.type.TransactionTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class StrategyChangerTest {

    @Autowired
    StrategyChanger strategyChanger;

    TransactionTO t1 = TransactionTO.builder().transactionId(1L).transactionAmount(1.2)
            .customerFirstName("Paul").customerId(1L).customerLastName("Jackson").transactionDate(LocalDateTime.now()).build();
    TransactionTO t2 = TransactionTO.builder().transactionId(2L).transactionAmount(3.2)
            .customerFirstName("Paul").customerId(1L).customerLastName("Jackson").transactionDate(LocalDateTime.now()).build();
    TransactionTO t3 = TransactionTO.builder().transactionId(3L).transactionAmount(5.2)
            .customerFirstName("Mark").customerId(2L).customerLastName("Smith").transactionDate(LocalDateTime.now()).build();
    TransactionTO t4 = TransactionTO.builder().transactionId(4L).transactionAmount(7.2)
            .customerFirstName("Mark").customerId(2L).customerLastName("Smith").transactionDate(LocalDateTime.now()).build();
    TransactionTO t5 = TransactionTO.builder().transactionId(5L).transactionAmount(9.2)
            .customerFirstName("Morgan").customerId(3L).customerLastName("Jackson").transactionDate(LocalDateTime.now()).build();
    List<TransactionTO> transactions = List.of(t1, t2, t3, t4, t5);

    @Test
    public void shouldReturn2_ForGetTransactions_WhenIdIs1() {
        //given
        //when
        List<TransactionTO> result = strategyChanger.getTransactions(transactions, List.of("1"));
        //then
        assertEquals(2, result.size());
    }

    @Test
    public void shouldReturn1_ForGetTransactions_WhenIdIs3() {
        //given
        //when
        List<TransactionTO> result = strategyChanger.getTransactions(transactions, List.of("3"));
        //then
        assertEquals(1, result.size());
    }

    @Test
    public void shouldReturn3_ForGetTransactions_WhenIdsAre1And3() {
        //given
        //when
        List<TransactionTO> result = strategyChanger.getTransactions(transactions, List.of("1", "3"));
        //then
        assertEquals(3, result.size());
    }

    @Test
    public void shouldReturn5_ForGetTransactions_WhenIdIsAll() {
        //given
        //when
        List<TransactionTO> result = strategyChanger.getTransactions(transactions, List.of("AlL"));
        //then
        assertEquals(5, result.size());
    }

    @Test
    public void shouldThrowException_ForGetTransactions_WhenIdsAreInvalid() {
        //given
        //when
        //then
        assertThrows(BusinessException.class, () -> strategyChanger.getTransactions(transactions, List.of("1sd", "1")));
    }

    @Test
    public void shouldThrowException_ForGetTransactions_WhenIdsAreEmpty() {
        //given
        List<String> empty = new ArrayList<>();
        //when
        //then
        assertThrows(BusinessException.class, () -> strategyChanger.getTransactions(transactions, empty));
    }
}
