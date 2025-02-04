package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDates {
    private String dateOpenedOrDisbursed;
    private String dateLastPayment;
    private String dateClosed;
    private String dateReportedAndCertified;
}
