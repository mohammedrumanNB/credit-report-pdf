package com.transunion.pdf.util.meter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

/**
 * This class has been automatically generated using
 * <a href="http://ebourg.github.io/flamingo-svg-transcoder/">Flamingo SVG transcoder</a>.
 */
public class NeedleSVG {

    private NeedleSVG() {
    }

    /**
     * Paints the transcoded SVG image on the specified graphics context. You
     * can install a custom transformation on the graphics context to scale the
     * image.
     *
     * @param g Graphics context.
     */
    public static void paint(Graphics2D g) {
        Shape shape = null;


        java.util.LinkedList<AffineTransform> transformations = new java.util.LinkedList<>();
        AffineTransform transform = new AffineTransform();
        transform.translate(46, 46);
        transformations.add(transform);


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

}
