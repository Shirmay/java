package com.zlb.sso.client.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import java.util.Objects;

public abstract class AbstractCasFilter extends AbstractConfigurationFilter{

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractCasFilter.class);

    public static final String CONST_CAS_ASSERTION = "_const_cas_assertion_";

    protected String artifactParameterName = "ticket";

    protected String serviceParameterName = "service";

    private String serverName;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        setServerName(getInitParameterValue(filterConfig, "serverName", null));
        initInternal(filterConfig);
        init();
    }

    protected void init(){
        Objects.requireNonNull(serverName, "serverName is null");
    }

    protected void initInternal(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
