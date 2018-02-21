package com.example.captchaserver;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

public class CaptchaResponseGenerator {
    private String captchaAttribute = "captcha";

    private String errorReason = "errorReason";
    private String resultParam = "result";

    private String correctAns   = "Correct!";
    private String incorrectAns = "Error!";

    private String[] errors = {"There is no such ID!",
                               "Time for answer is exceeded!",
                               "CAPTCHA is not correct!"};

    private String[] params;

    public CaptchaResponseGenerator(String[] params) {
        this.params = params;
    }

    public ModelAndView generateResponse(String page, ModelMap model, String captcha) {
        for (String attr : params) {
            model.addAttribute(attr, "");
        }
        model.addAttribute(captchaAttribute, captcha);
        return new ModelAndView(page, model);
    }

    public ModelAndView generateResponse(String page, ModelMap model, String captcha, boolean IsCorrect) {
        model.addAttribute(resultParam, correctAns);
        model.addAttribute(errorReason, "");
        model.addAttribute(captchaAttribute, captcha);
        return new ModelAndView(page, model);
    }

    public ModelAndView generateResponse(String page, ModelMap model, String captcha, boolean isCorrect, int errorCode) {
        if (isCorrect) {
            model.addAttribute(resultParam, correctAns);
            model.addAttribute(errorReason, "");
        } else {
            model.addAttribute(resultParam, incorrectAns);
            model.addAttribute(errorReason, errors[errorCode]);

            if (errorCode == 0)
                return new ModelAndView(page, model);
        }
        model.addAttribute(captchaAttribute, captcha);
        return new ModelAndView(page, model);
    }
}
