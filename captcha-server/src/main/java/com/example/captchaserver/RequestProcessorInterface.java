package com.example.captchaserver;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;


public interface RequestProcessorInterface {
    ModelAndView processGetRequest(Model model, HttpServletResponse response);
    ModelAndView processPostRequest(Model model, String[] params);
}
