package com.zlb.sso.server.servlet;

import com.zlb.sso.server.service.TicketStorage;
import com.zlb.sso.server.util.CookieUtils;
import com.zlb.sso.server.vo.ResponseData;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/cas")
public class ValidateTicktServlet {

    @RequestMapping(value = "/validate", method = RequestMethod.GET)
    @ResponseBody
    public ResponseData validate(HttpServletRequest request, HttpServletResponse response){
        String ticket = request.getParameter("ticket");
        String server = request.getParameter("server");
        if (StringUtils.isEmpty(ticket)){
            return ResponseData.faill("ticket is null");
        }
        if (StringUtils.isEmpty(server)){
            return ResponseData.faill("server is null");
        }
        String name = CookieUtils.getCookie(request, TicketStorage.KEY);
        if (StringUtils.isEmpty(name)){
            return ResponseData.faill("validate fail");
        }
        return ResponseData.success(name);
    }
}
