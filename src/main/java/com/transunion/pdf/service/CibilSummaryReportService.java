package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.dto.Summary;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.SummaryInfo;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CibilSummaryReportService {
    public JasperReport getCibilSummaryReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case STARTER:
                filePath = ApplicationConstant.STARTER_SUMMARY_JASPER_PATH;
                break;
            case PAID:
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

        SummaryInfo summaryInfo = pdfData.getSummaryInfo();

        //Get Total Accounts
        int totalAccounts = getTotalAccounts(summaryInfo);

        //Assign Values to Summary Object
        summary.setCibilScore(pdfData.getCibilScore());
        summary.setName(summaryInfo.getName());
        summary.setDob(summaryInfo.getDob());
        summary.setGender(summaryInfo.getGender());
        summary.setIdType(summaryInfo.getSummaryIdType()==null?"-":summaryInfo.getSummaryIdType().getCode());
        summary.setIdNumber(summaryInfo.getSummaryIdNumber());
        summary.setEmail(summaryInfo.getEmail());
        summary.setMobileNumber(summaryInfo.getMobileNumber());
        summary.setAddress(summaryInfo.getAddress());

        summary.setEnquiries12(summaryInfo.getEnquiries12());
        summary.setEnquiries24(summaryInfo.getEnquiries24());
        summary.setEnquiries36(summaryInfo.getEnquiries36());

        summary.setTotalDisputes(summaryInfo.getTotalAccountsUnderDisputes());

        //Format the monetary values to Indian Format
        summary.setCurrentBalance(CommonUtil.formatIndianCurrency(summaryInfo.getCurrentBalance()));
        summary.setAmountOverdue(CommonUtil.formatIndianCurrency(summaryInfo.getAmountOverdue()));


        summary.setTotalAccounts(totalAccounts);
        summary.setClosedCreditCards(summaryInfo.getClosedCreditCards());
        summary.setClosedLoans(summaryInfo.getClosedLoans());
        summary.setOpenCreditCards(summaryInfo.getOpenCreditCards());
        summary.setOpenLoans(summaryInfo.getOpenLoans());

        summary.setLatePayment12(summaryInfo.getLatePayment12());
        summary.setLatePayment36(summaryInfo.getLatePayment36());

        summary.setLatePaymentRemarkCount12(summaryInfo.getLatePaymentRemarkCount12());

        summary.setLatePaymentRemarkCount36(summaryInfo.getLatePaymentRemarkCount36());

        return summary;
    }

    private int getTotalAccounts(SummaryInfo summaryInfo) {

        return summaryInfo.getOpenCreditCards() + summaryInfo.getOpenLoans();
    }

}
