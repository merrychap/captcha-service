package com.example.captchaserver;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageGeneratorInterface {
    void drawOnImage(Graphics2D g);
    void initGraphics(Graphics2D graphics);
    void createImage(int width, int height);
}
