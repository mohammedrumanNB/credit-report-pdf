package com.transunion.pdf.dto;

import com.transunion.pdf.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

import static com.transunion.pdf.constant.ApplicationConstant.DEFAULT_HYPHEN;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PDFData {
    private String copyrightYear = DEFAULT_HYPHEN;
    private String controlNumber = DEFAULT_HYPHEN; // Send in this format 123 456 789 or 987 654 3211
    private String reportGeneratedDate = DEFAULT_HYPHEN; // Send in this format 12 Dec 2024
    private String cibilScoreDate = DEFAULT_HYPHEN; // Send in this format 11 Nov 2024
    private Integer cibilScore = 0; //Send 0 for NH Case
    private String coverPageName =DEFAULT_HYPHEN;
    private PersonalInfo personalInfo;
    private List<IdentificationInfo> identificationInfoList;
    private List<EmailInfo> emailInfoList;
    private List<ContactInfo> contactInfoList;
    private List<AddressInfo> addressInfoList;
    private int totalAccountsUnderDisputes = 0;
    private EmploymentInfo employmentInfo;
    private List<OpenAccountInfo> openAccountInfoList;
    private List<ClosedAccountInfo> closedAccountInfoList;
    private List<EnquiryInfo> enquiryInfoList; // First Object should contain enquiry information of present year i.e last12 Months, thereafter last24 and last36 Months
    private boolean personalInfoDisputePresent;
    private DisputeInfo personalInfoDisputeInfo;
    private boolean enquiryInfoDisputePresent;
    private DisputeInfo enquiryInfoDisputeInfo;
    private SummaryInfo summaryInfo;
}
