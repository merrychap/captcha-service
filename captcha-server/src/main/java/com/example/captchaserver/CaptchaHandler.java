package com.example.captchaserver;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class CaptchaHandler extends TextedImage {
    private int width   = 150;
    private int height  = 50;
    private int textLen = 6;

    private String genText;
    private Random rand;

    public CaptchaHandler() {
        rand = new Random();
    }

    public String generateBase64Captcha() {
        this.genText = TextHandler.generateText(this.textLen);
        createImage(width, height);
        drawText(this.graphics);

        try {
            return DatatypeConverter.printBase64Binary(imageToBytes(this.bufImage));
        } catch (IOException ex) {
            return "";
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

    @Override
    public void drawText(Graphics2D graphics) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < this.genText.length(); i++) {
            x += 10 + Math.abs(rand.nextInt()) % 15;
            y = 20 + Math.abs(rand.nextInt()) % 20;
            graphics.drawChars(this.genText.toCharArray(), i, 1, x, y);
        }
        graphics.dispose();
    }
}
