package com.zlb.sso.client.filter.validation;

import com.zlb.sso.client.authentication.SimplePrincipal;
import com.zlb.sso.client.filter.AbstractCasFilter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class TicketValidationFilter extends AbstractCasFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(TicketValidationFilter.class);

    private String casServerValidationUrl;

    private TicketValidator ticketValidator = new CasServiceTicketValidator();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        String ticket = request.getParameter(artifactParameterName);
        if (StringUtils.isNotEmpty(ticket)){
            try{
                SimplePrincipal principal = ticketValidator.validation(ticket, constructServerValidationUrl(ticket));
                LOGGER.debug("Successfully authenticated user:{}", principal.getName());
                request.setAttribute(CONST_CAS_ASSERTION, principal);
                request.getSession(false).setAttribute(CONST_CAS_ASSERTION, principal);
                response.sendRedirect(getServerName());
            }catch (Exception ex){
                response.setStatus(403);
                LOGGER.error("ticket validation fail:{}", ex);
                throw new ServletException(ex);
            }
        }
    }

    @Override
    protected void initInternal(FilterConfig filterConfig) throws ServletException {
        setCasServerValidationUrl(getInitParameterValue(filterConfig, "casServerValidationUrl", null));
    }

    @Override
    protected void init() {
        super.init();
        Objects.requireNonNull(this.casServerValidationUrl, "casServerValidationUrl is null");
    }

    private String constructServerValidationUrl(String ticket){
        return casServerValidationUrl + (casServerValidationUrl.indexOf("?") != -1 ? "&" : "?") + serviceParameterName + "=" + getServerName() + "&" + artifactParameterName + "=" + ticket;
    }

    public String getCasServerValidationUrl() {
        return casServerValidationUrl;
    }

    public void setCasServerValidationUrl(String casServerValidationUrl) {
        this.casServerValidationUrl = casServerValidationUrl;
    }

    public TicketValidator getTicketValidator() {
        return ticketValidator;
    }

    public void setTicketValidator(TicketValidator ticketValidator) {
        this.ticketValidator = ticketValidator;
    }
}
