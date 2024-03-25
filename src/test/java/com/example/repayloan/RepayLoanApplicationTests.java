package com.example.repayloan;

import com.example.repayloan.model.RepayLoan;
import com.example.repayloan.service.RepayLoanServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RepayLoanApplicationTests {
    private final RepayLoanServiceImpl repayLoanService;

    @Autowired
    public RepayLoanApplicationTests(RepayLoanServiceImpl repayLoanService) {
        this.repayLoanService = repayLoanService;
    }

    @Test
    void loanRepay() throws JsonProcessingException {
        RepayLoan repayLoan = RepayLoan.builder()
                .phoneNumber("0712321806")
                .amountToRepay(100.0)
                .build();
        repayLoanService.repayLoan(repayLoan);
    }

}
