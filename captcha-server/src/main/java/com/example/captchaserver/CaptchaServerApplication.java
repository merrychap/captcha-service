package com.example.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@SpringBootApplication
public class CaptchaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaptchaServerApplication.class, args);
	}
}


@RestController
@RequestMapping(value="/")
class CaptchaController {
    private CaptchaProcessor cProcessor = new CaptchaProcessor();

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView captchaForm(ModelMap model, HttpServletResponse response) {
        try {
            return cProcessor.processGetRequest(model, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ModelAndView("index", model);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView captchaSubmit(@ModelAttribute("user_id") String userId,
                                      @ModelAttribute("captcha_ans") String captchaAns,
                                      ModelMap model) {
        return cProcessor.processPostRequest(model, new String [] {userId, captchaAns});
    }
}
