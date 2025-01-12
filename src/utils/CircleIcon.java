package utils;

import javax.swing.*;
import java.awt.*;

public class CircleIcon implements Icon {
    private final Color borderColor;
    private final Color fillColor;

    // Constructor to initialize border and fill colors
    public CircleIcon(Color borderColor, Color fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }

    // Paint the icon as a circle with the specified border and fill colors
    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the filled circle
        g2.setColor(fillColor);
        g2.fillOval(x, y, getIconWidth(), getIconHeight());

        // Draw the border of the circle
        g2.setColor(borderColor);
        g2.drawOval(x, y, getIconWidth(), getIconHeight());

        g2.dispose();
    }

    // Define the width of the icon (in this case, 16 pixels)
    @Override
    public int getIconWidth() {
        return 16;
    }

    // Define the height of the icon (in this case, 16 pixels)
    @Override
    public int getIconHeight() {
        return 16;
    }
}