package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwelveMonthLatePaymentInfo {
    private LatePaymentCount latePaymentCount;
    private LatePaymentRemarkCount latePaymentRemarkCount;
}
