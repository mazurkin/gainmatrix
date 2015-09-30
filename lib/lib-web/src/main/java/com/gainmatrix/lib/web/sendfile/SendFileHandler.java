package com.gainmatrix.lib.web.sendfile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;

/**
 * Обработка запросов на отсылку файлов
 */
public interface SendFileHandler {

    /**
     * Отправка файла в ответ на запрос
     * @param request HTTP-запрос
     * @param response HTTP-ответ
     * @param file Файл, который необходимо отправить
     * @throws SendFileHandlerException Исключение в случае ошибки
     */
    void sendfile(HttpServletRequest request, HttpServletResponse response, File file) throws SendFileHandlerException;

}
