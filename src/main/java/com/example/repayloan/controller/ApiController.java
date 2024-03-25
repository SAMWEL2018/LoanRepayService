package com.example.repayloan.controller;

import com.example.repayloan.model.ClientResponse;
import com.example.repayloan.model.RepayLoan;
import com.example.repayloan.service.RepayLoanServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 10:51
 * Project RepayLoan
 */
@Controller
public class ApiController {
    private final RepayLoanServiceImpl repayLoanService;
    public static final String REPAY_LOAN_SERVICE = "repayLoanService";
    private int attempt = 1;

    @Autowired
    public ApiController(RepayLoanServiceImpl repayLoanService) {
        this.repayLoanService = repayLoanService;
    }

    /**
     * ------------------------------------------------------------------------------------------
     * -------------------------------*** REPAY LOAN ENDPOINT ***-------------------------------------------
     * ------------------------------------------------------------------------------------------
     */

    @RequestMapping(value = "/repayLoanByCustomer", method = RequestMethod.POST)
    @Retry(name = REPAY_LOAN_SERVICE, fallbackMethod = "fallbackMethod")
    public ResponseEntity<?> repayLoan(@RequestBody RepayLoan repayLoan) throws JsonProcessingException {
        System.out.println("retry method called " + attempt++ + " times " + " at " + new Date());
        if (repayLoan != null) {
            ClientResponse response = repayLoanService.repayLoan(repayLoan);
            return ResponseEntity.status(Integer.parseInt("200")).body(response);
        } else {
            return ResponseEntity.status(400).body(ClientResponse.builder().responseCode("400").responseDescription("Empty Object"));

        }
    }

    /**
     * ------------------------------------------------------------------------------------------
     * --*** FALLBACK METHOD CALLED WHEN ANOTHER MICRO-SERVICE BEING CALLED IS UNAVAILABLE ***---
     * ------------------------------------------------------------------------------------------
     */
    public ResponseEntity<?> fallbackMethod(Exception e) {
        /// Email notification service can be executed here no notify Tech Ops
        return ResponseEntity.status(503).body(ClientResponse.builder()
                .responseCode("503")
                .responseDescription("THE SERVICE IS CURRENTLY DOWN, PLEASE BE PATIENT AS WE RESOLVE THE ISSUE")
                .build());
    }
}
