package com.example.captchaserver;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface ImageGeneratorInterface {
    void drawImage(Graphics2D g);
    Graphics2D initGraphics(Graphics2D graphics);
    BufferedImage createImage(int width, int height);
}
