package com.zlb.sso.client.filter.Authentication;

import com.zlb.sso.client.authentication.SimplePrincipal;
import com.zlb.sso.client.filter.AbstractCasFilter;
import com.zlb.sso.client.utils.CommonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Objects;

public class AuthenticationFilter extends AbstractCasFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    private String casServerLoginUrl;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        HttpSession session = request.getSession(false);
        SimplePrincipal principal = (SimplePrincipal)session.getAttribute(CONST_CAS_ASSERTION);
        if (principal != null){
            doFilter(servletRequest, servletResponse, filterChain);
        } else {
            String ticket = request.getParameter(artifactParameterName);
            if (StringUtils.isNotBlank(ticket)){
                doFilter(servletRequest, servletResponse, filterChain);
            }else {
                String redirectUrl = constructRedirectUrl();
                LOGGER.debug("redirectUrl：{}", redirectUrl);
                response.sendRedirect(redirectUrl);
            }
        }
    }

    @Override
    protected void initInternal(FilterConfig filterConfig) throws ServletException {
        setCasServerLoginUrl(getInitParameterValue(filterConfig, "casServerLoginUrl", null));
    }

    @Override
    protected void init() {
        super.init();
        Objects.requireNonNull(this.getCasServerLoginUrl(), "casServerLoginUrl is null");
    }

    private String constructRedirectUrl(){
        try{
            return casServerLoginUrl + (casServerLoginUrl.indexOf("?") != -1 ? "&" : "?") + serviceParameterName + "=" + URLEncoder.encode(getServerName(), "UTF-8");
        }catch (UnsupportedEncodingException ex){
            throw new RuntimeException(ex);
        }
    }

    public String getCasServerLoginUrl() {
        return casServerLoginUrl;
    }

    public void setCasServerLoginUrl(String casServerLoginUrl) {
        this.casServerLoginUrl = casServerLoginUrl;
    }
}