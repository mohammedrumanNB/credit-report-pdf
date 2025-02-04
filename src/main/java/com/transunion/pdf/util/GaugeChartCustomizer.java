package com.transunion.pdf.util;

import java.awt.Color;

import net.sf.jasperreports.charts.JRChart;
import net.sf.jasperreports.charts.JRChartCustomizer;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.MeterInterval;


public class GaugeChartCustomizer implements JRChartCustomizer {

    @Override
    public void customize(JFreeChart chart, JRChart jasperChart) {
        if (chart.getPlot() instanceof MeterPlot) {
            MeterPlot plot = (MeterPlot) chart.getPlot();

            // Configure the meter range (full span: 300 to 900)
            plot.setRange(new org.jfree.data.Range(300, 900));

            // Add intervals to color the outer arc
            plot.addInterval(new MeterInterval("Red", new org.jfree.data.Range(300, 500), null, null, Color.RED));
            plot.addInterval(new MeterInterval("Orange", new org.jfree.data.Range(501, 600), null, null, Color.ORANGE));
            plot.addInterval(new MeterInterval("Yellow", new org.jfree.data.Range(601, 700), null, null, Color.YELLOW));
            plot.addInterval(new MeterInterval("Light Green", new org.jfree.data.Range(701, 800), null, null, new Color(144, 238, 144)));
            plot.addInterval(new MeterInterval("Dark Green", new org.jfree.data.Range(801, 900), null, null, Color.GREEN));

            // Set the dial shape to a semi-circle
            plot.setDialShape(DialShape.PIE);

            // Remove ticks and labels
            plot.setTickLabelsVisible(false);
            plot.setTickPaint(new Color(0, 0, 0, 0)); // Fully transparent ticks

            // Set a clean white background for the dial face
            plot.setDialBackgroundPaint(Color.WHITE);

            // Remove the dial outline
            plot.setDialOutlinePaint(null);

            // Remove the plot's outline
            plot.setOutlinePaint(null);

            // Set the pointer color to black
            plot.setNeedlePaint(Color.BLACK);
          plot.setDrawBorder(false);
          plot.clearIntervals();

            // Customize the meter angle for better design
            plot.setMeterAngle(180); // Semi-circle
        }
    }
}
