package com.example.captchaserver;

import java.util.Arrays;
import java.util.List;

public class RequestProcessor {
    private static NoiseFilter noiseFilter = new NoiseFilter();

    public static Object generate(ImageType type) {
        if (type == ImageType.CAPTCHA)
            return generateCaptcha();
        return null;
    }

    private static CaptchaGenResult generateCaptcha() {
        CaptchaHandler captchaHandler = new CaptchaHandler(Arrays.asList(noiseFilter));

        String base64image = captchaHandler.generateBase64Captcha();
        String genText     = captchaHandler.genText;

        return new CaptchaGenResult(genText, base64image);
    }

}
