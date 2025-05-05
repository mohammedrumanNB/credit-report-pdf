package com.transunion.pdf.util.meter;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;

public class ArrowSVG {


    private ArrowSVG() {
    }
    /**
     * Paints the transcoded SVG image on the specified graphics context.
     * Rotates the arrow dynamically to the given angle.
     *
     * @param g     Graphics2D context.
     * @param angle Rotation angle in degrees.
     */

    public static void paint(Graphics2D g, double angle) {
        Shape shape = null;

        // Define the arrow shape (as before)
        shape = new GeneralPath();
        ((GeneralPath) shape).moveTo(1.32404, 3.2654);
        ((GeneralPath) shape).lineTo(35.0688, 4.49732);
        ((GeneralPath) shape).curveTo(36.3283, 4.5433, 37.3595, 3.52019, 37.34, 2.244);
        ((GeneralPath) shape).curveTo(37.3204, 0.967803, 36.2579, -0.0502004, 34.9999, 0.00191545);
        ((GeneralPath) shape).lineTo(1.29542, 1.39819);
        ((GeneralPath) shape).curveTo(0.800542, 1.41869, 0.415258, 1.83193, 0.422953, 2.33396);
        ((GeneralPath) shape).curveTo(0.430649, 2.83598, 0.828575, 3.24732, 1.32404, 3.2654);
        ((GeneralPath) shape).closePath();

        // Get the rightmost point (pivot point)
        double pivotX = 36;  // Approximate rightmost X from shape 37px
        double pivotY = 0.0;   // Approximate middle Y of the shape 2px

        // Apply transformations: move to pivot, rotate, move back
        AffineTransform transform = new AffineTransform();
        transform.translate(pivotX, pivotY);    // Move pivot to (0,0)
        transform.rotate(Math.toRadians(angle)); // Rotate around pivot
        transform.translate(-pivotX, -pivotY);  // Move back

        // Apply transform to shape
        Shape rotatedShape = transform.createTransformedShape(shape);

        // Draw the rotated arrow
        g.setPaint(new Color(0x262626));
        g.fill(rotatedShape);
    }

}


