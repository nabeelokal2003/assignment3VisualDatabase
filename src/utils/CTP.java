package utils;

import java.awt.*;

public class CTP {
    private final Color base;
    private final Color mantle;
    private final Color text;
    private final Color sky;
    private final Color surface;
    private final Color crust;
    private final Color surface2;
    private final Color surface0;
    private final Color subtext;

    public Color getSurface0() {
        return surface0;
    }

    public Color getSurface2() {
        return surface0;
    }

    public Color getSurface() {
        return surface;
    }

    public Color getSubtext() {
        return subtext;
    }

    public Color getCrust() {
        return crust;
    }

    public CTP() {
        this.base = new Color(46,111,64);
        this.mantle = new Color(109,129,150);
        this.crust = new Color(0x11111b);
        this.text = Color.white;
        this.surface = new Color(0x585b70);
        this.sky = Color.orange;
        this.surface2 = new Color(0x585b70);
        this.surface0 = new Color(0x313244);
        this.subtext = new Color(0xa5adcb);
    }

    public Color getBase() {
        return base;
    }

    public Color getMantle() {
        return mantle;
    }

    public Color getText() {
        return text;
    }

    public Color getSky() {
        return sky;
    }
}