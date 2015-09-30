package com.gainmatrix.lib.web.filter.realip;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Фильтр определяющий реальный IP-адрес клиента по специальным заголовкам которые выставляет фронтенд
 * @see <a href="http://www.lacerta.be/d7/content/keeping-real-user-ip-java-web-apps-behind-nginx-proxy">
 *     Keeping real user IP in Java web apps behind Nginx proxy</a>
 */
public class RealClientIPFilter extends OncePerRequestFilter {

    private static final String DEFAULT_REAL_IP_HEADER_NAME = "X-Real-IP";

    private String realIpHeaderName = DEFAULT_REAL_IP_HEADER_NAME;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException
    {
        String realIp = request.getHeader(realIpHeaderName);
        if (realIp != null) {
            HttpServletRequest requestWrapper = new RealClientIPRequestWrapper(request, realIp);
            filterChain.doFilter(requestWrapper, response);
            return;
        }

        filterChain.doFilter(request, response);
    }

    public void setRealIpHeaderName(String realIpHeaderName) {
        this.realIpHeaderName = realIpHeaderName;
    }

}
