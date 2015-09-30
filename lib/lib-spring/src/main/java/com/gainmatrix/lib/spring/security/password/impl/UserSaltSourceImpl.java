package com.gainmatrix.lib.spring.security.password.impl;

import com.gainmatrix.lib.spring.security.password.UserSaltSource;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;

public class UserSaltSourceImpl implements UserSaltSource {

    private String systemWideSalt;

    @Override
    public String getSalt(String username) {
        Preconditions.checkNotNull(username, "User name is null");

        StringBuilder sb = new StringBuilder();

        sb.append(username);
        sb.append('.');
        sb.append(systemWideSalt);

        return sb.toString();
    }

    @Required
    public void setSystemWideSalt(String systemWideSalt) {
        this.systemWideSalt = systemWideSalt;
    }

}
