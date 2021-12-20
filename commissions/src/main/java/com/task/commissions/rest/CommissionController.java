package com.task.commissions.rest;

import com.task.commissions.service.CommissionService;
import com.task.commissions.type.CommissionTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CommissionController {

    private final CommissionService commissionService;

    public CommissionController(CommissionService commissionService) {
        this.commissionService = commissionService;
    }

    /**
     * It returns commission for given by id customer's.
     *
     * @param customerId given by user ids.
     * @return list of commissions with given customer.
     */
    @GetMapping("/id/")
    public List<CommissionTO> findCommissionByCustomerId(@RequestParam("id") String customerId) {
        return commissionService.findCommissionByCustomerId(customerId);
    }

}
