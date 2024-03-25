package com.example.repayloan.service;

import com.example.repayloan.model.ClientResponse;
import com.example.repayloan.model.RepayLoan;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 10:52
 * Project RepayLoan
 */
public interface RepayLoanService {

    ClientResponse repayLoan(RepayLoan repayLoan) throws JsonProcessingException;
}
