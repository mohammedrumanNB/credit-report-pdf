package com.transunion.pdf.model;

import lombok.*;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisputeInfo {
    private String cibilRemarks = DEFAULT_HYPHEN;
    private String disputeDate = DEFAULT_HYPHEN;
}
