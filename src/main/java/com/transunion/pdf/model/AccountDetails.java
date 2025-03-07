package com.transunion.pdf.model;

import lombok.*;

import java.math.BigDecimal;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;
import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_BIG_DECIMAL;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDetails {
    private String accountOpenOrClosed = "Open";
    private String accountType = DEFAULT_HYPHEN;
    private String bankName = DEFAULT_HYPHEN;
    private String accountNumber = DEFAULT_HYPHEN;
    private String ownerShipType = DEFAULT_HYPHEN;
    private boolean isAccountUnderDispute;
    private DisputeInfo disputeInfo;

    private BigDecimal creditLimit = DEFAULT_BIG_DECIMAL;
    private BigDecimal highCredit = DEFAULT_BIG_DECIMAL;
    private BigDecimal currentBalance = DEFAULT_BIG_DECIMAL;
    private BigDecimal cashLimit = DEFAULT_BIG_DECIMAL;
    private BigDecimal amountOverdue = DEFAULT_BIG_DECIMAL;
    private BigDecimal rateOfInterest = DEFAULT_BIG_DECIMAL;
    private BigDecimal repaymentTenure = DEFAULT_BIG_DECIMAL;
    private BigDecimal emiAmount = DEFAULT_BIG_DECIMAL;
    private String paymentFrequency = DEFAULT_HYPHEN;
    private BigDecimal actualPaymentAmount = DEFAULT_BIG_DECIMAL;
}
