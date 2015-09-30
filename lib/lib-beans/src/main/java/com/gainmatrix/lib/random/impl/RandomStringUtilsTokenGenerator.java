package com.gainmatrix.lib.random.impl;

import com.gainmatrix.lib.random.RandomTokenGenerator;
import com.google.common.base.Preconditions;
import org.apache.commons.lang.RandomStringUtils;

/**
 * Генератор токенов на основе функций из библиотеки Apache Common Lang
 */
public class RandomStringUtilsTokenGenerator implements RandomTokenGenerator {

    private RandomStringUtilsType type = RandomStringUtilsType.ALPHANUMERIC;

    @Override
    public String generate(int length) {
        Preconditions.checkArgument(length > 0, "Length should be positive");

        switch (type) {
            case ALPHA:
                return RandomStringUtils.randomAlphabetic(length);
            case NUMERIC:
                return RandomStringUtils.randomNumeric(length);
            case ALPHANUMERIC:
                return RandomStringUtils.randomAlphanumeric(length);
            default:
                throw new IllegalArgumentException("Type is not supported: " + type);
        }
    }

    public void setType(RandomStringUtilsType type) {
        this.type = type;
    }

}
