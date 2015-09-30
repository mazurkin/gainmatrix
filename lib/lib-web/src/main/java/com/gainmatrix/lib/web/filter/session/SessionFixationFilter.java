package com.gainmatrix.lib.web.filter.session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Фильтр запрещающий смену IP-адреса в ходе сессии. Если IP-адрес клиента меняется в ходе сессии то мы отказываем
 * пользователю в обработке запросов
 */
public class SessionFixationFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionFixationFilter.class);

    private static final String DEFAULT_ATTRIBUTE_NAME = "session.fixation.ip";

    private String attributeName = DEFAULT_ATTRIBUTE_NAME;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException
    {
        // Стараемся не создавать сессию сами

        // Если текущий IP-адрес не установлен, то просто вызываем оставшуюся цепочку фильтров и выходим
        String currentIp = request.getRemoteAddr();
        if (currentIp == null) {
            filterChain.doFilter(request, response);
            return;
        }

        // Если сессия существует то проверим сохраненный в ней адрес с текущим. При несовпадении сохранененного адреса
        // и текущего адреса выдаем 403-ю ошибку.
        HttpSession preSession = request.getSession(false);
        if (preSession != null) {
            String sessionIp = (String) preSession.getAttribute(attributeName);
            if ((sessionIp != null) && (!sessionIp.equals(currentIp))) {
                LOGGER.debug("IP fixation incident. IP-old={}, IP-new={}", sessionIp, currentIp);
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "IP fixation error");
                return;
            }
        }

        // Остальная цепочка фильтров
        filterChain.doFilter(request, response);

        // Если сессия существует, но адрес в ней еще не сохранен - сохраняем его. Проверим сессию заново, так как
        // после выполнения запроса пользователь может получить уже новую сессию.
        HttpSession postSession = request.getSession(false);
        if (postSession != null) {
            String sessionIp = (String) postSession.getAttribute(attributeName);
            if (sessionIp == null) {
                postSession.setAttribute(attributeName, currentIp);
            }
        }
    }

    public void setAttributeName(String attributeName) {
        this.attributeName = attributeName;
    }

}
