package com.gainmatrix.lib.spring.security.userdetails;

import com.gainmatrix.lib.spring.security.password.UserSaltSource;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.security.authentication.dao.SaltSource;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Источник дополнительного кода для хэш-функции использующий как системный дополнительный код так и имя пользователя
 */
public class UserDetailsSaltSource implements SaltSource {

    private UserSaltSource userSaltSource;

    @Override
    public Object getSalt(UserDetails userDetails) {
        Preconditions.checkNotNull(userDetails, "User details reference is null");

        return userSaltSource.getSalt(userDetails.getUsername());
    }

    @Required
    public void setUserSaltSource(UserSaltSource userSaltSource) {
        this.userSaltSource = userSaltSource;
    }

}
