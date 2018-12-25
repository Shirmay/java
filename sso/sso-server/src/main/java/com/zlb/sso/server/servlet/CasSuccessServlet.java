package com.zlb.sso.server.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/success")
public class CasSuccessServlet {

    public ModelAndView success(HttpServletRequest request, HttpServletResponse response){
        ModelAndView model = new ModelAndView("cas");
        return model;
    }

}
