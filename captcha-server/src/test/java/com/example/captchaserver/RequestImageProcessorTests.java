package com.example.captchaserver;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.bind.DatatypeConverter;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RequestImageProcessorTests {
    @Test
    public void testDoesNotThrownExceptionOnCAPTCHA() {
        try {
            CaptchaGenResult generated = (CaptchaGenResult) RequestImageProcessor.generate(ImageType.CAPTCHA);
        } catch (ClassCastException e) {
            Assert.fail("Class cast exception. It had to be correctly casted");
        }
    }

    @Test
    public void testReturnedValueHasCorrectBase64OnCAPTCHA() {
        CaptchaGenResult generated = (CaptchaGenResult) RequestImageProcessor.generate(ImageType.CAPTCHA);
        try {
            DatatypeConverter.parseBase64Binary(generated.base64image);
        } catch (Exception e) {
            Assert.fail("Generated object has to have a valid base64 string as one of fields");
        }
    }
}
