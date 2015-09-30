package com.gainmatrix.lib.web.resolver.locale.impl;

import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.Locale;

public class StubLocalResolver implements LocaleResolver {

    private Locale locale = Locale.getDefault();

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        this.locale = locale;
    }
}
