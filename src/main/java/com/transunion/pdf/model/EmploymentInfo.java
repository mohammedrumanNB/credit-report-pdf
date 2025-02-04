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
public class EmploymentInfo {
    private String accountType;
    private String dateReported;
    private String occupation;
    private BigDecimal income;
    private BigDecimal monthlyAnnualIncome;
    private BigDecimal netGrossIncome;
}
