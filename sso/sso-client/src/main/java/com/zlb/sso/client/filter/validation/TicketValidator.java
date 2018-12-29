package com.zlb.sso.client.filter.validation;

import com.zlb.sso.client.authentication.SimplePrincipal;

public interface TicketValidator {

    SimplePrincipal validation(String ticket, String url) throws Exception;

}
