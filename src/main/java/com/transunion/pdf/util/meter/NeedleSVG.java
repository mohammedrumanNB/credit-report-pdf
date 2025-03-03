package com.transunion.pdf.util.meter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class NeedleSVG {

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     *
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g) {
        Shape shape = null;

        float origAlpha = 1.0f;
        Composite origComposite = g.getComposite();
        if (origComposite instanceof AlphaComposite) {
            AlphaComposite origAlphaComposite = (AlphaComposite)origComposite;
            if (origAlphaComposite.getRule() == AlphaComposite.SRC_OVER) {
                origAlpha = origAlphaComposite.getAlpha();
            }
        }

        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<AffineTransform>();
        AffineTransform transform = new AffineTransform();
        transform.translate(46, 46);
        transformations.add(transform);



        //

        // _0

        // _0_0
        shape = new GeneralPath();

        ((GeneralPath) shape).moveTo(33.8212, 16.8486);
        ((GeneralPath) shape).curveTo(33.8212, 12.4283, 32.0653, 8.18895, 28.9396, 5.06328);
        ((GeneralPath) shape).curveTo(25.8139, 1.93762, 21.5746, 0.181641, 17.1543, 0.181641);
        ((GeneralPath) shape).curveTo(12.7339, 0.18164, 8.49461, 1.93762, 5.36895, 5.06328);
        ((GeneralPath) shape).curveTo(2.24329, 8.18894, 0.487305, 12.4283, 0.487305, 16.8486);
        ((GeneralPath) shape).lineTo(17.1543, 16.8486);
        ((GeneralPath) shape).lineTo(33.8212, 16.8486);
        ((GeneralPath) shape).closePath();

        g.setPaint(new Color(0xF2F2F2));
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
        return 1;
    }

    /**
     * Returns the width of the bounding box of the original SVG image.
     *
     * @return The width of the bounding box of the original SVG image.
     */
    public static int getOrigWidth() {
        return 34;
    }

    /**
     * Returns the height of the bounding box of the original SVG image.
     *
     * @return The height of the bounding box of the original SVG image.
     */
    public static int getOrigHeight() {
        return 17;
    }
}
