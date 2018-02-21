package com.example.captchaserver;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface RequestProcessorInterface {
    ModelAndView processGetRequest(ModelMap model, HttpServletResponse response) throws IOException;
    ModelAndView processPostRequest(ModelMap model, String[] params);
}
