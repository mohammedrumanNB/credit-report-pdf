package com.transunion.pdf.service;

import com.transunion.pdf.constant.ApplicationConstant;
import com.transunion.pdf.dto.PDFData;
import com.transunion.pdf.enums.PdfVersion;
import com.transunion.pdf.util.CommonUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.pdf.JRPdfExporter;
import net.sf.jasperreports.pdf.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class PDFGenerator {
    @Autowired
    private MainReportService mainReportService;

    @Autowired
    private IndexReportService indexReportService;

    @Autowired
    private CibilSummaryReportService cibilSummaryReportService;

    @Autowired
    private PersonalInfoReportService personalInfoReportService;

    @Autowired
    private AccountInfoReportService accountInfoReportService;

    @Autowired
    private EnquiryInfoReportService enquiryInfoReportService;

    @Autowired
    private GlossaryReportService glossaryReportService;

    public byte[] generatePdf(PdfVersion pdfVersion, PDFData pdfData, boolean encryptPdf, String userPassword) throws JRException {

        JasperReport mainReport;
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("copyrightYear", pdfData.getCopyrightYear());

        switch (pdfVersion) {
            case PAID:
                mainReport = mainReportService.getMainReport(pdfVersion);

                //Prepare map of parameter for all sub report
                parameters.put("indexReport", indexReportService.getIndexReport(pdfVersion));
                parameters.put("indexParam", indexReportService.getIndexParam(pdfData));

                parameters.put("cibilSummaryReport", cibilSummaryReportService.getCibilSummaryReport(pdfVersion));
                parameters.put("cibilSummaryParam", cibilSummaryReportService.getCibilSummaryParam(pdfData));

                parameters.put("personalInformationReport", personalInfoReportService.getPersonalInfoReport(pdfVersion));
                parameters.put("personalInformationParam", personalInfoReportService.getPersonalInformationParam(pdfData));

                parameters.put("accountInformationReport", accountInfoReportService.getAccountInfoReport(pdfVersion));
                parameters.put("accountInformationParam", accountInfoReportService.getAccountInformationParam(pdfData, pdfVersion));

                parameters.put("enquiryInformationReport", enquiryInfoReportService.getEnquiryInfoReport(pdfVersion));
                parameters.put("enquiryInformationParam", enquiryInfoReportService.getEnquiryInformationParam(pdfData));

                parameters.put("glossaryReport1", glossaryReportService.getGlossaryReport1());
                parameters.put("glossaryReport2", glossaryReportService.getGlossaryReport2());
                parameters.put("glossaryParam", glossaryReportService.getGlossaryParam(pdfData));

                break;
            case STARTER:
                mainReport = mainReportService.getMainReport(pdfVersion);

                //Prepare map of parameter for all sub report
                parameters.put("indexReport", indexReportService.getIndexReport(pdfVersion));
                parameters.put("indexParam", indexReportService.getIndexParam(pdfData));

                parameters.put("cibilSummaryReport", cibilSummaryReportService.getCibilSummaryReport(pdfVersion));
                parameters.put("cibilSummaryParam", cibilSummaryReportService.getCibilSummaryParam(pdfData));

                parameters.put("personalInformationReport", personalInfoReportService.getPersonalInfoReport(pdfVersion));
                parameters.put("personalInformationParam", personalInfoReportService.getPersonalInformationParam(pdfData));

                parameters.put("accountInformationReport", accountInfoReportService.getAccountInfoReport(pdfVersion));
                parameters.put("accountInformationParam", accountInfoReportService.getAccountInformationParam(pdfData, pdfVersion));

                parameters.put("enquiryInformationReport", enquiryInfoReportService.getEnquiryInfoReport(pdfVersion));
                parameters.put("enquiryInformationParam", enquiryInfoReportService.getEnquiryInformationParam(pdfData));

                parameters.put("glossaryReport1", glossaryReportService.getGlossaryReport1());
                parameters.put("glossaryReport2", glossaryReportService.getGlossaryReport2());
                parameters.put("glossaryParam", glossaryReportService.getGlossaryParam(pdfData));

                break;
            case INDIRECT:
                mainReport = mainReportService.getMainReport(pdfVersion);

                //Prepare map of parameter for all sub report
                parameters.put("indexReport", indexReportService.getIndexReport(pdfVersion));
                parameters.put("indexParam", indexReportService.getIndexParam(pdfData));

                parameters.put("personalInformationReport", personalInfoReportService.getPersonalInfoReport(pdfVersion));
                parameters.put("personalInformationParam", personalInfoReportService.getPersonalInformationParam(pdfData));

                parameters.put("accountInformationReport", accountInfoReportService.getAccountInfoReport(pdfVersion));
                parameters.put("accountInformationParam", accountInfoReportService.getAccountInformationParam(pdfData, pdfVersion));

                parameters.put("enquiryInformationReport", enquiryInfoReportService.getEnquiryInfoReport(pdfVersion));
                parameters.put("enquiryInformationParam", enquiryInfoReportService.getEnquiryInformationParam(pdfData));

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


            byte[] pdfBytes = outputStream.toByteArray();

            savePdfToFile(pdfBytes, pdfVersion);

            return pdfBytes;

        } catch (JRException e) {
            // Handle JasperReport generation exception, possibly logging or rethrowing a custom exception
            throw new JRException("Failed to generate PDF for version: " + pdfVersion, e);
        } catch (IOException e) {
            throw new RuntimeException(e);
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

    private void savePdfToFile(byte[] pdfBytes, PdfVersion pdfVersion) throws IOException {
        UUID uuid = UUID.randomUUID();
        // Determine the file path in the resources directory
        String fileName = pdfVersion.name().toLowerCase() + "_report_" + uuid.toString().substring(0, 3) + ".pdf";
        String resourcePath = "src/main/resources/" + fileName;

        // Create the file
        File pdfFile = new File(resourcePath);
        try (FileOutputStream fos = new FileOutputStream(pdfFile)) {
            fos.write(pdfBytes);
        }
    }

}
