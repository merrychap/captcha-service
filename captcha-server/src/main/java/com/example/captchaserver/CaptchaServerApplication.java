package com.example.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


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
    public ModelAndView captchaForm(Model model, HttpServletResponse response) {
        return cProcessor.processGetRequest(model, response);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView captchaSubmit(@ModelAttribute("user_id") String userId,
                                      @ModelAttribute("captcha_ans") String captchaAns,
                                      Model model) {
        return cProcessor.processPostRequest(model, new String [] {userId, captchaAns});
    }
}
