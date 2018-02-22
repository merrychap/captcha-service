package com.example.captchaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.ModelMap;
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
@RequestMapping("/captcha")
class CaptchaController {
    private RequestsHandler reqHandler = new RequestsHandler();

    @RequestMapping(value="/new", method=RequestMethod.GET)
    public ModelAndView getNewCaptcha(ModelMap model, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value="/image", method=RequestMethod.GET)
    public ModelAndView getCaptchaImage(ModelMap model, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value="/solve", method=RequestMethod.POST)
    public ModelAndView postSolveCaptcha(ModelMap model, HttpServletResponse response) {
        return null;
    }

    @RequestMapping(value="/verify", method=RequestMethod.GET)
    public ModelAndView getVerifyResult(ModelMap model, HttpServletResponse response) {
        return null;
    }

//    @RequestMapping(value="/", method=RequestMethod.GET)
//    public ModelAndView captchaForm(ModelMap model, HttpServletResponse response) {
//        try {
//            return reqHandler.processGetRequest(model, response);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return new ModelAndView("index", model);
//    }
//
//    @RequestMapping(value="/", method=RequestMethod.POST)
//    public ModelAndView captchaSubmit(@ModelAttribute("user_id") String userId,
//                                      @ModelAttribute("captcha_ans") String captchaAns,
//                                      ModelMap model) {
//        return reqHandler.processPostRequest(model, new String [] {userId, captchaAns});
//    }
}

@RestController
@RequestMapping("/client")
class ClientController {
    private ClientsProducer clientsProducer = new ClientsProducer();

    @RequestMapping(value="/register", method=RequestMethod.GET)
    @ResponseBody
    public String postClientRegister(ModelMap model, HttpServletResponse response) {
        return clientsProducer.createNewClient().toJSON();
    }
}
