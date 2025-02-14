package com.transunion.pdf.dto;

import com.transunion.pdf.model.LatePaymentCount;
import com.transunion.pdf.model.LatePaymentRemarkCount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Summary {
    private String controlNumber;
    private String reportGeneratedDate;
    private int cibilScore;
    private String name;
    private String dob;
    private String gender;
    private String idType;
    private String idNumber;
    private String email;
    private String mobileNumber;
    private String address;
    private int enquiries12;
    private int enquiries24;
    private int enquiries36;
    private int totalDisputes;
    private String currentBalance;
    private String amountOverdue;
    private int totalAccounts;
    private int closedCreditCards;
    private int closedLoans;
    private int openCreditCards;
    private int openLoans;
    private LatePaymentCount latePayment12;
    private LatePaymentCount latePayment36;
    private LatePaymentRemarkCount latePaymentRemarkCount12;
    private LatePaymentRemarkCount latePaymentRemarkCount36;

}
