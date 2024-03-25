package com.example.repayloan.repository;

import com.example.repayloan.model.RepayLoan;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author samwel.wafula
 * Created on 16/03/2024
 * Time 10:52
 * Project RepayLoan
 */
@Repository
public interface RepayLoanRepository extends JpaRepository<RepayLoan,Integer> {

    @Query(nativeQuery = true,value = "update tbl_repay_loan set is_payment_sent=:isPaymentSent where phone_number=:phoneNumber")
    @Transactional
    @Modifying
    void updateTransactionAfterPaymentISent(@Param("isPaymentSent") boolean isPaymentSent,@Param("phoneNumber") String phoneNumber);

}
