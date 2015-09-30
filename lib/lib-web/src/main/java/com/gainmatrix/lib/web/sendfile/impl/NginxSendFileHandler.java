package com.gainmatrix.lib.web.sendfile.impl;

import com.gainmatrix.lib.web.sendfile.SendFileHandler;
import com.gainmatrix.lib.web.sendfile.SendFileHandlerException;
import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

/**
 * Отправка файлов через NGinx <a href="http://wiki.nginx.org/XSendfile">XSendfile</a>
 */
public class NginxSendFileHandler implements SendFileHandler {

    private static final String RESPONSE_ATTR_REDIRECT = "X-Accel-Redirect";

    @Override
    public void sendfile(HttpServletRequest request, HttpServletResponse response, File file)
        throws SendFileHandlerException
    {
        Preconditions.checkNotNull(request, "Request is null");
        Preconditions.checkNotNull(response, "Response is null");
        Preconditions.checkNotNull(file, "File is null");

        response.setHeader(RESPONSE_ATTR_REDIRECT, file.getPath());
    }

}
