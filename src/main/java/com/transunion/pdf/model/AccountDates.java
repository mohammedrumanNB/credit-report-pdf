package com.transunion.pdf.model;

import lombok.*;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDates {
    private String dateOpenedOrDisbursed = DEFAULT_HYPHEN;
    private String dateLastPayment = DEFAULT_HYPHEN;
    private String dateClosed = DEFAULT_HYPHEN;
    private String dateReportedAndCertified = DEFAULT_HYPHEN;
}
