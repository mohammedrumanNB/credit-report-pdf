package com.transunion.pdf.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmploymentInfo {
    private String accountType;
    private String dateReported;
    private String occupation;
    private BigDecimal income;
    private String monthlyAnnualIncome;
    private String netGrossIncome;
}
