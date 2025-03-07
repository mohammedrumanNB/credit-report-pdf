package com.transunion.pdf.model;

import lombok.*;

import java.util.List;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnquiryInfo {
    private String enquiryYear = DEFAULT_HYPHEN;
    private List<EnquiryDetails> enquiryDetailsList;
}
