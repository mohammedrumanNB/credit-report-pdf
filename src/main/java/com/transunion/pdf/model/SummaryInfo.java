package com.transunion.pdf.model;

import com.transunion.pdf.enums.SummaryIdType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_BIG_DECIMAL;
import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SummaryInfo {
    private String name = DEFAULT_HYPHEN;
    private String dob = DEFAULT_HYPHEN;
    private String gender = DEFAULT_HYPHEN;
    private SummaryIdType summaryIdType; // Use the respective enum while creating the Object
    private String summaryIdNumber = DEFAULT_HYPHEN;
    private String email = DEFAULT_HYPHEN;
    private String mobileNumber = DEFAULT_HYPHEN;
    private String address = DEFAULT_HYPHEN;
    private int totalEnquiries=0;
    private int enquiries12 = 0;
    private int enquiries24 = 0;
    private int enquiries36 = 0;
    private BigDecimal currentBalance = DEFAULT_BIG_DECIMAL;
    private BigDecimal amountOverdue = DEFAULT_BIG_DECIMAL;
    private int totalAccountsUnderDisputes = 0;
    private int closedCreditCards = 0;
    private int closedLoans = 0;
    private int openCreditCards = 0;
    private int openLoans = 0;
    private LatePaymentCount latePayment12;
    private LatePaymentCount latePayment36;
    private LatePaymentRemarkCount latePaymentRemarkCount12;
    private LatePaymentRemarkCount latePaymentRemarkCount36;
}
