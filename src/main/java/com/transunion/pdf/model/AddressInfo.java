package com.transunion.pdf.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressInfo {
    private String addressType;
    private String completeAddress;
    private String residenceCode;
    private String dateReported;
}
