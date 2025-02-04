package com.transunion.pdf.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInfo {
    private String fullName;
    private String gender;
    private String dateOfBirth; //ex 28 Feb 1970
}
