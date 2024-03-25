package com.example.repayloan;

import com.example.repayloan.controller.ApiController;
import com.example.repayloan.model.RepayLoan;
import com.example.repayloan.repository.Datalayer;
import com.example.repayloan.repository.RepayLoanRepository;
import com.example.repayloan.service.RepayLoanServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author samwel.wafula
 * Created on 19/03/2024
 * Time 13:49
 * Project RegisterService
 */

@ExtendWith(MockitoExtension.class)
@WebMvcTest(controllers = ApiController.class)
public class ApiControllerMockitoTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RepayLoanServiceImpl repayLoanService;
    @MockBean
    private RepayLoanRepository repayLoanRepository;
    @MockBean
    private Datalayer dataLayer;

    @Test
    void registerUser() throws Exception {
        RepayLoan repayLoan = RepayLoan.builder()
                .phoneNumber("071321806")
                .amountToRepay(100.0)
                .build();
        ResultActions response = mockMvc.perform(post("/repayLoanByCustomer").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().writeValueAsString(repayLoan)));
        response.andExpect(status().isOk());

    }
}
