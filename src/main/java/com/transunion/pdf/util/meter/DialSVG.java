package com.transunion.pdf.util.meter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class DialSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     *
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g) {
        Shape shape = null;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        float origAlpha = 1.0f;
        Composite origComposite = g.getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = (AlphaComposite) origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }

        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<AffineTransform>();

        //

        // _0

        // _0_0
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(0.598633, 63.7266);
        ((GeneralPath) shape).curveTo(0.598634, 46.8253, 7.31267, 30.6162, 19.2637, 18.6651);
        ((GeneralPath) shape).curveTo(31.2148, 6.71404, 47.4239, -2.01547E-7, 64.3253, 0.0);
        ((GeneralPath) shape).lineTo(64.3253, 6.37266);
        ((GeneralPath) shape).curveTo(49.114, 6.37266, 34.5258, 12.4153, 23.7699, 23.1712);
        ((GeneralPath) shape).curveTo(13.0139, 33.9272, 6.9713, 48.5154, 6.9713, 63.7266);
        ((GeneralPath) shape).lineTo(0.598633, 63.7266);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xE15825));
        g.fill(shape);

        // _0_1
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(65.6283, 0.0136719);
        ((GeneralPath) shape).curveTo(73.524, 0.175127, 81.3208, 1.80276, 88.6216, 4.81364);
        ((GeneralPath) shape).lineTo(86.192, 10.705);
        ((GeneralPath) shape).curveTo(79.6213, 7.99518, 72.6041, 6.53031, 65.498, 6.385);
        ((GeneralPath) shape).lineTo(65.6283, 0.0136719);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xF18200));
        g.fill(shape);

        // _0_2
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(89.7972, 5.3125);
        ((GeneralPath) shape).curveTo(97.0362, 8.46916, 103.625, 12.9438, 109.229, 18.5086);
        ((GeneralPath) shape).lineTo(104.739, 23.0304);
        ((GeneralPath) shape).curveTo(99.6953, 18.0221, 93.7651, 13.9949, 87.25, 11.1539);
        ((GeneralPath) shape).lineTo(89.7972, 5.3125);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xFCD800));
        g.fill(shape);

        // _0_3
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(110.088, 19.3789);
        ((GeneralPath) shape).curveTo(115.584, 25.0501, 119.978, 31.6934, 123.046, 38.9704);
        ((GeneralPath) shape).lineTo(117.174, 41.4461);
        ((GeneralPath) shape).curveTo(114.413, 34.8968, 110.458, 28.9178, 105.512, 23.8138);
        ((GeneralPath) shape).lineTo(110.088, 19.3789);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xA9D161));
        g.fill(shape);

        // _0_4
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(123.614, 40.3594);
        ((GeneralPath) shape).curveTo(126.51, 47.7066, 128.015, 55.5281, 128.052, 63.4253);
        ((GeneralPath) shape).lineTo(121.679, 63.4553);
        ((GeneralPath) shape).curveTo(121.646, 56.3479, 120.292, 49.3085, 117.686, 42.696);
        ((GeneralPath) shape).lineTo(123.614, 40.3594);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0x009900));
        g.fill(shape);

    }

    /**
     * Returns the X of the bounding box of the original SVG image.
     *
     * @return The X of the bounding box of the original SVG image.
     */
    public static int getOrigX() {
        return 1;
    }

    /**
     * Returns the Y of the bounding box of the original SVG image.
     *
     * @return The Y of the bounding box of the original SVG image.
     */
    public static int getOrigY() {
        return 0;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     *
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 128;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     *
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 64;
    }
}
