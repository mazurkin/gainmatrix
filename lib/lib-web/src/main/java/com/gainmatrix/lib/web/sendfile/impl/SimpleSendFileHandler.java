package com.gainmatrix.lib.web.sendfile.impl;

import com.gainmatrix.lib.web.sendfile.SendFileHandler;
import com.gainmatrix.lib.web.sendfile.SendFileHandlerException;
import com.google.common.base.Preconditions;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * Простая отправка файла через потоковое копирование в выходной поток
 */
public class SimpleSendFileHandler implements SendFileHandler {

    @Override
    public void sendfile(HttpServletRequest request, HttpServletResponse response, File file)
        throws SendFileHandlerException
    {
        Preconditions.checkNotNull(request, "Request is null");
        Preconditions.checkNotNull(response, "Response is null");
        Preconditions.checkNotNull(file, "File is null");
        Preconditions.checkNotNull(file.isFile(), "File doesn't exist");

        try {
            InputStream is = new FileInputStream(file);
            try {
                InputStream bis =  new BufferedInputStream(is);
                try {
                    ServletOutputStream os = response.getOutputStream();
                    try {
                        IOUtils.copy(is, os);
                        os.flush();
                    } finally {
                        os.close();
                    }
                } finally {
                    bis.close();
                }
            } finally {
                is.close();
            }
        } catch (Exception e) {
            throw new SendFileHandlerException("Fail to send file", e);
        }
    }

}
