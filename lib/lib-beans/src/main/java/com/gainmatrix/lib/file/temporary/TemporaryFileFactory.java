package com.gainmatrix.lib.file.temporary;

import java.io.File;
import java.io.IOException;

/**
 * Temporary file allocation abstraction
 */
public interface TemporaryFileFactory {

    /**
     * Запрос временного файла
     * @return Дескриптор временного файла
     * @throws IOException Исключение в случае ошибки выделения файла
     */
    File allocateFile() throws IOException;

    /**
     * Освобождение выделенного временного файла
     * @param file Временный файл
     */
    void releaseFile(File file);

}
