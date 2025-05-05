package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.util.CommonUtil;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.pdf.JRPdfExporter;
import net.sf.jasperreports.pdf.SimplePdfExporterConfiguration;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PDFGenerator {

    public static final String INDEX_REPORT = "indexReport";
    public static final String INDEX_PARAM = "indexParam";
    public static final String CIBIL_SUMMARY_REPORT = "cibilSummaryReport";
    public static final String CIBIL_SUMMARY_PARAM = "cibilSummaryParam";
    public static final String PERSONAL_INFORMATION_REPORT = "personalInformationReport";
    public static final String PERSONAL_INFORMATION_PARAM = "personalInformationParam";
    public static final String ACCOUNT_INFORMATION_REPORT = "accountInformationReport";
    public static final String ACCOUNT_INFORMATION_PARAM = "accountInformationParam";
    public static final String ENQUIRY_INFORMATION_REPORT = "enquiryInformationReport";
    public static final String ENQUIRY_INFORMATION_PARAM = "enquiryInformationParam";
    public static final String GLOSSARY_REPORT_1 = "glossaryReport1";
    public static final String GLOSSARY_REPORT_2 = "glossaryReport2";
    public static final String GLOSSARY_PARAM = "glossaryParam";

    private final MainReportService mainReportService;


    private final IndexReportService indexReportService;


    private final CibilSummaryReportService cibilSummaryReportService;


    private final PersonalInfoReportService personalInfoReportService;


    private final AccountInfoReportService accountInfoReportService;


    private final EnquiryInfoReportService enquiryInfoReportService;


    private final GlossaryReportService glossaryReportService;

    public byte[] generatePdf(PdfVersion pdfVersion, PDFData pdfData, boolean encryptPdf, String userPassword) throws JRException {

        JasperReport mainReport;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("copyrightYear", pdfData.getCopyrightYear());

        switch (pdfVersion) {
            case PAID:
                mainReport = mainReportService.getMainReport(pdfVersion);

                parameters.put(INDEX_REPORT, indexReportService.getIndexReport(pdfVersion));
                parameters.put(INDEX_PARAM, indexReportService.getIndexParam(pdfData));

                parameters.put(CIBIL_SUMMARY_REPORT, cibilSummaryReportService.getCibilSummaryReport(pdfVersion));
                parameters.put(CIBIL_SUMMARY_PARAM, cibilSummaryReportService.getCibilSummaryParam(pdfData));

                parameters.put(PERSONAL_INFORMATION_REPORT, personalInfoReportService.getPersonalInfoReport(pdfVersion));
                parameters.put(PERSONAL_INFORMATION_PARAM, personalInfoReportService.getPersonalInformationParam(pdfData));

                parameters.put(ACCOUNT_INFORMATION_REPORT, accountInfoReportService.getAccountInfoReport(pdfVersion));
                parameters.put(ACCOUNT_INFORMATION_PARAM, accountInfoReportService.getAccountInformationParam(pdfData, pdfVersion));

                parameters.put(ENQUIRY_INFORMATION_REPORT, enquiryInfoReportService.getEnquiryInfoReport(pdfVersion));
                parameters.put(ENQUIRY_INFORMATION_PARAM, enquiryInfoReportService.getEnquiryInformationParam(pdfData));

                parameters.put(GLOSSARY_REPORT_1, glossaryReportService.getGlossaryReport1());
                parameters.put(GLOSSARY_REPORT_2, glossaryReportService.getGlossaryReport2());
                parameters.put(GLOSSARY_PARAM, glossaryReportService.getGlossaryParam(pdfData));

                break;
            case STARTER:
                mainReport = mainReportService.getMainReport(pdfVersion);

                parameters.put(INDEX_REPORT, indexReportService.getIndexReport(pdfVersion));
                parameters.put(INDEX_PARAM, indexReportService.getIndexParam(pdfData));

                parameters.put(CIBIL_SUMMARY_REPORT, cibilSummaryReportService.getCibilSummaryReport(pdfVersion));
                parameters.put(CIBIL_SUMMARY_PARAM, cibilSummaryReportService.getCibilSummaryParam(pdfData));

                parameters.put(PERSONAL_INFORMATION_REPORT, personalInfoReportService.getPersonalInfoReport(pdfVersion));
                parameters.put(PERSONAL_INFORMATION_PARAM, personalInfoReportService.getPersonalInformationParam(pdfData));

                parameters.put(ACCOUNT_INFORMATION_REPORT, accountInfoReportService.getAccountInfoReport(pdfVersion));
                parameters.put(ACCOUNT_INFORMATION_PARAM, accountInfoReportService.getAccountInformationParam(pdfData, pdfVersion));

                parameters.put(ENQUIRY_INFORMATION_REPORT, enquiryInfoReportService.getEnquiryInfoReport(pdfVersion));
                parameters.put(ENQUIRY_INFORMATION_PARAM, enquiryInfoReportService.getEnquiryInformationParam(pdfData));

                parameters.put(GLOSSARY_REPORT_1, glossaryReportService.getGlossaryReport1());
                parameters.put(GLOSSARY_REPORT_2, glossaryReportService.getGlossaryReport2());
                parameters.put(GLOSSARY_PARAM, glossaryReportService.getGlossaryParam(pdfData));

                break;
            case INDIRECT:
                mainReport = mainReportService.getMainReport(pdfVersion);

                //Prepare map of parameter for all sub report
                parameters.put(INDEX_REPORT, indexReportService.getIndexReport(pdfVersion));
                parameters.put(INDEX_PARAM, indexReportService.getIndexParam(pdfData));

                parameters.put(PERSONAL_INFORMATION_REPORT, personalInfoReportService.getPersonalInfoReport(pdfVersion));
                parameters.put(PERSONAL_INFORMATION_PARAM, personalInfoReportService.getPersonalInformationParam(pdfData));

                parameters.put(ACCOUNT_INFORMATION_REPORT, accountInfoReportService.getAccountInfoReport(pdfVersion));
                parameters.put(ACCOUNT_INFORMATION_PARAM, accountInfoReportService.getAccountInformationParam(pdfData, pdfVersion));

                parameters.put(ENQUIRY_INFORMATION_REPORT, enquiryInfoReportService.getEnquiryInfoReport(pdfVersion));
                parameters.put(ENQUIRY_INFORMATION_PARAM, enquiryInfoReportService.getEnquiryInformationParam(pdfData));

                break;
            default:
                throw new IllegalArgumentException("Unsupported PDF version: " + pdfVersion);
        }

        //  Generate the JasperPrint and export it as a PDF byte array
        try {
            JasperPrint jasperPrintDraft = JasperFillManager.fillReport(mainReport, parameters, new JREmptyDataSource());

            //Set Page Numbers
            JasperPrint jasperPrint = setPageNumbers(jasperPrintDraft);

            // Set up the PDF exporter with password protection
            JRPdfExporter exporter = new JRPdfExporter();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));

            // Configure PDF export settings for encryption and metadata
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            configuration.setEncrypted(true);
            configuration.set128BitKey(true);
            if (encryptPdf) {
                CommonUtil.validateUserPassword(userPassword);
                configuration.setUserPassword(userPassword);
            }

            // Set PDF metadata
            configuration.setMetadataAuthor(ApplicationConstant.PDF_AUTHOR);
            configuration.setMetadataTitle(ApplicationConstant.PDF_TITLE);
            configuration.setMetadataSubject(ApplicationConstant.PDF_SUBJECT);
            configuration.setMetadataKeywords(ApplicationConstant.PDF_KEYWORDS);
            configuration.setMetadataProducer(ApplicationConstant.PDF_PRODUCER);
            configuration.setMetadataCreator(ApplicationConstant.PDF_CREATOR);
            configuration.setTagged(false);
            configuration.setCompressed(true);

            exporter.setConfiguration(configuration);

            // Export the report with password protection and metadata
            exporter.exportReport();


            return outputStream.toByteArray();

        } catch (JRException e) {
            // Handle JasperReport generation exception, possibly logging or rethrowing a custom exception
            throw new JRException("Failed to generate PDF for version: " + pdfVersion, e);
        }
    }


    private JasperPrint setPageNumbers(JasperPrint jasperPrint) {
        List<String> subreportMarkers = List.of("CSP", "PIP", "AIP", "EIP", "GIP");

        // Get page numbers of each subreport
        Map<String, Integer> subreportPageNumbers = Indexer.getSubreportPageNumbers(jasperPrint, subreportMarkers);

        // Access the first page of the JasperPrint
        if (!jasperPrint.getPages().isEmpty()) {
            JRPrintPage firstPage = jasperPrint.getPages().get(0);

            // Iterate over the elements on the first page
            for (JRPrintElement element : firstPage.getElements()) {
                // Check if the element is a text element
                if (element instanceof JRPrintText) {
                    JRPrintText textElement = (JRPrintText) element;
                    String text = (String) textElement.getValue();

                    // Check if the text matches any subreport marker
                    if (text != null && subreportPageNumbers.containsKey(text)) {
                        // Replace the text with the page number of the subreport
                        Integer pageNumber = subreportPageNumbers.get(text);
                        textElement.setText(String.format("%02d", pageNumber));
                    }
                }
            }
        }

        return jasperPrint;
    }


}
