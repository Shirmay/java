package com.zlb.sso.client.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;

public abstract class AbstractConfigurationFilter implements Filter{

    private static Logger logger = LoggerFactory.getLogger(AbstractConfigurationFilter.class);

    protected String getInitParameterValue(FilterConfig config, String param, String defaultValue){
        String value = config.getInitParameter(param);
        if (!StringUtils.isBlank(value)){
            logger.debug("param：{}, value：{}", param, value);
            return value;
        }else {
            ServletContext servletContext = config.getServletContext();
            String value2 = servletContext.getInitParameter(param);
            if (!StringUtils.isBlank(value2)){
                logger.debug("param：{}, value：{}", param, value);
                return value2;
            }
        }
        return defaultValue;
    }
}
