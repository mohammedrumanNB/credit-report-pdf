package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.dto.Summary;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.*;
import com.transunion.pdf.util.CibilGraphUtil;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CibilSummaryReportService {
//    public JasperReport getCibilSummaryReport(PdfVersion pdfVersion) throws JRException {
//        String filePath="";
//        switch (pdfVersion) {
//            case FREE:
//                //Get Cibil Summary Report jrxml and compile it
//                break;
//            case PAID:
//                //Get Cibil Summary Report jrxml and compile it
//                filePath= ApplicationConstant.PAID_SUMMARY_JRXML_PATH;
//                break;
//            case INDIRECT:
//                //Get Cibil Summary Report jrxml and compile it
//                break;
//            case STARTER:
//                //Get Cibil Summary Report jrxml and compile it
//                break;
//            case NH:
//                //Get Cibil Summary Report jrxml and compile it
//                filePath= ApplicationConstant.NH_SUMMARY_JRXML_PATH;
//                break;
//            default:
//                throw new InvalidDataException(1016,"Unsupported PDF version: " + pdfVersion);
//        }
//        return JasperCompileManager.compileReport(filePath);
//    }

    public Summary getCibilSummaryParam(PDFData pdfData) {
        Summary summary=new Summary();

        PersonalInfo personalInfo = pdfData.getPersonalInfo();
        List<IdentificationInfo> identificationInfoList = pdfData.getIdentificationInfoList();
        List<ContactInfo> contactInfoList = pdfData.getContactInfoList();
        List<EmailInfo> emailInfoList = pdfData.getEmailInfoList();
        List<AddressInfo> addressInfoList = pdfData.getAddressInfoList();
        TwelveMonthLatePaymentInfo twelveMonthLatePaymentInfo = pdfData.getTwelveMonthLatePaymentInfo();
        ThirtySixMonthLatePaymentInfo thirtySixMonthLatePaymentInfo = pdfData.getThirtySixMonthLatePaymentInfo();


        //Validate Personal Info
        CommonUtil.validatePersonalInfo(personalInfo);

        //Validate Identification Details
        CommonUtil.validateIdentificationInfo(identificationInfoList);

        //Get PAN Card Details if available
        IdentificationInfo identificationInfo=getIdentificationInfo(identificationInfoList);

        //Validate Contact Info - Get Clarification on this - 07/11
        CommonUtil.validateContactInfo(contactInfoList);


        //Validate Address Info

        //Validate Late Payment Info
        CommonUtil.validateLatePaymentInfoTw(twelveMonthLatePaymentInfo);
        CommonUtil.validateLatePaymentInfoTh(thirtySixMonthLatePaymentInfo);


        //Get Total Accounts
        int totalAccounts = getTotalAccounts(pdfData);

        //Assign Values to Summary Object
        summary.setCibilScore(pdfData.getCibilScore());
        summary.setCibilGraph(CibilGraphUtil.getCibilGraph(pdfData.getCibilScore()));
        summary.setName(personalInfo.getFullName());
        summary.setDob(personalInfo.getDateOfBirth());
        summary.setGender(personalInfo.getGender());
        summary.setIdType(identificationInfo.getIdentificationType());
        summary.setIdNumber(identificationInfo.getIdNumber());
        summary.setEmail(emailInfoList.size()>0 ?emailInfoList.get(0).getEmail():"-");
        summary.setMobileNumber(contactInfoList.size()>0? contactInfoList.get(0).getContactNumber():"-");
        summary.setAddress(addressInfoList.size()>0? addressInfoList.get(0).getCompleteAddress():"-");
        summary.setTotalEnquiries(pdfData.getTotalEnquiries());
        summary.setTotalDisputes(pdfData.getTotalDisputes());

        //Format the monetary values to Indian Format
        summary.setCurrentBalance(CommonUtil.formatIndianCurrency(pdfData.getCurrentBalance()));
        summary.setAmountOverdue(CommonUtil.formatIndianCurrency(pdfData.getAmountOverDue()));


        summary.setTotalAccounts(totalAccounts);
        summary.setClosedCreditCards(pdfData.getClosedCreditCards());
        summary.setClosedLoans(pdfData.getClosedLoans());
        summary.setOpenCreditCards(pdfData.getOpenCreditCards());
        summary.setOpenLoans(pdfData.getOpenLoans());

        //Create JRDataSource for PieChart
        JRBeanCollectionDataSource pieChartData=new JRBeanCollectionDataSource(null);
        if(totalAccounts>0){
            pieChartData=getPieChartData(pdfData);
        }

        summary.setPieChartDataSource(pieChartData);

        //Create JRDataSource for Bar chart - 12 Months
        JRBeanCollectionDataSource barChartData12=new JRBeanCollectionDataSource(null);
        LatePaymentCount latePaymentCount12 = twelveMonthLatePaymentInfo.getLatePaymentCount();
        int lpc12=getLatePaymentCount(latePaymentCount12);
        if(lpc12>0){
            summary.setLatePayment12(true);
            barChartData12=getBarChartData(latePaymentCount12);

        }
        summary.setLatePayment12DataSource(barChartData12);

        //Create JRDataSource for Bar chart - 36 Months
        JRBeanCollectionDataSource barChartData36=new JRBeanCollectionDataSource(null);
        LatePaymentCount latePaymentCount36 = thirtySixMonthLatePaymentInfo.getLatePaymentCount();
        int lpc36=getLatePaymentCount(latePaymentCount36);
        if(lpc36>0){
            summary.setLatePayment36(true);
            barChartData36=getBarChartData(latePaymentCount36);
        }
        summary.setLatePayment36DataSource(barChartData36);

        //Fetch Late Payment Remarks - 12 Months
        LatePaymentRemarkCount latePaymentRemarkCount12 = twelveMonthLatePaymentInfo.getLatePaymentRemarkCount();

        summary.setLatePaymentRemarkCount12(latePaymentRemarkCount12);

        //Fetch Late Payment Remarks - 36 Months
        LatePaymentRemarkCount latePaymentRemarkCount36 = thirtySixMonthLatePaymentInfo.getLatePaymentRemarkCount();

        summary.setLatePaymentRemarkCount36(latePaymentRemarkCount36);


        return summary;
    }

    private JRBeanCollectionDataSource getBarChartData(LatePaymentCount latePaymentCount) {
        BarChartData dat1=new BarChartData("1-30 Days",latePaymentCount.getDays1To30());
        BarChartData dat2=new BarChartData("31-60 Days",latePaymentCount.getDays31To60());
        BarChartData dat3=new BarChartData("61-90 Days",latePaymentCount.getDays61To90());
        BarChartData dat4=new BarChartData(">90 Days",latePaymentCount.getMoreThan90Days());

        List<BarChartData> paymentData=new ArrayList<>();
        paymentData.add(dat1);
        paymentData.add(dat2);
        paymentData.add(dat3);
        paymentData.add(dat4);

        return new JRBeanCollectionDataSource(paymentData);


    }

    private int getLatePaymentCount(LatePaymentCount latePaymentCount) {
        return latePaymentCount.getDays1To30() + latePaymentCount.getDays31To60() + latePaymentCount.getDays61To90() +latePaymentCount.getMoreThan90Days();

    }

    private JRBeanCollectionDataSource getPieChartData(PDFData pdfData) {
        int closedCreditCards = pdfData.getClosedCreditCards();
        int closedLoans = pdfData.getClosedLoans();
        int openCreditCards = pdfData.getOpenCreditCards();
        int openLoans = pdfData.getOpenLoans();
        PieChartData data1 = new PieChartData("Closed credit cards", closedCreditCards);
        PieChartData data2 = new PieChartData("Closed loans", closedLoans);
        PieChartData data3 = new PieChartData("Open credit cards", openCreditCards);
        PieChartData data4 = new PieChartData("Open loans", openLoans);

        List<PieChartData> accountData = new ArrayList<>();
        accountData.add(data1);
        accountData.add(data2);
        accountData.add(data3);
        accountData.add(data4);
        return new JRBeanCollectionDataSource(accountData);
    }

    private int getTotalAccounts(PDFData pdfData) {

        return pdfData.getClosedCreditCards() + pdfData.getOpenCreditCards() + pdfData.getOpenLoans() + pdfData.getClosedLoans();
    }

    public IdentificationInfo getIdentificationInfo(List<IdentificationInfo> identificationInfoList) {
        IdentificationInfo identificationInfo=null;
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
