package com.transunion.pdf.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressInfo {
    private String addressType;
    private String completeAddress;
    private String residenceCode;
    private String dateReported;
}
