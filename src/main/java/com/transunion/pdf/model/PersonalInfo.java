package com.transunion.pdf.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private String fullName;
    private String gender;
    private String dateOfBirth; //ex 28 Feb 1970
}
