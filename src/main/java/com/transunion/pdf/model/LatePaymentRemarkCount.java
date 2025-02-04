package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LatePaymentRemarkCount {
    private int subStandardRemark;
    private int specialMentionRemark;
    private int doubtfulRemark;
    private int lossRemark;
}
