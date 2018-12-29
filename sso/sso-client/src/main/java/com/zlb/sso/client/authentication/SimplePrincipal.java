package com.zlb.sso.client.authentication;

import java.io.Serializable;
import java.security.Principal;

public class SimplePrincipal implements Principal, Serializable{

    private static final long serialVersionUID = -5645357206342793145L;

    private String name;

    public SimplePrincipal(){

    }

    public SimplePrincipal(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
