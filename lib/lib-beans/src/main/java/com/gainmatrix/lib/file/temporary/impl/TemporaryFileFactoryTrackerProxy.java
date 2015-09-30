package com.gainmatrix.lib.file.temporary.impl;

import com.gainmatrix.lib.file.temporary.TemporaryFileFactory;
import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Proxy tracks the list of allocated files. Clears orphaned temporary files on exit and forbids deleting
 * non-temporary files.
 */
public class TemporaryFileFactoryTrackerProxy implements TemporaryFileFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(TemporaryFileFactoryTrackerProxy.class);

    private Set<File> allocated;

    private TemporaryFileFactory temporaryFileFactory;

    private boolean strictRelease;

    private boolean deleteOnDestroy;

    @PostConstruct
    public void afterPropertiesSet() {
        Preconditions.checkNotNull(temporaryFileFactory, "Temporary file factory is not set");

        this.allocated = Collections.newSetFromMap(new ConcurrentHashMap<File, Boolean>());
    }

    @PreDestroy
    public void destroy() throws Exception {
        if (deleteOnDestroy) {
            clearAllAllocated();
        }
    }

    protected void clearAllAllocated() {
        if (allocated.isEmpty()) {
            return;
        }

        for (File file : allocated) {
            LOGGER.warn("Undeleted temporary file {} will be deleted", file);
            temporaryFileFactory.releaseFile(file);
        }

        allocated.clear();
    }

    @Override
    public File allocateFile() throws IOException {
        File file = temporaryFileFactory.allocateFile();

        allocated.add(file);

        return file;
    }

    @Override
    public void releaseFile(File file) {
        if (allocated.contains(file)) {
            temporaryFileFactory.releaseFile(file);
        } else {
            if (strictRelease) {
                throw new IllegalStateException("Deleting non-temporary file [" + file + "] is forbidden");
            } else {
                LOGGER.warn("Trying to delete some non-temporary file {}", file);
            }
        }
    }

    public void setTemporaryFileFactory(TemporaryFileFactory temporaryFileFactory) {
        this.temporaryFileFactory = temporaryFileFactory;
    }

    public void setStrictRelease(boolean strictRelease) {
        this.strictRelease = strictRelease;
    }

    public void setDeleteOnDestroy(boolean deleteOnDestroy) {
        this.deleteOnDestroy = deleteOnDestroy;
    }
}
