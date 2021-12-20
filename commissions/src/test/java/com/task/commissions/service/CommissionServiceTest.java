package com.task.commissions.service;

import com.task.commissions.type.CommissionTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommissionServiceTest {

    @Autowired
    CommissionService commissionService;

    @Test
    public void shouldReturn1_forFindCommissionByCustomerId_WhenIdIs1() {
        //given
        //when
        List<CommissionTO> result = commissionService.findCommissionByCustomerId("1");
        //then
        assertEquals(1, result.size());
        assertEquals("Andrzej", result.get(0).getCustomerFirstName());
        assertEquals(1.1d, result.get(0).getFeeValue());
    }

    @Test
    public void shouldReturn2_forFindCommissionByCustomerId_WhenIdIs1And2() {
        //given
        //when
        List<CommissionTO> result = commissionService.findCommissionByCustomerId("1,2");
        //then
        assertEquals(2, result.size());
        assertEquals("Andrzej", result.get(0).getCustomerFirstName());
        assertEquals(1.1d, result.get(0).getFeeValue());
        assertEquals("Anna", result.get(1).getCustomerFirstName());
        assertEquals(0d, result.get(1).getFeeValue());
    }

    @Test
    public void shouldReturn5_forFindCommissionByCustomerId_WhenIdIsAll() {
        //given
        //when
        List<CommissionTO> result = commissionService.findCommissionByCustomerId("All");
        //then
        assertEquals(5, result.size());
        assertEquals("Andrzej", result.get(0).getCustomerFirstName());
        assertEquals(1.1d, result.get(0).getFeeValue());
        assertEquals("Anna", result.get(1).getCustomerFirstName());
        assertEquals(0d, result.get(1).getFeeValue());
        assertEquals("Micha?", result.get(2).getCustomerFirstName());
        assertEquals(3.5d, result.get(2).getFeeValue());
        assertEquals("Jakub", result.get(3).getCustomerFirstName());
        assertEquals(2.5d, result.get(3).getFeeValue());
        assertEquals("Jacek", result.get(4).getCustomerFirstName());
        assertEquals(1.1d, result.get(4).getFeeValue());
    }

}
