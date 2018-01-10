package com.example.captchaserver;

import java.util.Arrays;

public class RequestImageProcessor {
    private static NoiseFilter noiseFilter = new NoiseFilter();

    public static Object generate(ImageType type) {
        if (type == ImageType.CAPTCHA)
            return generateCaptcha();
        return null;
    }

    private static CaptchaGenResult generateCaptcha() {
        CaptchaImageHandler captchaImageHandler = new CaptchaImageHandler(Arrays.asList(noiseFilter));

        String base64image = captchaImageHandler.generateBase64Captcha();
        String genText     = captchaImageHandler.genText;

        return new CaptchaGenResult(genText, base64image);
    }

}
