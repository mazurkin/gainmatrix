package com.gainmatrix.lib.spring.security.password.impl;

import com.gainmatrix.lib.spring.security.password.UserPasswordCipher;
import com.gainmatrix.lib.spring.security.password.UserSaltSource;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class UserPasswordCipherImpl implements UserPasswordCipher {

    private PasswordEncoder passwordEncoder;

    private UserSaltSource userSaltSource;

    @Override
    public String cipher(String username, String plainPassword) {
        Preconditions.checkNotNull(username, "User name is null");
        Preconditions.checkNotNull(plainPassword, "Password is null");

        String salt = userSaltSource.getSalt(username);

        return passwordEncoder.encodePassword(plainPassword, salt);
    }

    @Required
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Required
    public void setUserSaltSource(UserSaltSource userSaltSource) {
        this.userSaltSource = userSaltSource;
    }

}
