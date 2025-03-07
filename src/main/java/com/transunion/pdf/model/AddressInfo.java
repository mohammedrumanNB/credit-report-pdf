package com.transunion.pdf.model;


import lombok.*;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddressInfo {
    private String addressType = DEFAULT_HYPHEN;
    private String completeAddress = DEFAULT_HYPHEN;
    private String residenceCode = DEFAULT_HYPHEN;
    private String dateReported = DEFAULT_HYPHEN;
}
