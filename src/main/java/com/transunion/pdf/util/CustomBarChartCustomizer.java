package com.transunion.pdf.util;

import net.sf.jasperreports.charts.JRChart;
import net.sf.jasperreports.charts.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.ui.TextAnchor;

import java.awt.Color;
import java.awt.Font;

public class CustomBarChartCustomizer implements JRChartCustomizer {

    @Override
    public void customize(JFreeChart chart, JRChart chartElement) {
        if (chart.getPlot() instanceof CategoryPlot) {
            CategoryPlot plot = (CategoryPlot) chart.getPlot();

            // Bar Renderer
            BarRenderer renderer = (BarRenderer) plot.getRenderer();

            // Set bar color
            renderer.setSeriesPaint(0, Color.decode("#FDE866")); // Yellow

            // Fixed bar height (reduce bar width for horizontal bars)
            renderer.setMaximumBarWidth(0.1); // Adjust as needed

            // Disable bar outline and shadow
            renderer.setDrawBarOutline(false);
            renderer.setShadowVisible(false);


            // Enable item labels
            renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
            renderer.setDefaultItemLabelsVisible(true);
            renderer.setDefaultItemLabelFont(new Font("Intro - semi bold", Font.BOLD, 7));
            renderer.setDefaultItemLabelPaint(Color.BLACK);



            // Customize category axis (left labels)
            CategoryAxis domainAxis = plot.getDomainAxis();
            domainAxis.setTickLabelFont(new Font("Intro", Font.PLAIN, 6));
            domainAxis.setTickLabelPaint(Color.decode("#333333"));

            // Adjust category label positions to align vertically at the bar center
            domainAxis.setCategoryLabelPositions(CategoryLabelPositions.STANDARD); // Default
            domainAxis.setLowerMargin(0.05); // Add small margins to avoid labels touching the plot edges
            domainAxis.setUpperMargin(0.05);

            // Customize value axis (bottom axis, optional)
            ValueAxis rangeAxis = plot.getRangeAxis();
            rangeAxis.setTickLabelsVisible(false); // Hide range axis labels
            rangeAxis.setAxisLineVisible(false); // Hide the axis line

            // Gridline customization
            plot.setRangeGridlinesVisible(false); // Remove gridlines
        } else {
            throw new IllegalArgumentException("Chart plot is not a CategoryPlot.");
        }
    }
}

