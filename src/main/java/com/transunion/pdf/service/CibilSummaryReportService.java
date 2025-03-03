package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.dto.Summary;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.*;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class CibilSummaryReportService {
    public JasperReport getCibilSummaryReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case NH:
                //Get Cibil Summary Report jrxml and compile it
                filePath = ApplicationConstant.NH_SUMMARY_JASPER_PATH;
                break;
            case STARTER:
                //Get Cibil Summary Report jrxml and compile it
                filePath = ApplicationConstant.STARTER_SUMMARY_JASPER_PATH;
                break;
            case PAID:
                //Get Cibil Summary Report jrxml and compile it
                filePath = ApplicationConstant.PAID_SUMMARY_JASPER_PATH;
                break;
            default:
                throw new InvalidDataException(1016, "Unsupported PDF version: " + pdfVersion);
        }
        return (JasperReport) JRLoader.loadObject(new File(filePath));
    }

    public Summary getCibilSummaryParam(PDFData pdfData) {
        Summary summary = new Summary();

        summary.setControlNumber(pdfData.getControlNumber());
        summary.setReportGeneratedDate(pdfData.getReportGeneratedDate());

        PersonalInfo personalInfo = pdfData.getPersonalInfo();
        List<IdentificationInfo> identificationInfoList = pdfData.getIdentificationInfoList();
        List<ContactInfo> contactInfoList = pdfData.getContactInfoList();
        List<EmailInfo> emailInfoList = pdfData.getEmailInfoList();
        List<AddressInfo> addressInfoList = pdfData.getAddressInfoList();
        TwelveMonthLatePaymentInfo twelveMonthLatePaymentInfo = pdfData.getTwelveMonthLatePaymentInfo();
        ThirtySixMonthLatePaymentInfo thirtySixMonthLatePaymentInfo = pdfData.getThirtySixMonthLatePaymentInfo();
        List<EnquiryInfo> enquiryInfoList = pdfData.getEnquiryInfoList();


        //Validate Personal Info
        CommonUtil.validatePersonalInfo(personalInfo);

        //Validate Identification Details
        CommonUtil.validateIdentificationInfo(identificationInfoList);

        //Get PAN Card Details if available
        IdentificationInfo identificationInfo = getIdentificationInfo(identificationInfoList);


        //Validate Late Payment Info
        CommonUtil.validateLatePaymentInfoTw(twelveMonthLatePaymentInfo);
        CommonUtil.validateLatePaymentInfoTh(thirtySixMonthLatePaymentInfo);


        //Get Total Accounts
        int totalAccounts = getTotalAccounts(pdfData);

        //Assign Values to Summary Object
        summary.setCibilScore(pdfData.getCibilScore());
        summary.setName(personalInfo.getFullName());
        summary.setDob(personalInfo.getDateOfBirth());
        summary.setGender(personalInfo.getGender());
        summary.setIdType(identificationInfo.getIdentificationType());
        summary.setIdNumber(identificationInfo.getIdNumber());
        summary.setEmail(!emailInfoList.isEmpty() ? emailInfoList.get(0).getEmail() : "-");
        summary.setMobileNumber(!contactInfoList.isEmpty() ? contactInfoList.get(0).getContactNumber() : "-");
        summary.setAddress(!addressInfoList.isEmpty() ? addressInfoList.get(0).getCompleteAddress() : "-");

        if (enquiryInfoList == null || enquiryInfoList.isEmpty()) {
            summary.setEnquiries12(0);
            summary.setEnquiries24(0);
            summary.setEnquiries36(0);
        } else {
            summary.setEnquiries12(enquiryInfoList.get(0) != null ? enquiryInfoList.get(0).getEnquiryDetailsList() == null || enquiryInfoList.get(0).getEnquiryDetailsList().isEmpty() ? 0 : enquiryInfoList.get(0).getEnquiryDetailsList().size() : 0);
            summary.setEnquiries24(enquiryInfoList.get(1) != null ? enquiryInfoList.get(1).getEnquiryDetailsList() == null || enquiryInfoList.get(1).getEnquiryDetailsList().isEmpty() ? 0 : enquiryInfoList.get(1).getEnquiryDetailsList().size() : 0);
            summary.setEnquiries36(enquiryInfoList.get(2) != null ? enquiryInfoList.get(2).getEnquiryDetailsList() == null || enquiryInfoList.get(2).getEnquiryDetailsList().isEmpty() ? 0 : enquiryInfoList.get(2).getEnquiryDetailsList().size() : 0);
        }

        summary.setTotalDisputes(pdfData.getTotalDisputes());

        //Format the monetary values to Indian Format
        summary.setCurrentBalance(CommonUtil.formatIndianCurrency(pdfData.getCurrentBalance()));
        summary.setAmountOverdue(CommonUtil.formatIndianCurrency(pdfData.getAmountOverDue()));


        summary.setTotalAccounts(totalAccounts);
        summary.setClosedCreditCards(pdfData.getClosedCreditCards());
        summary.setClosedLoans(pdfData.getClosedLoans());
        summary.setOpenCreditCards(pdfData.getOpenCreditCards());
        summary.setOpenLoans(pdfData.getOpenLoans());

        LatePaymentCount latePaymentCount12 = twelveMonthLatePaymentInfo.getLatePaymentCount();

        summary.setLatePayment12(latePaymentCount12);


        LatePaymentCount latePaymentCount36 = thirtySixMonthLatePaymentInfo.getLatePaymentCount();

        summary.setLatePayment36(latePaymentCount36);

        //Fetch Late Payment Remarks - 12 Months
        LatePaymentRemarkCount latePaymentRemarkCount12 = twelveMonthLatePaymentInfo.getLatePaymentRemarkCount();

        summary.setLatePaymentRemarkCount12(latePaymentRemarkCount12);

        //Fetch Late Payment Remarks - 36 Months
        LatePaymentRemarkCount latePaymentRemarkCount36 = thirtySixMonthLatePaymentInfo.getLatePaymentRemarkCount();

        summary.setLatePaymentRemarkCount36(latePaymentRemarkCount36);


        return summary;
    }

    private int getTotalAccounts(PDFData pdfData) {

        return pdfData.getOpenCreditCards() + pdfData.getOpenLoans();
    }

    public IdentificationInfo getIdentificationInfo(List<IdentificationInfo> identificationInfoList) {

        // Search for PAN ID and return it if found
        for (IdentificationInfo info : identificationInfoList) {
            if (ApplicationConstant.ID_TYPE_PAN.equalsIgnoreCase(info.getIdentificationType())) {
                return info;
            }
        }

        // Return the first element if PAN ID is not found
        return identificationInfoList.get(0);

    }
}
