package com.transunion.pdf.util.chartutil;


import com.transunion.pdf.util.meter.ArrowSVG;
import com.transunion.pdf.util.meter.DialSVG;
import com.transunion.pdf.util.meter.NeedleSVG;
import org.jfree.chart.plot.DialShape;
import org.jfree.chart.plot.MeterPlot;
import org.jfree.chart.plot.PlotRenderingInfo;
import org.jfree.chart.plot.PlotState;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.general.ValueDataset;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CustomMeterPlot extends MeterPlot {

    private DialShape shape;

    public CustomMeterPlot() {
        super();
        this.shape = DialShape.CIRCLE;
    }

    public CustomMeterPlot(ValueDataset dataset) {
        super(dataset);
        this.shape = DialShape.CIRCLE; // Initialize the shape
    }

    @Override
    public void draw(Graphics2D g2, Rectangle2D area, Point2D anchor, PlotState parentState, PlotRenderingInfo info) {
        setUnits(""); // Ensure no units are displayed

        if (info != null) {
            info.setPlotArea(area);
        }

        RectangleInsets insets = new RectangleInsets(0, 0, 0, 0);
        insets.trim(area);

        // Ensure the area is properly set
        area.setRect(area.getX(), area.getY(), area.getWidth(), area.getHeight());

        // Draw background if required
        if (getDrawBorder()) {
            drawBackground(g2, area);
        }

        // Define the meter area
        Rectangle2D meterArea = new Rectangle2D.Double(0, 0, 128, 64);

        // Retrieve dataset value
        ValueDataset data = getDataset();
        if (data != null) {
            Number n = data.getValue();
            if (n != null) {
                double value = n.doubleValue();

                // Save original transformations and clipping
                AffineTransform originalTransform = g2.getTransform();
                Shape savedClip = g2.getClip();
                Composite originalComposite = g2.getComposite();

                try {
                    g2.clip(meterArea); // Clip only within the meter area
                    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, getForegroundAlpha()));

                    // Draw the dial
                    DialSVG.paint(g2);

                    // Restore transform and draw the needle
                    g2.setTransform(originalTransform);
                    g2.translate(46.89, 46.18);
                    NeedleSVG.paint(g2);

                    // If value is in range, calculate and rotate the arrow
                    if (getRange().contains(value)) {
                        double valueAngle = scoreToAngle(value);

                        g2.setTransform(originalTransform);
                        g2.translate(29.42, 57);
                        ArrowSVG.paint(g2, valueAngle);
                    }

                } catch (Exception e) {
                    Logger.getLogger(CustomMeterPlot.class.getName()).log(Level.SEVERE, "Error in CustomMeterPlot rendering", e);
                } finally {
                    // Restore graphics state
                    g2.setTransform(originalTransform);
                    g2.setClip(savedClip);
                    g2.setComposite(originalComposite);
                }
            }
        }

        // Draw outline if required
        if (getDrawBorder()) {
            drawOutline(g2, area);
        }
    }

    private double scoreToAngle(double value) {
        if (value < 300 || value > 900) {
            throw new IllegalArgumentException("Value out of range");
        }

        if (value <= 722) {
            return calculateAngle(value, 300, 722, 0, 90);
        } else if (value <= 747) {
            return calculateAngle(value, 723, 747, 91, 114);
        } else if (value <= 764) {
            return calculateAngle(value, 748, 764, 115, 139);
        } else if (value <= 777) {
            return calculateAngle(value, 765, 777, 140, 159);
        } else {
            return calculateAngle(value, 778, 900, 160, 180);
        }
    }

    private double calculateAngle(double score, int startScore, int endScore, int startDegree, int endDegree) {
        return startDegree + ((score - startScore) / (double) (endScore - startScore) * (endDegree - startDegree));
    }
}


