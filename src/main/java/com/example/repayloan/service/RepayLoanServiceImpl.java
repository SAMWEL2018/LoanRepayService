package com.example.repayloan.service;

import com.example.repayloan.AppConfiguration.AppConfig;
import com.example.repayloan.AppConfiguration.HttpService;
import com.example.repayloan.model.ClientResponse;
import com.example.repayloan.model.RepayLoan;
import com.example.repayloan.repository.Datalayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 10:52
 * Project RepayLoan
 */
@Service
@Slf4j
public class RepayLoanServiceImpl implements RepayLoanService {

    private final HttpService httpService;
    private final Datalayer datalayer;

    private final AppConfig appConfig;

    @Autowired
    public RepayLoanServiceImpl(HttpService httpService, Datalayer datalayer, AppConfig appConfig) {
        this.httpService = httpService;
        this.datalayer = datalayer;
        this.appConfig = appConfig;
    }

    @Transactional
    @Override
    public ClientResponse repayLoan(RepayLoan repayLoan) throws JsonProcessingException {
        datalayer.saveRepayment(repayLoan);
        JsonNode res = httpService.sendSyncRequestCall(HttpMethod.POST, appConfig.getRepay_loan_to_check_balanceAndLimitService(), repayLoan);
        ClientResponse response = ClientResponse.builder()
                .responseCode(res.get("responseCode").asText())
                .responseDescription(res.get("responseDescription").asText())
                .build();
        log.info("response {}",new ObjectMapper().writeValueAsString(response));
        if (response.getResponseCode().equalsIgnoreCase("200")) {
            datalayer.updateTransactionAfterPaymentISent(true, repayLoan.getPhoneNumber());
        }
        return response;
    }
}
