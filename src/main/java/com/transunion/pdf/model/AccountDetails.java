package com.transunion.pdf.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
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

    private BigDecimal creditLimit;
    private BigDecimal highCredit;
    private BigDecimal currentBalance;
    private BigDecimal cashLimit;
    private BigDecimal amountOverdue;
    private BigDecimal rateOfInterest;
    private BigDecimal repaymentTenure;
    private BigDecimal emiAmount;
    private String paymentFrequency;
    private BigDecimal actualPaymentAmount;
}
