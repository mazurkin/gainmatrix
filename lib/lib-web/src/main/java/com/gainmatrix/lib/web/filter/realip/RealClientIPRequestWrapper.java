package com.gainmatrix.lib.web.filter.realip;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Обертка вокруг запроса выдающая реальный IP-адрес пользователя
 */
public class RealClientIPRequestWrapper extends HttpServletRequestWrapper {

    private final String realIpAddress;

    public RealClientIPRequestWrapper(HttpServletRequest request, String realIpAddress) {
        super(request);

        this.realIpAddress = realIpAddress;
    }

    @Override
    public String getRemoteAddr() {
        return realIpAddress;
    }

    @Override
    public String getRemoteHost() {
        try {
            return InetAddress.getByName(getRemoteAddr()).getHostName();
        } catch (UnknownHostException e) {
            return getRemoteAddr();
        }
    }

}
