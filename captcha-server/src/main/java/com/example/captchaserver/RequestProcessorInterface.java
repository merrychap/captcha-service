package com.example.captchaserver;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface RequestProcessorInterface {
    ModelAndView processGetRequest(Model model, HttpServletResponse response) throws IOException;
    ModelAndView processPostRequest(Model model, String[] params);
}
