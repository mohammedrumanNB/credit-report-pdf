package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class EnquiryInfo {
    private String enquiryYear;
    private List<EnquiryDetails> enquiryDetailsList;
}
