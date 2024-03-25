package com.example.repayloan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 10:51
 * Project RepayLoan
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "tbl_repay_loan")
public class RepayLoan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentId;
    private String phoneNumber;
    @JsonProperty("loanAmount")
    private double amountToRepay;
    @Column(name = "is_payment_sent")
    private boolean isPaymentSent = false;
}
