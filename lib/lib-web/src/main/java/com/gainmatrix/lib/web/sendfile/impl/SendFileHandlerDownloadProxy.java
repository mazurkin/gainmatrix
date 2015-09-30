package com.gainmatrix.lib.web.sendfile.impl;

import com.gainmatrix.lib.web.sendfile.SendFileHandler;
import com.gainmatrix.lib.web.sendfile.SendFileHandlerException;
import com.google.common.base.Preconditions;
import org.springframework.beans.factory.annotation.Required;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

public class SendFileHandlerDownloadProxy implements SendFileHandler {

    private static final String DEFAULT_CONTENT_TYPE = "application/force-download";

    private String contentType = DEFAULT_CONTENT_TYPE;

    private SendFileHandler sendFileHandler;

    @Override
    public void sendfile(HttpServletRequest request, HttpServletResponse response, File file)
        throws SendFileHandlerException
    {
        Preconditions.checkNotNull(request, "Request is null");
        Preconditions.checkNotNull(response, "Response is null");
        Preconditions.checkNotNull(file, "File is null");

        String contentDispositionHeader = String.format("attachment; filename=\"%s\";", file.getName());
        response.setHeader("Content-Disposition", contentDispositionHeader);

        if ((file.isFile()) && (file.length() <= Integer.MAX_VALUE)) {
            response.setContentLength((int) file.length());
        }

        response.setContentType(contentType);

        sendFileHandler.sendfile(request, response, file);
    }

    @Required
    public void setSendFileHandler(SendFileHandler sendFileHandler) {
        this.sendFileHandler = sendFileHandler;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

}
