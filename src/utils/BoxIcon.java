package utils;
import javax.swing.*;
import java.awt.*;

public class BoxIcon implements Icon {
    private final Color borderColor;
    private final Color fillColor;


    public BoxIcon(Color borderColor, Color fillColor) {
        this.borderColor = borderColor;
        this.fillColor = fillColor;
    }


    @Override
    public void paintIcon(Component c, Graphics g, int x, int y) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        g2.setColor(fillColor);
        g2.fillRect(x, y, getIconWidth(), getIconHeight());


        g2.setColor(borderColor);
        g2.drawRect(x, y, getIconWidth(), getIconHeight());

        g2.dispose();
    }



    @Override
    public int getIconWidth() {
        return 16;
    }


    @Override
    public int getIconHeight() {
        return 16;
    }
}