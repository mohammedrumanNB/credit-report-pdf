package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.EnquiryInformation;
import com.transunion.pdf.dto.EnquiryTableInformation;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.FileNotFoundException;
import com.transunion.pdf.model.EnquiryInfo;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Service
public class EnquiryInfoReportService {
    public JasperReport getEnquiryInfoReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case INDIRECT:
                filePath = ApplicationConstant.INDIRECT_ENQUIRYINFO_JASPER_PATH;
                break;
            case NH:
                filePath = ApplicationConstant.NH_ENQUIRYINFO_JASPER_PATH;
                break;
            case STARTER:
                filePath = ApplicationConstant.STARTER_ENQUIRYINFO_JASPER_PATH;
                break;

            case PAID:
                filePath = ApplicationConstant.PAID_ENQUIRYINFO_JASPER_PATH;
                break;

            default:
                throw new IllegalArgumentException("Unsupported PDF version: " + pdfVersion);
        }
        return (JasperReport) JRLoader.loadObject(new File(filePath));

    }

    public EnquiryInformation getEnquiryInformationParam(PDFData pdfData) {
        EnquiryInformation enquiryInformation = new EnquiryInformation();
        enquiryInformation.setControlNumber(pdfData.getControlNumber());
        enquiryInformation.setReportGeneratedDate(pdfData.getReportGeneratedDate());

        List<EnquiryInfo> enquiryInfoList = pdfData.getEnquiryInfoList();

        //Validate EnquiryInfo List
        if (enquiryInfoList == null || enquiryInfoList.isEmpty()) {
            enquiryInformation.setEnquiryInfoPresent(false);
            enquiryInformation.setTotalEnquiries(0);
        } else {
            enquiryInformation.setEnquiryInfoPresent(true);
            enquiryInformation.setTotalEnquiries(getTotalEnquiries(enquiryInfoList));
            enquiryInformation.setEnquiryInfoDataSource(getEnquiryInfoDataSource(enquiryInfoList));
            enquiryInformation.setEnquiryTableReport(getEnquiryTableReport());
        }

        return enquiryInformation;
    }

    private int getTotalEnquiries(List<EnquiryInfo> enquiryInfoList) {
        int count = 0;
        for (EnquiryInfo enquiryInfo : enquiryInfoList) {
            if (enquiryInfo.getEnquiryDetailsList() == null || enquiryInfo.getEnquiryDetailsList().isEmpty()) {
                continue;
            }
            count += enquiryInfo.getEnquiryDetailsList().size();
        }
        return count;
    }

    private JRBeanCollectionDataSource getEnquiryInfoDataSource(List<EnquiryInfo> enquiryInfoList) {
        List<EnquiryTableInformation> enquiryTableInformationList = new ArrayList<>();
        for (EnquiryInfo enquiryInfo : enquiryInfoList) {
            enquiryTableInformationList.add(getEnquiryTableInformation(enquiryInfo));
        }
        return new JRBeanCollectionDataSource(enquiryTableInformationList);
    }

    private EnquiryTableInformation getEnquiryTableInformation(EnquiryInfo enquiryInfo) {
        EnquiryTableInformation enquiryTableInformation = new EnquiryTableInformation();
        enquiryTableInformation.setEnquiryYear(enquiryInfo.getEnquiryYear());
        if (enquiryInfo.getEnquiryDetailsList() != null) {
            enquiryTableInformation.setEnquiryTableDetailsPresent(true);
            enquiryTableInformation.setEnquiryTableDataSource(new JRBeanCollectionDataSource(enquiryInfo.getEnquiryDetailsList()));
            enquiryTableInformation.setEnquiryCount(String.valueOf(enquiryInfo.getEnquiryDetailsList().size()));
        } else {
            enquiryTableInformation.setEnquiryTableDetailsPresent(false);
            enquiryTableInformation.setEnquiryCount("0");
        }
        return enquiryTableInformation;

    }

    private JasperReport getEnquiryTableReport() {
        try {
            return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.ENQUIRY_TABLE_JASPER_PATH));

        } catch (JRException e) {
            // Check if the cause of JRException is a FileNotFoundException
            if (e.getCause() instanceof java.io.FileNotFoundException) {
                throw new FileNotFoundException(1029, "Enquiry Table Jrxml / Jasper Report Not Found at this directory: "
                        + ApplicationConstant.ENQUIRY_TABLE_JASPER_PATH);
            } else {
                // Re-throw JRException as a runtime exception if the cause is different
                throw new RuntimeException("An error occurred while compiling the address report.", e);
            }
        }
    }
}
