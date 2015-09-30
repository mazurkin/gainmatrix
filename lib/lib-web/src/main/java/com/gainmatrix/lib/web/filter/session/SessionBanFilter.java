package com.gainmatrix.lib.web.filter.session;

import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Фильтр запрещает создание сессии. При любой попытке получения сессии выбрасывается исключение.
 */
public class SessionBanFilter extends OncePerRequestFilter {

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException
    {
        HttpServletRequest sessionBanRequest = new SessionBanRequestWrapper(request);
        chain.doFilter(sessionBanRequest, response);
    }

}
