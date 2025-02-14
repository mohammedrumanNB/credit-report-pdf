package com.transunion.pdf.service;

import net.sf.jasperreports.engine.JRPrintElement;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JRPrintText;
import net.sf.jasperreports.engine.JasperPrint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Indexer {

    public static Map<String, Integer> getSubreportPageNumbers(JasperPrint jasperPrint, List<String> subreportMarkers) {
        Map<String, Integer> subreportPageNumbers = new HashMap<>();

        // Iterate over each page in the JasperPrint
        for (int pageIndex = 1; pageIndex < jasperPrint.getPages().size(); pageIndex++) {
            JRPrintPage page = jasperPrint.getPages().get(pageIndex);

            // Check each element on the page to see if it contains a subreport marker
            for (JRPrintElement element : page.getElements()) {
                if (element instanceof JRPrintText) {
                    JRPrintText textElement = (JRPrintText) element;
                    String text = (String) textElement.getValue();

                    // Check if the text matches any subreport marker
                    if (text != null && subreportMarkers.contains(text) && !subreportPageNumbers.containsKey(text)) {
                        // Store the page number (1-indexed) for the subreport marker
                        subreportPageNumbers.put(text, pageIndex + 1);
                        break; // Move to the next page after finding the marker
                    }
                }
            }
        }

        return subreportPageNumbers;
    }
}
