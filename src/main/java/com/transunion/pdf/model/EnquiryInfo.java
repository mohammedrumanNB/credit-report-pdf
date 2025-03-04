package com.transunion.pdf.model;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EnquiryInfo {
    private String enquiryYear;
    private List<EnquiryDetails> enquiryDetailsList;
}
