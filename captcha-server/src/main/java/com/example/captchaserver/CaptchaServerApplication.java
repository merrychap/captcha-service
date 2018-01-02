package com.example.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;


@SpringBootApplication
public class CaptchaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaptchaServerApplication.class, args);
	}
}

@RestController
@RequestMapping(value="/")
class CaptchaService {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView rootPath(HashMap<String, Object> model, HttpServletResponse response) {
        CaptchaHandler handler = new CaptchaHandler();
        String captcha = handler.generateBase64Captcha();
        handler = null;

        model.put("name", "stranger");
        model.put("captcha", captcha);

        response.setHeader("SomeHeader", "SomeValue");

        return new ModelAndView("index", model);
    }
}
