package com.zlb.sso.server.bean;

import com.zlb.sso.server.authentication.UserPrincipal;

public class UserBean extends UserPrincipal implements java.io.Serializable{

    private Long id;

    private String password;

    private String salt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    @Override
    public String toString() {
        return super.getName();
    }
}
