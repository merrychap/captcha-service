package com.example.captchaserver;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;


public abstract class TextedImage implements ImageGeneratorInterface {
    protected String text2generate;

    protected int width;
    protected int height;

    public abstract void drawText(Graphics2D graphics);

    @Override
    public void drawOnImage(BufferedImage image, Graphics2D graphics) {
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.setColor(new Color(255, 153, 0));
    }

    @Override
    public void initGraphics(Graphics2D graphics) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp  = new GradientPaint(0, 0, Color.red, 0, this.height/2, Color.black, true);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        graphics.setFont(new Font("Georgia", Font.BOLD, 11));
        graphics.setRenderingHints(rh);
        graphics.setPaint(gp);
    }

    @Override
    public BufferedImage createImage(int width, int height) {
        BufferedImage bufImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics    = bufImage.createGraphics();

        initGraphics(graphics);
        drawOnImage(bufImage, graphics);

        return null;
    }
}
