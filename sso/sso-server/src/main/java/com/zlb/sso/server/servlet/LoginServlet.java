package com.zlb.sso.server.servlet;

import com.zlb.sso.server.bean.UserBean;
import com.zlb.sso.server.service.LoginService;
import com.zlb.sso.server.service.TicketStorage;
import com.zlb.sso.server.util.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/cas")
public class LoginServlet {

    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    @Autowired
    private TicketStorage storage;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(HttpServletRequest request) throws IOException, ServletException{
        ModelAndView model = new ModelAndView("login");
        String ticket = CookieUtils.getCookie(request, TicketStorage.KEY);
        if(StringUtils.isNotEmpty(ticket)){
            model.setViewName("cas");
            return model;
        }
        String url = request.getRequestURI() + (StringUtils.isEmpty(request.getQueryString()) ? "" : "?" + request.getQueryString());
        model.addObject("serverUrl", url);
        return model;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        ModelAndView model = new ModelAndView("login");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String service = request.getParameter("service");
        if (StringUtils.isEmpty(username)){
            model.addObject("message", "用户名或密码不能为空");
            logger.info("用户名不能为空");
            return model;
        }
        if (StringUtils.isEmpty(password)){
            model.addObject("message", "用户名或密码不能为空");
            logger.info("密码不能为空");
            return model;
        }
        UserBean user = loginService.getUserByLoginName(username);
        if (user == null){
            model.addObject("message", "账号或密码错误");
            logger.info("账号不存在");
            return model;
        }
        String userPassword = user.getPassword();
        if(!password.equals(userPassword)){
            model.addObject("message", "账号或密码错误");
            logger.info("密码错误");
            return model;
        }
        String ticket = storage.put(user);
        CookieUtils.addCookie(request, response, TicketStorage.KEY, ticket);
        if(StringUtils.isEmpty(service)){
            model.setViewName("cas");
            return model;
        }else{
            String url = service.indexOf("?") == -1 ? service + "?ticket=" + ticket : service + "&ticket=" + ticket;
            response.sendRedirect(url);
        }
        return null;
    }

    @RequestMapping(value = "/loginout", method = RequestMethod.GET)
    public String loginout(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        ModelAndView model = new ModelAndView("login");
        String ticket = CookieUtils.getCookie(request, TicketStorage.KEY);
        if(StringUtils.isNotEmpty(ticket)){
            CookieUtils.removeCookie(response, TicketStorage.KEY);
            storage.remove(ticket);
        }
        return "redirect:/login";
    }
}
