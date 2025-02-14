package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.exception.InvalidDataException;
import com.transunion.pdf.model.IndexInfo;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class IndexReportService {
    public JasperReport getIndexReport(PdfVersion pdfVersion) throws JRException {
        String filePath;
        switch (pdfVersion) {
            case INDIRECT:
                //Get Index Report jrxml and compile it
                filePath = ApplicationConstant.INDIRECT_INDEX_JASPER_PATH;
                break;
            case NH:
                //Get Index Report jrxml and compile it
                filePath = ApplicationConstant.NH_INDEX_JASPER_PATH;
                break;
            default:
                throw new InvalidDataException(1016, "Unsupported PDF version: " + pdfVersion);
        }
        return (JasperReport) JRLoader.loadObject(new File(filePath));


    }

    public IndexInfo getIndexParam(PDFData pdfData) {
        CommonUtil.validateIndexInfo(pdfData);
        return new IndexInfo(pdfData.getCibilScore(), pdfData.getReportGeneratedDate(), pdfData.getCibilScoreDate(), pdfData.getControlNumber(), pdfData.getPersonalInfo().getFullName());
    }
}
