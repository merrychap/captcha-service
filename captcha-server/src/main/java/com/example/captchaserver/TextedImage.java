package com.example.captchaserver;

import java.awt.*;
import java.awt.image.BufferedImage;


public abstract class TextedImage implements ImageGeneratorInterface {
    protected int width;
    protected int height;
    protected int textLen;

    protected BufferedImage bufImage;

    public abstract void drawText(Graphics2D graphics);
    public abstract void drawImage(Graphics2D graphics);
    public abstract Graphics2D initGraphics(Graphics2D graphics);

    @Override
    public BufferedImage createImage(int width, int height) {
        this.width  = width;
        this.height = height;

        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics    = bufImage.createGraphics();

        drawImage(initGraphics(graphics));

        return bufImage;
    }
}