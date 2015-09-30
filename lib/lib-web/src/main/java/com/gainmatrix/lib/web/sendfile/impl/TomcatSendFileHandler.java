package com.gainmatrix.lib.web.sendfile.impl;

import com.gainmatrix.lib.web.sendfile.SendFileHandler;
import com.gainmatrix.lib.web.sendfile.SendFileHandlerException;
import com.google.common.base.Preconditions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

/**
 * Отправка файлов через <a href="http://tomcat.apache.org/tomcat-6.0-doc/aio.html">sendfile</a>
 * с помощью APR-модуля Tomcat
 */
public class TomcatSendFileHandler implements SendFileHandler {

    private static final String REQUEST_ATTR_SENDFILE_SUPPORT = "org.apache.tomcat.sendfile.support";

    private static final String REQUEST_ATTR_SENDFILE_FILENAME = "org.apache.tomcat.sendfile.filename";

    private static final String REQUEST_ATTR_SENDFILE_START_OFFSET = "org.apache.tomcat.sendfile.start";

    private static final String REQUEST_ATTR_SENDFILE_END_OFFSET = "org.apache.tomcat.sendfile.end";

    @Override
    public void sendfile(HttpServletRequest request, HttpServletResponse response, File file)
        throws SendFileHandlerException
    {
        Preconditions.checkNotNull(request, "Request is null");
        Preconditions.checkNotNull(response, "Response is null");
        Preconditions.checkNotNull(file, "File is null");
        Preconditions.checkArgument(file.isFile(), "File doesn't exist");

        Object sendFileSupport = request.getAttribute(REQUEST_ATTR_SENDFILE_SUPPORT);
        if (Boolean.TRUE.equals(sendFileSupport)) {
            throw new SendFileHandlerException("Tomcat's 'sendfile' feature is not enabled");
        }

        request.setAttribute(REQUEST_ATTR_SENDFILE_FILENAME, file.getPath());
        request.setAttribute(REQUEST_ATTR_SENDFILE_START_OFFSET, 0L);
        request.setAttribute(REQUEST_ATTR_SENDFILE_END_OFFSET, file.length());
    }

}
