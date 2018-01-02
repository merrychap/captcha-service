package com.example.captchaserver;

import java.awt.*;
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

    public void generateCaptcha() {
        this.genText = TextHandler.generateText(this.textLen);
        createImage(width, height);
    }

    @Override
    public void drawText(Graphics2D graphics) {
        int x = 0;
        int y = 0;

        for (int i = 0; i < this.genText.length(); i++) {
            x += 10 + Math.abs(rand.nextInt()) % 15;
            y += 20 + Math.abs(rand.nextInt()) % 20;

            graphics.drawChars(this.genText.toCharArray(), i, 1, x, y);
        }
        graphics.dispose();
        
    }
}
