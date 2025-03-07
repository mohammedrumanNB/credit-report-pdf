package com.transunion.pdf.model;

import lombok.*;
import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private String fullName = DEFAULT_HYPHEN;
    private String gender = DEFAULT_HYPHEN;
    private String dateOfBirth = DEFAULT_HYPHEN; //ex 28 Feb 1970
}
