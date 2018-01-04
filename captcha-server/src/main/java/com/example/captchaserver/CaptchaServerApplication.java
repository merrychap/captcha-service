package com.example.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
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
    private long minutesDelay  = 1;
    private String errorReason = "errorReason";

    private String userIdHeader        = "UserId";
    private String captchaAnswerHeader = "CaptchaAnswer";

    private String captchaAttribute = "captcha";
    private String nameAttribute    = "name";

    private HashMap<String, CaptchaTimeTuple> userId2Data = new HashMap<>();


    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView captchaForm(Model model, HttpServletResponse response) {
        CaptchaHandler handler = new CaptchaHandler();
        String userId = TextHandler.generateId();
        String captcha = handler.generateBase64Captcha();

        userId2Data.put(userId, new CaptchaTimeTuple(handler.genText, LocalDateTime.now()));

        model.addAttribute(nameAttribute, "stranger");
        model.addAttribute(captchaAttribute, captcha);

        response.setHeader(userIdHeader, userId);
        response.setHeader(captchaAnswerHeader, handler.genText);

        return new ModelAndView("index", (Map<String, ?>) model);
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public ModelAndView captchaSubmit(@ModelAttribute("user_id") String userId,
                                      @ModelAttribute("captcha_ans") String captchaAns,
                                      Model model) {
        if (!userId2Data.containsKey(userId))
            return generateError(model, "There is no such id");

        CaptchaTimeTuple expectedData = userId2Data.get(userId);
        if (!expectedData.captcha.equals(captchaAns))
            return generateError(model, "CAPTCHA is not correct");
        else {
            return processValidPost(expectedData, model, userId);
        }
    }

    private ModelAndView processValidPost(CaptchaTimeTuple expectedData, Model model, String userId) {
        if (getDateDiffInMinutes(expectedData.time) >= minutesDelay)
            return generateError(model, "Time for answer is exceeded");
        userId2Data.remove(userId);
        return new ModelAndView("correct");
    }

    private ModelAndView generateError(Model model, String errorString) {
        model.addAttribute(errorReason, errorString);
        return new ModelAndView("error", (Map<String, ?>) model);
    }

    private long getDateDiffInMinutes(LocalDateTime localTime) {
        LocalDateTime nowTime = LocalDateTime.now();
        Duration duration = Duration.between(nowTime, localTime);
        return Math.abs(duration.toMinutes());
    }
}

class CaptchaTimeTuple {
    public String captcha;
    public LocalDateTime time;

    public CaptchaTimeTuple(String captcha, LocalDateTime time) {
        this.captcha = captcha;
        this.time    = time;
    }
}
