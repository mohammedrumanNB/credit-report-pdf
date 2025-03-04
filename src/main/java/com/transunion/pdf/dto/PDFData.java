package com.transunion.pdf.dto;

import com.transunion.pdf.enums.SummaryIdType;
import com.transunion.pdf.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PDFData {
    private String copyrightYear;
    private String controlNumber; // Send in this format 123 456 789 or 987 654 3211
    private String reportGeneratedDate; // Send in this format 12 Dec 2024
    private String cibilScoreDate; // Send in this format 11 Nov 2024
    private Integer cibilScore; //Send 0 for NH Case
    private SummaryIdType summaryIdType; // Use the respective enum while creating the Object
    private String summaryIdNumber;
    private PersonalInfo personalInfo;
    private List<IdentificationInfo> identificationInfoList;
    private List<EmailInfo> emailInfoList;
    private List<ContactInfo> contactInfoList;
    private List<AddressInfo> addressInfoList;
    private int totalAccountsUnderDisputes;
    private BigDecimal currentBalance;
    private BigDecimal amountOverDue;
    private int closedCreditCards;
    private int closedLoans;
    private int openCreditCards;
    private int openLoans;
    private TwelveMonthLatePaymentInfo twelveMonthLatePaymentInfo;
    private ThirtySixMonthLatePaymentInfo thirtySixMonthLatePaymentInfo;
    private EmploymentInfo employmentInfo;
    private List<OpenAccountInfo> openAccountInfoList;
    private List<ClosedAccountInfo> closedAccountInfoList;
    private List<EnquiryInfo> enquiryInfoList; // First Object should contain enquiry information of present year i.e last12 Months, thereafter last24 and last36 Months
}
