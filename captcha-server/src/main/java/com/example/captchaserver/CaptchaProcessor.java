package com.example.captchaserver;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;


public class CaptchaProcessor implements RequestProcessorInterface {
    private long minutesDelay   = 1;
    private String indexPage    = "index";
    private String errorIdPage  = "errorId";

    private String userIdHeader        = "UserId";
    private String captchaAnswerHeader = "CaptchaAnswer";

    private HashMap<String, ResponseStruct> userId2Data;

    private CaptchaResponseGenerator respGenerator;

    public CaptchaProcessor() {
        userId2Data   = new HashMap<>();
        respGenerator = new CaptchaResponseGenerator(new String[] {"result", "errorReason"});
    }

    @Override
    public ModelAndView processGetRequest(Model model, HttpServletResponse response) throws IOException {
        CaptchaGenResult captcha = (CaptchaGenResult) RequestImageProcessor.generate(ImageType.CAPTCHA);
        String userId  = TextHandler.generateId();

        userId2Data.put(userId, new ResponseStruct(captcha.generatedText, captcha.base64image, LocalDateTime.now()));

        response.setHeader(userIdHeader, userId);
        response.setHeader(captchaAnswerHeader, captcha.generatedText);

        return respGenerator.generateResponse(indexPage, model, captcha.base64image);
    }

    @Override
    public ModelAndView processPostRequest(Model model, String[] params) {
        String userId     = params[0];
        String captchaAns = params[1];

        if (!userId2Data.containsKey(userId))
            return respGenerator.generateResponse(errorIdPage, model, "", false, 0);

        ResponseStruct expectedData = userId2Data.remove(userId);
        if (!expectedData.captcha.equals(captchaAns))
            return respGenerator.generateResponse(indexPage, model, expectedData.base64, false, 2);
        else
            return processValidPost(expectedData, model);
    }

    private ModelAndView processValidPost(ResponseStruct expectedData, Model model) {
        if (getDateDiffInMinutes(expectedData.time) >= minutesDelay)
            return respGenerator.generateResponse(indexPage, model, expectedData.base64, false, 1);
        return respGenerator.generateResponse(indexPage, model, expectedData.base64, true);
    }

    private long getDateDiffInMinutes(LocalDateTime localTime) {
        LocalDateTime nowTime = LocalDateTime.now();
        Duration duration = Duration.between(nowTime, localTime);
        return Math.abs(duration.toMinutes());
    }
}


class ResponseStruct {
    public String captcha;
    public LocalDateTime time;
    public String base64;

    public ResponseStruct(String captcha, String base64, LocalDateTime time) {
        this.captcha = captcha;
        this.time    = time;
        this.base64  = base64;
    }
}
