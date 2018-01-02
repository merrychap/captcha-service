package com.example.captchaserver;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageGeneratorInterface {
    void drawOnImage(BufferedImage image, Graphics2D g);
    void initGraphics(Graphics2D graphics);

    BufferedImage createImage(int width, int height);
}
