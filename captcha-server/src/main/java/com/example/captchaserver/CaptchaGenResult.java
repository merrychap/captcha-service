package com.example.captchaserver;

public class CaptchaGenResult {
    public String generatedText;
    public String base64image;

    public CaptchaGenResult(String generatedText, String base64image) {
        this.generatedText = generatedText;
        this.base64image   = base64image;
    }
}
