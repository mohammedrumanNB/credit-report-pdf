package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OpenAccountInfo {
    private AccountDetails accountDetails;
    private AccountDates accountDates;
    private String paymentStartDate;
    private String paymentEndDate;
    private List<PastDueMonthlyStatus> pastDueMonthlyStatusList;
    private BigDecimal valueOfCollateral;
    private String typeOfCollateral;
    private String suitFiledOrWillfulDefault;
    private String creditFacilityStatus;
    private BigDecimal writtenOffAmountTotal;
    private BigDecimal writtenOffAmountPrincipal;
    private BigDecimal settlementAmount;
}
