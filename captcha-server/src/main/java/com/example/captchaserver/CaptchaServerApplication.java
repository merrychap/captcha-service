package com.example.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
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
    private String errorReason = "errorReason";
    private HashMap<String, String> userId2captcha = new HashMap<>();

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView captchaForm(Model model, HttpServletResponse response) {
        CaptchaHandler handler = new CaptchaHandler();
        String userId = TextHandler.generateId();
        String captcha = handler.generateBase64Captcha();

        userId2captcha.put(userId, handler.genText);

        model.addAttribute("name", "stranger");
        model.addAttribute("captcha", captcha);

        response.setHeader("UserId", userId);
        response.setHeader("CaptchaAnswer", handler.genText);
        response.setHeader("TimeExpires", "Someday");

        return new ModelAndView("index", (Map<String, ?>) model);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView captchaSubmit(@ModelAttribute("user_id") String userId,
                                      @ModelAttribute("captcha_ans") String captchaAns,
                                      Model model) {
        if (!userId2captcha.containsKey(userId)) {
            model.addAttribute(errorReason, "There is no such id");
            return new ModelAndView("error", (Map<String, ?>) model);
        }
        String expectedCaptcha = userId2captcha.get(userId);
        if (!expectedCaptcha.equals(captchaAns)) {
            model.addAttribute(errorReason, "CAPTCHA is not correct");
            return new ModelAndView("error", (Map<String, ?>) model);
        } else return new ModelAndView("correct");
    }
}
