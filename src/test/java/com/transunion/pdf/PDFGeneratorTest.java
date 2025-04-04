package com.transunion.pdf;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.model.*;
import com.transunion.pdf.service.PDFGenerator;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.AssertionErrors;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class PDFGeneratorTest {
    @Autowired
    private PDFGenerator pdfGenerator;

    @Test
    void testGeneratePdfPaidVersion() {
        try {
            PDFData pdfData = getPdfData();
//            pdfData.getOpenAccountInfoList().get(1).getAccountDetails().setAccountUnderDispute(true);

            // Call the generatePdf method for the PAID version
            byte[] pdfBytes = pdfGenerator.generatePdf(PdfVersion.INDIRECT, pdfData, false, "password");

            // Validate that the PDF byte array is not null or empty
            AssertionErrors.assertNotNull(Arrays.toString(pdfBytes), "PDF byte array should not be null");
            assert pdfBytes.length > 0 : "PDF byte array should contain data";

            System.out.println("PDF generated successfully for PAID version.");

        } catch (JRException e) {
            e.printStackTrace();
            assert false : "An exception occurred during PDF generation: " + e.getMessage();
        }
    }

    @Test
    void testCompileJrxmlToJasper() {
        try {
            JasperCompileManager.compileReportToFile(ApplicationConstant.INDIRECT_ACCOUNTINFO_JRXML_PATH, "account_information.jasper");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //Create Mock Data - PDFData
    private PDFData getPdfData() {
        PDFData pdfData = new PDFData();
        pdfData.setCopyrightYear("2026");
        pdfData.setControlNumber("3214589045");
        pdfData.setCibilScore(300);
        pdfData.setReportGeneratedDate("17 Dec 2024");
        pdfData.setCibilScoreDate("12th Nov 2024");
        pdfData.setPersonalInfo(getPersonalInfo());
        pdfData.setIdentificationInfoList(getIdentificationInfoList());
        pdfData.setEmailInfoList(getEmailInfoList());
        pdfData.setContactInfoList(getContactInfoList());
        pdfData.setAddressInfoList(getAddressInfoList());
        pdfData.setTotalAccountsUnderDisputes(1);
//        pdfData.setCurrentBalance(BigDecimal.valueOf(150));
//        pdfData.setAmountOverDue(BigDecimal.valueOf(50));
//        pdfData.setClosedCreditCards(0);
//        pdfData.setClosedLoans(0);
//        pdfData.setOpenCreditCards(10);
//        pdfData.setOpenLoans(0);
        pdfData.setPersonalInfoDisputePresent(true);
        pdfData.setPersonalInfoDisputeInfo(new DisputeInfo("Remarks", "25 Jasn 2025"));

        pdfData.setEnquiryInfoDisputePresent(true);
        pdfData.setEnquiryInfoDisputeInfo(new DisputeInfo());

//        pdfData.setSummaryIdType(SummaryIdType.DRIVERS_LICENSE);
//        pdfData.setSummaryIdNumber("DYDPR23P");

//        pdfData.setTwelveMonthLatePaymentInfo(getTwelveMonthLatePaymentInfo());
//        pdfData.setTwelveMonthLatePaymentInfo(new TwelveMonthLatePaymentInfo(new LatePaymentCount(),new LatePaymentRemarkCount()));

//        pdfData.setThirtySixMonthLatePaymentInfo(getThirtySixMonthLatePaymentInfo());
//        pdfData.setThirtySixMonthLatePaymentInfo(new ThirtySixMonthLatePaymentInfo(new LatePaymentCount(), new LatePaymentRemarkCount()));

        pdfData.setEmploymentInfo(getEmploymentInfo());


        pdfData.setOpenAccountInfoList(getOpenAccountInfoList());
        pdfData.setClosedAccountInfoList(getClosedAccountInfoList());

        pdfData.setEnquiryInfoList(getEnquiryInfoList());
        pdfData.setSummaryInfo(getSummaryInfo());


        return pdfData;
    }

    private SummaryInfo getSummaryInfo() {
        SummaryInfo summaryInfo = new SummaryInfo();
//        summaryInfo.setDob("25/09/1997");
//        summaryInfo.setName("Summit");
//        summaryInfo.setEmail("email");
//        summaryInfo.setMobileNumber("987654321");
//        summaryInfo.setGender("Male");
//        summaryInfo.setAddress("address");
//        summaryInfo.setSummaryIdNumber("IDNumber");
//        summaryInfo.setSummaryIdType(SummaryIdType.DRIVERS_LICENSE);
//        summaryInfo.setAmountOverdue(BigDecimal.valueOf(0));
//        summaryInfo.setCurrentBalance(BigDecimal.valueOf(0));
//        summaryInfo.setClosedLoans(10);
//        summaryInfo.setClosedCreditCards(5);
//        summaryInfo.setOpenLoans(6);
//        summaryInfo.setOpenCreditCards(5);
//        summaryInfo.setLatePaymentRemarkCount12(getLatePaymentRemark());
//        summaryInfo.setLatePaymentRemarkCount36(getLatePaymentRemark());
//        summaryInfo.setLatePayment12(getLatePaymentCount());
//        summaryInfo.setLatePayment36(getLatePaymentCount());
//        summaryInfo.setEnquiries12(10);
//        summaryInfo.setEnquiries24(10);
//        summaryInfo.setEnquiries36(10);
//        summaryInfo.setTotalAccountsUnderDisputes(10);
        summaryInfo.setLatePayment12(new LatePaymentCount());
        return summaryInfo;
    }

    private List<EnquiryInfo> getEnquiryInfoList() {
        List<EnquiryInfo> enquiryInfoList = new ArrayList<>();
        enquiryInfoList.add(EnquiryInfo.builder()
                .enquiryYear("2024")
                .enquiryDetailsList(getEnquiryDetailsList())
                .build());
        enquiryInfoList.add(EnquiryInfo.builder()
                .enquiryYear("2023")
                .enquiryDetailsList(getEnquiryDetailsList())
                .build());
        enquiryInfoList.add(EnquiryInfo.builder()
                .enquiryYear("2022")
//                .enquiryDetailsList(getEnquiryDetailsList())
                .build());

        return enquiryInfoList;
    }

    private List<EnquiryDetails> getEnquiryDetailsList() {
        List<EnquiryDetails> enquiryDetailsList = new ArrayList<>();
        enquiryDetailsList.add(EnquiryDetails.builder()
                .dateOfEnquiry("17 Dec 2023")
                .memberName("Kotak Mahindra")
                .enquiryPurpose("Credit Card")
                .build());
        enquiryDetailsList.add(EnquiryDetails.builder()
                .dateOfEnquiry("12 Nov 2023")
                .memberName("HDFC Bank")
                .enquiryPurpose("Commercial Vehicle Loan")
                .build());

        enquiryDetailsList.add(EnquiryDetails.builder()
                .dateOfEnquiry("04 Sep 2023")
                .memberName("Shamrao Vitthal Co-operative Bank")
                .enquiryPurpose("Personal Loan")
                .build());
        return enquiryDetailsList;
    }

    private List<ClosedAccountInfo> getClosedAccountInfoList() {
        List<ClosedAccountInfo> closedAccountInfoList = new ArrayList<>();
        ClosedAccountInfo closedAccountInfo = new ClosedAccountInfo();
        closedAccountInfo.setAccountDetails(new AccountDetails());
        closedAccountInfo.setAccountDates(new AccountDates());
        closedAccountInfo.setPastDueMonthlyStatusList(getPastDueMonthlyStatusList());
        closedAccountInfoList.add(closedAccountInfo);
        closedAccountInfoList.add(ClosedAccountInfo.builder()
                .accountDetails(getAccountDetails("Closed"))
                .accountDates(getAccountDates())
                .paymentStartDate("Closed Account")
                .paymentEndDate("25/06/2024")
                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
                .valueOfCollateral(BigDecimal.valueOf(50000))
                .typeOfCollateral("Prop")
                .suitFiledOrWillfulDefault("Yes")
                .creditFacilityStatus("Don't Know")
                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
                .settlementAmount(BigDecimal.valueOf(5000))
                .build());
//        closedAccountInfoList.add(ClosedAccountInfo.builder()
//                .accountDetails(getAccountDetails("Closed"))
//                .accountDates(getAccountDates())
//                .paymentStartDate("Closed Account")
//                .paymentEndDate("25/06/2024")
//                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
//                .valueOfCollateral(BigDecimal.valueOf(50000))
//                .typeOfCollateral("Prop")
//                .suitFiledOrWillfulDefault("Yes")
//                .creditFacilityStatus("Don't Know")
//                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
//                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
//                .settlementAmount(BigDecimal.valueOf(5000))
//                .build());
//        closedAccountInfoList.add(ClosedAccountInfo.builder()
//                .accountDetails(getAccountDetails("Closed"))
//                .accountDates(getAccountDates())
//                .paymentStartDate("Closed Account")
//                .paymentEndDate("25/06/2024")
//                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
//                .valueOfCollateral(BigDecimal.valueOf(50000))
//                .typeOfCollateral("Prop")
//                .suitFiledOrWillfulDefault("Yes")
//                .creditFacilityStatus("Don't Know")
//                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
//                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
//                .settlementAmount(BigDecimal.valueOf(5000))
//                .build());
//        closedAccountInfoList.add(ClosedAccountInfo.builder()
//                .accountDetails(getAccountDetails())
//                .accountDates(getAccountDates())
//                .paymentStartDate("Closed Account")
//                .paymentEndDate("25/06/2024")
//                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
//                .valueOfCollateral(BigDecimal.valueOf(50000))
//                .typeOfCollateral("Prop")
//                .suitFiledOrWillfulDefault("Yes")
//                .creditFacilityStatus("Don't Know")
//                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
//                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
//                .settlementAmount(BigDecimal.valueOf(5000))
//                .build());
//        closedAccountInfoList.add(ClosedAccountInfo.builder()
//                .accountDetails(getAccountDetails())
//                .accountDates(getAccountDates())
//                .paymentStartDate("Closed Account")
//                .paymentEndDate("25/06/2024")
//                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
//                .valueOfCollateral(BigDecimal.valueOf(50000))
//                .typeOfCollateral("Prop")
//                .suitFiledOrWillfulDefault("Yes")
//                .creditFacilityStatus("Don't Know")
//                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
//                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
//                .settlementAmount(BigDecimal.valueOf(5000))
//                .build());
        return closedAccountInfoList;
    }

    private List<OpenAccountInfo> getOpenAccountInfoList() {
        List<OpenAccountInfo> openAccountInfoList = new ArrayList<>();
        OpenAccountInfo openAccountInfo = new OpenAccountInfo();
        openAccountInfo.setAccountDetails(getAccountDetails("Open"));
        openAccountInfo.setAccountDates(new AccountDates());
        openAccountInfo.setPastDueMonthlyStatusList(getPastDueMonthlyStatusList());
        openAccountInfoList.add(openAccountInfo);
        openAccountInfoList.add(OpenAccountInfo.builder()
                .accountDetails(getAccountDetails("Open"))
                .accountDates(getAccountDates())
                .paymentStartDate("25/06/2020")
                .paymentEndDate("25/06/2024")
                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
                .valueOfCollateral(BigDecimal.valueOf(50000))
                .typeOfCollateral("Property and rental")
                .suitFiledOrWillfulDefault("Yes")
                .creditFacilityStatus("Don't Know")
                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
                .settlementAmount(BigDecimal.valueOf(5000))
                .build());
//
//        openAccountInfoList.add(OpenAccountInfo.builder()
//                .accountDetails(getAccountDetails("Open"))
//                .accountDates(getAccountDates())
//                .paymentStartDate("25/06/2020")
//                .paymentEndDate("25/06/2024")
//                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
//                .valueOfCollateral(BigDecimal.valueOf(50000))
//                .typeOfCollateral("Property")
//                .suitFiledOrWillfulDefault("Yes")
//                .creditFacilityStatus("Don't Know")
//                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
//                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
//                .settlementAmount(BigDecimal.valueOf(5000))
//                .build());
//        openAccountInfoList.add(OpenAccountInfo.builder()
//                .accountDetails(getAccountDetails("Open"))
//                .accountDates(getAccountDates())
//                .paymentStartDate("25/06/2020")
//                .paymentEndDate("25/06/2024")
//                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
//                .valueOfCollateral(BigDecimal.valueOf(50000))
//                .typeOfCollateral("Property")
//                .suitFiledOrWillfulDefault("Yes")
//                .creditFacilityStatus("Don't Know")
//                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
//                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
//                .settlementAmount(BigDecimal.valueOf(5000))
//                .build());
//        openAccountInfoList.add(OpenAccountInfo.builder()
//                .accountDetails(getAccountDetails("Open"))
//                .accountDates(getAccountDates())
//                .paymentStartDate("25/06/2020")
//                .paymentEndDate("25/06/2024")
//                .pastDueMonthlyStatusList(getPastDueMonthlyStatusList())
//                .valueOfCollateral(BigDecimal.valueOf(50000))
//                .typeOfCollateral("Property")
//                .suitFiledOrWillfulDefault("Yes")
//                .creditFacilityStatus("Don't Know")
//                .writtenOffAmountTotal(BigDecimal.valueOf(10000))
//                .writtenOffAmountPrincipal(BigDecimal.valueOf(1500))
//                .settlementAmount(BigDecimal.valueOf(5000))
//                .build());
        return openAccountInfoList;
    }

    private List<PastDueMonthlyStatus> getPastDueMonthlyStatusList() {
        List<PastDueMonthlyStatus> pastDueMonthlyStatusList = new ArrayList<>();
        pastDueMonthlyStatusList.add(PastDueMonthlyStatus.builder()
                .year(2021).febDue("1").marDue("###")
                .aprDue("000").mayDue("0").julDue("000")
                .augDue("SMA").sepDue("000").octDue("XXX").novDue("001")
                .decDue("002")
                .build());
        pastDueMonthlyStatusList.add(PastDueMonthlyStatus.builder()
                .year(2022).janDue("000").febDue("000").marDue("002")
                .aprDue("000").mayDue("STD").junDue("30").julDue("000")
                .augDue("SMA").sepDue("000").octDue("DBT").novDue("001")
                .decDue("LSS")
                .build());
        pastDueMonthlyStatusList.add(PastDueMonthlyStatus.builder()
                .year(2023).janDue("XXX").febDue("000").marDue("002")
                .aprDue("50").mayDue("STD").junDue("000").julDue("000")
                .augDue("SMA").sepDue("000").octDue("DBT").novDue("001")
                .decDue("LSS")
                .build());
//        pastDueMonthlyStatusList.add(PastDueMonthlyStatus.builder()
//                .year(2023).janDue("XXX").febDue("000").marDue("002")
//                .aprDue("000").mayDue("STD").junDue("000").julDue("000")
//                .augDue("SMA").sepDue("000").octDue("DBT").novDue("001")
//                .decDue("LSS")
//                .build());
        return pastDueMonthlyStatusList;
    }

    private AccountDates getAccountDates() {
        return AccountDates.builder()
                .dateOpenedOrDisbursed("14/07/2020")
                .dateLastPayment("14/06/2020")
                .dateClosed("15/06/1997")
                .dateReportedAndCertified("14/05/2010")
                .build();
    }

    private AccountDetails getAccountDetails(String accountOpenOrClosed) {
        return AccountDetails.builder()
                .accountOpenOrClosed(accountOpenOrClosed)
                .accountType("Business Non-Funded Credit Facility – Priority Sector – Small Business")
                .bankName("Evangelical Social Action Forum Small Finance Bank")
                .highCreditOrAmountSanctionLabel("High Credit")
                .accountNumber("1234567890")
                .ownerShipType("Joint Ownership")
                .isAccountUnderDispute(false)
                .disputeInfo(new DisputeInfo("IDontknow", "10/05/2020"))
                .creditLimit(BigDecimal.valueOf(50000))
                .highCredit(BigDecimal.valueOf(10000))
                .currentBalance(BigDecimal.valueOf(4000))
                .cashLimit(BigDecimal.valueOf(3000))
                .amountOverdue(BigDecimal.valueOf(2000))
                .rateOfInterest(BigDecimal.valueOf(-1.0))
                .repaymentTenure(BigDecimal.valueOf(10))
                .emiAmount(BigDecimal.valueOf(3000))
                .paymentFrequency("Monthly")
                .actualPaymentAmount(BigDecimal.valueOf(5000))
                .build();
    }

    private EmploymentInfo getEmploymentInfo() {
        EmploymentInfo employmentInfo = new EmploymentInfo();
        employmentInfo.setAccountType("Loan");
        employmentInfo.setOccupation("Dev");
//        return EmploymentInfo.builder()
//                .accountType("Loan")
//                .dateReported("14/07/2022")
//                .occupation("Developer")
//                .income(BigDecimal.valueOf(10000))
//                .monthlyAnnualIncome("Monthly")
//                .netGrossIncome("Net Income")
//                .build();
        return employmentInfo;
    }

    private ThirtySixMonthLatePaymentInfo getThirtySixMonthLatePaymentInfo() {
        ThirtySixMonthLatePaymentInfo thirtySixMonthLatePaymentInfo = new ThirtySixMonthLatePaymentInfo();
        thirtySixMonthLatePaymentInfo.setLatePaymentCount(getLatePaymentCount());
        thirtySixMonthLatePaymentInfo.setLatePaymentRemarkCount(getLatePaymentRemark());
        return thirtySixMonthLatePaymentInfo;

    }

    private TwelveMonthLatePaymentInfo getTwelveMonthLatePaymentInfo() {
        TwelveMonthLatePaymentInfo twelveMonthLatePaymentInfo = new TwelveMonthLatePaymentInfo();
        twelveMonthLatePaymentInfo.setLatePaymentCount(getLatePaymentCount());
        twelveMonthLatePaymentInfo.setLatePaymentRemarkCount(getLatePaymentRemark());

        return twelveMonthLatePaymentInfo;
    }

    private LatePaymentRemarkCount getLatePaymentRemark() {
        return LatePaymentRemarkCount.builder()
                .subStandardRemark(2)
                .specialMentionRemark(4)
                .lossRemark(6)
                .doubtfulRemark(8)
                .build();
    }

    private LatePaymentCount getLatePaymentCount() {
        return LatePaymentCount.builder()
                .days1To30(30)
                .days31To60(2)
                .days61To90(10)
                .moreThan90Days(15)
                .build();
    }

    private List<AddressInfo> getAddressInfoList() {
        List<AddressInfo> addressInfoList = new ArrayList<>();
//        addressInfoList.add(AddressInfo.builder()
//                .addressType(ApplicationConstant.ADDRESS_RESIDENCE)
//                .completeAddress("Yaseen Manzil, Vijayapura 4th cross, Behind Indian Service Station, Chikmagaluru, Karnataka - 577101")
//                .residenceCode("-")
//                .dateReported("25/06/2020")
//                .build());
//
//        addressInfoList.add(AddressInfo.builder()
//                .addressType(ApplicationConstant.ADDRESS_PERMANENT)
//                .completeAddress("Yaseen Manzil, Vijayapura 4th cross, Behind Indian Service Station, Chikmagaluru, Karnataka - 577101")
//                .residenceCode("+91")
//                .dateReported("25/06/2020")
//                .build());
//
//        addressInfoList.add(AddressInfo.builder()
//                .addressType(ApplicationConstant.ADDRESS_PERMANENT)
//                .completeAddress("Yaseen Manzil, Vijayapura 4th cross, Behind Indian Service Station, Chikmagaluru, Karnataka - 577101")
//                .residenceCode("+91")
//                .dateReported("25/06/2020")
//                .build());
//        addressInfoList.add(AddressInfo.builder()
//                .addressType(ApplicationConstant.ADDRESS_PERMANENT)
//                .completeAddress("Yaseen Manzil, Vijayapura 4th cross, Behind Indian Service Station, Chikmagaluru, Karnataka - 577101")
//                .residenceCode("+91")
//                .dateReported("25/06/2020")
//                .build());

        return addressInfoList;

    }

    private List<ContactInfo> getContactInfoList() {
        List<ContactInfo> contactInfoList = new ArrayList<>();
        contactInfoList.add(new ContactInfo("test", "9876543210", "-"));
//        contactInfoList.add(new ContactInfo(ApplicationConstant.CONTACT_HOME, "9876543210", "+91"));
//        contactInfoList.add(new ContactInfo(ApplicationConstant.CONTACT_OFFICE, "9876543210", "+91"));
//        contactInfoList.add(new ContactInfo(ApplicationConstant.CONTACT_NOT_CLASSIFIED, "9876543210", "-"));
        return contactInfoList;
    }

    private List<EmailInfo> getEmailInfoList() {
        List<EmailInfo> emailInfoList = new ArrayList<>();
        emailInfoList.add(new EmailInfo("mohammed.ruman25@netbrahma.com"));
        emailInfoList.add(new EmailInfo("mohammed.rum@netbrahma.com"));
        emailInfoList.add(new EmailInfo("mohammed.r@netbrahma.com"));

        return emailInfoList;

    }

    private List<IdentificationInfo> getIdentificationInfoList() {
        List<IdentificationInfo> identificationInfoList = new ArrayList<>();
        identificationInfoList.add(new IdentificationInfo(ApplicationConstant.ID_TYPE_PAN, "DYDPR123X", "12/07/2020", "-"));
//        identificationInfoList.add(new IdentificationInfo(ApplicationConstant.ID_TYPE_PASSPORT, "RQERWT98", "-", "-"));
//        identificationInfoList.add(new IdentificationInfo(ApplicationConstant.ID_TYPE_DRIVER_LICENSE, "DL4567890", "-", "12/07/2020"));
//        identificationInfoList.add(new IdentificationInfo(ApplicationConstant.ID_TYPE_VOTER_ID, "YJP123456", "12/07/2020", "12/07/2020"));
//        identificationInfoList.add(new IdentificationInfo(ApplicationConstant.ID_TYPE_RATION_CARD, "RC123456", "12/07/2020", "12/07/2020"));
        return identificationInfoList;

    }

    private PersonalInfo getPersonalInfo() {
        PersonalInfo personalInfo = new PersonalInfo();
        personalInfo.setFullName("Nupur Kumari");
        personalInfo.setDateOfBirth("25/06/1997");
        personalInfo.setGender("Male");
        return personalInfo;
    }
}
