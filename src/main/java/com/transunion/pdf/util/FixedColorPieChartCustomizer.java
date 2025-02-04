package com.transunion.pdf.util;

import net.sf.jasperreports.charts.JRChart;
import net.sf.jasperreports.charts.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.Plot;
import java.awt.Color;

public class FixedColorPieChartCustomizer implements JRChartCustomizer {

    @Override
    public void customize(JFreeChart chart, JRChart chartElement) {
        Plot plot = chart.getPlot();

        // Ensure the plot is a PiePlot
        if (plot instanceof PiePlot) {
            PiePlot piePlot = (PiePlot) plot;

            // Remove the shadow
            piePlot.setShadowPaint(null);

            // Remove the border of the plot area
            piePlot.setOutlinePaint(null);

            // Remove the border around the chart
            chart.setBorderVisible(false);

            // Set fixed colors for each category using HEX colors
            piePlot.setSectionPaint("Closed credit cards", Color.decode("#7E79D0")); // Color 1
            piePlot.setSectionPaint("Closed loans", Color.decode("#FDE866"));        // Color 2
            piePlot.setSectionPaint("Open credit cards", Color.decode("#99DBEA"));   // Color 3
            piePlot.setSectionPaint("Open loans", Color.decode("#67D995"));          // Color 4
        } else {
            throw new IllegalArgumentException("The chart plot is not a PiePlot.");
        }
    }
}
