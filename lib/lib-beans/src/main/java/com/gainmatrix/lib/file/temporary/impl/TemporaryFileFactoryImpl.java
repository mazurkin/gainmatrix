package com.gainmatrix.lib.file.temporary.impl;

import com.gainmatrix.lib.file.temporary.TemporaryFileFactory;
import com.gainmatrix.lib.preconditions.Preconditions2;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

import java.io.File;
import java.io.IOException;

public class TemporaryFileFactoryImpl implements TemporaryFileFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporaryFileFactoryImpl.class);

    private static final String DEFAULT_PREFIX = "temp";

    private static final String DEFAULT_SUFFIX = ".tmp";

    private File directory;

    private String prefix = DEFAULT_PREFIX;

    private String suffix = DEFAULT_SUFFIX;

    @PostConstruct
    public void afterPropertiesSet() throws Exception {
        Preconditions2.checkNotBlank(prefix, "Prefix is not set");
    }

    @Override
    public File allocateFile() throws IOException {
        File file = File.createTempFile(prefix, suffix, directory);

        LOGGER.trace("Temporary file {} is allocated", file);

        return file;
    }

    @Override
    public void releaseFile(File file) {
        Preconditions.checkNotNull(file, "File is not specified");

        LOGGER.trace("Temporary file {} will be deleted", file);

        if (file.isFile() && !file.delete()) {
            LOGGER.warn("Fail to delete temporary file {}", file);
        }
    }

    public void setDirectory(File directory) {
        this.directory = directory;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

}
