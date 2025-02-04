package com.transunion.pdf.dto;

import com.transunion.pdf.model.ContactInfo;
import com.transunion.pdf.model.EmailInfo;
import com.transunion.pdf.model.PersonalInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalInformation {
    private String controlNumber;
    private String reportGeneratedDate;
    private PersonalInfo personalInfo;
    private JRBeanCollectionDataSource identificationTableDataSource;
    private AddressInformation addressInformation;
    private List<ContactInfo> contactInfoList;
    private List<EmailInfo> emailInfoList;
    private String accountType;
    private String dateReported;
    private String occupation;
    private String income;
    private String monthlyAnnualIncome;
    private String netGrossIncome;
}
