package com.transunion.pdf.dto;

import com.transunion.pdf.model.*;
import lombok.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformation {
    private String controlNumber;
    private String reportGeneratedDate;
    private PersonalInfo personalInfo;
    private boolean identificationInfoPresent;
    private JRBeanCollectionDataSource identificationTableDataSource;
    private AddressInformation addressInformation;
    private List<ContactInfo> contactInfoList;
    private List<EmailInfo> emailInfoList;
    private boolean employeeInfoPresent;
    private String accountType;
    private String dateReported;
    private String occupation;
    private String income;
    private String monthlyAnnualIncome;
    private String netGrossIncome;
    private List<AddressInfo> addressInfoList;
    private boolean personalInfoDisputePresent;
    private DisputeInfo personalInfoDisputeInfo;
}
