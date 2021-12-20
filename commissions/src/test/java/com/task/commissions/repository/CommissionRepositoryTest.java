package com.task.commissions.repository;

import com.task.commissions.domain.TransactionEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommissionRepositoryTest {

    @Autowired
    CommissionRepository commissionRepository;

    @Test
    public void shouldReturn53_ForFindAll() {
        //given
        //when
        List<TransactionEntity> result = commissionRepository.findAll();
        //then
        assertEquals(52, result.size());
    }

}
