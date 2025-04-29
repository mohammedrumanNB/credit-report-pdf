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
public class EmploymentInfo {
    private String accountType = DEFAULT_HYPHEN;
    private String dateReported = DEFAULT_HYPHEN;
    private String occupation = DEFAULT_HYPHEN;
    private BigDecimal income = DEFAULT_BIG_DECIMAL;
    private String monthlyAnnualIncome = DEFAULT_HYPHEN;
    private String netGrossIncome = DEFAULT_HYPHEN;
    private boolean employeeInfoPresent;
}
