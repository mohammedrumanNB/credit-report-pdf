package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.enums.PdfVersion;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MainReportService {

    JasperReport getMainReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case INDIRECT:
                filePath = ApplicationConstant.INDIRECT_MAIN_JASPER_PATH;
                break;
            case STARTER:
                filePath = ApplicationConstant.STARTER_MAIN_JASPER_PATH;
                break;
            case PAID:
                filePath = ApplicationConstant.PAID_MAIN_JASPER_PATH;
                break;
            default:
                throw new IllegalArgumentException("Unsupported PDF version: " + pdfVersion);
        }
        return (JasperReport) JRLoader.loadObject(new File(filePath));

    }
}
