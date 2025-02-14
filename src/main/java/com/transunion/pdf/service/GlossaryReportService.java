package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.Glossary;
import com.transunion.pdf.dto.PDFData;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class GlossaryReportService {

    public JasperReport getGlossaryReport1() throws JRException {
        return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.GLOSSARY_1_JASPER_PATH));
    }

    public JasperReport getGlossaryReport2() throws JRException {
        return (JasperReport) JRLoader.loadObject(new File(ApplicationConstant.GLOSSARY_2_JASPER_PATH));
    }

    public Glossary getGlossaryParam(PDFData pdfData) {
        Glossary glossary = new Glossary();
        glossary.setControlNumber(pdfData.getControlNumber());
        glossary.setReportGeneratedDate(pdfData.getReportGeneratedDate());
        return glossary;
    }
}
