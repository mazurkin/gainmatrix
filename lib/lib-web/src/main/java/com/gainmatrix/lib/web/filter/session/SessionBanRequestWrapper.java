package com.gainmatrix.lib.web.filter.session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

public class SessionBanRequestWrapper extends HttpServletRequestWrapper {

    public SessionBanRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public HttpSession getSession() {
        throw new IllegalStateException("This application is sessionless");
    }

    @Override
    public HttpSession getSession(boolean create) {
        if (create) {
            throw new IllegalStateException("This application is sessionless");
        } else {
            return null;
        }
    }

}
