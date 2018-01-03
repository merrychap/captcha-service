package com.example.captchaserver;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;


public abstract class TextedImage implements ImageGeneratorInterface {
    protected int width;
    protected int height;
    protected int textLen;

    protected Graphics2D graphics;
    protected BufferedImage bufImage;

    public abstract void drawText(Graphics2D graphics);
    public abstract void drawOnImage(Graphics2D graphics);
    public abstract void initGraphics(Graphics2D graphics);

    @Override
    public void createImage(int width, int height) {
        this.width = width;
        this.height = height;

        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics    = bufImage.createGraphics();

        initGraphics(graphics);
        drawOnImage(graphics);

        this.bufImage = bufImage;
    }
}