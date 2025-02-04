package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetails {
    private String accountOpenOrClosed;
    private String accountType;
    private String bankName;
    private String accountNumber;
    private String ownerShipType;
    private boolean isAccountUnderDispute;
    private DisputeInfo disputeInfo;
    private String accountStatus;

    private BigDecimal creditLimit;
    private BigDecimal highCredit;
    private BigDecimal currentBalance;
    private BigDecimal cashLimit;
    private BigDecimal amountOverdue;
    private String rateOfInterest;
    private String repaymentTenure;
    private BigDecimal emiAmount;
    private String paymentFrequency;
    private BigDecimal actualPaymentAmount;
}
