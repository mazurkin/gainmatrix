package com.gainmatrix.lib.business.entity;

import junit.framework.Assert;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class AbstractBusinessEntityTest {

    @Test
    public void testSerialization() throws Exception {
        SomeEntity someEntity = new SomeEntity();
        someEntity.setId(13L);
        someEntity.setValue("abcdefg");

        File file = File.createTempFile("test", ".tmp");
        file.deleteOnExit();

        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(someEntity);
        objectOutputStream.close();
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        SomeEntity someEntityLoaded = (SomeEntity) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        Assert.assertTrue(EqualsBuilder.reflectionEquals(someEntity, someEntityLoaded));
    }

    public static class SomeEntity extends AbstractBusinessEntity<Long> implements Serializable {

        public static final int BUSINESS_MODEL_VERSION = 753;

        private Long id;

        private String value;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public int getCurrentBusinessModelVersion() {
            return BUSINESS_MODEL_VERSION;
        }
    }
}
