package com.example.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;


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
    public ModelAndView rootPath(Map<String, Object> model) {
        CaptchaHandler handler = new CaptchaHandler();
        handler.generateCaptcha();
        handler = null;
        return new ModelAndView("index", model);
    }
}
