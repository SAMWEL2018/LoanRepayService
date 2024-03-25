package com.example.repayloan.repository;

import com.example.repayloan.model.RepayLoan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 10:52
 * Project RepayLoan
 */
@Repository
public class Datalayer {

    private final RepayLoanRepository repayLoanRepository;

    @Autowired
    public Datalayer(RepayLoanRepository repayLoanRepository) {
        this.repayLoanRepository = repayLoanRepository;
    }

    public void saveRepayment(RepayLoan repayLoan) {
        repayLoanRepository.save(repayLoan);
    }

    public void updateTransactionAfterPaymentISent(boolean isPaymentSent, String phoneNumber) {
        repayLoanRepository.updateTransactionAfterPaymentISent(isPaymentSent,phoneNumber);
    }
}
