package com.example.captchaserver;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;


public class CaptchaHandler extends TextedImage {
    public static String EmptyString = "";

    public String genText;

    private Random rand;

    @Override
    public void drawText(Graphics2D graphics) {
        int x = 0, y = 0;

        for (int i = 0; i < this.genText.length(); i++) {
            x += 13 + Math.abs(rand.nextInt()) % 15;
            y = 25 + Math.abs(rand.nextInt()) % 20;
            graphics.drawChars(this.genText.toCharArray(), i, 1, x, y);
        }
        graphics.dispose();
    }

    @Override
    public void drawOnImage(Graphics2D graphics) {
        graphics.fillRect(0, 0, this.width, this.height);
        graphics.setColor(Color.MAGENTA);
    }

    @Override
    public void initGraphics(Graphics2D graphics) {
        RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gp  = new GradientPaint(0, 0, new Color(234, 255, 99), 0, this.height/2, new Color(68, 255, 109), true);

        rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        graphics.setFont(new Font("Courier New", Font.BOLD, 30));
        graphics.setRenderingHints(rh);
        graphics.setPaint(gp);

        this.graphics = graphics;
    }

    public CaptchaHandler() {
        rand = new Random();
        width   = 180;
        height  = 80;
        textLen = 7;
    }

    public String generateBase64Captcha() {
        this.genText = TextHandler.generateText(this.textLen);
        createImage(width, height);
        drawText(this.graphics);

        try {
            return DatatypeConverter.printBase64Binary(imageToBytes(this.bufImage));
        } catch (IOException ex) {
            return EmptyString;
        }
    }

    private byte[] imageToBytes(BufferedImage bufImage) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bufImage, "png", baos);
        baos.flush();
        byte[] imageBytes = baos.toByteArray();
        baos.close();
        return imageBytes;
    }
}