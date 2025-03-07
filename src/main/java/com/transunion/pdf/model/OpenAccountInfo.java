package com.transunion.pdf.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;
import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_BIG_DECIMAL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenAccountInfo {
    private AccountDetails accountDetails;
    private AccountDates accountDates;
    private String paymentStartDate = DEFAULT_HYPHEN;
    private String paymentEndDate = DEFAULT_HYPHEN;
    private List<PastDueMonthlyStatus> pastDueMonthlyStatusList;
    private BigDecimal valueOfCollateral = DEFAULT_BIG_DECIMAL;
    private String typeOfCollateral = DEFAULT_HYPHEN;
    private String suitFiledOrWillfulDefault = DEFAULT_HYPHEN;
    private String creditFacilityStatus = DEFAULT_HYPHEN;
    private BigDecimal writtenOffAmountTotal = DEFAULT_BIG_DECIMAL;
    private BigDecimal writtenOffAmountPrincipal = DEFAULT_BIG_DECIMAL;
    private BigDecimal settlementAmount = DEFAULT_BIG_DECIMAL;
}
