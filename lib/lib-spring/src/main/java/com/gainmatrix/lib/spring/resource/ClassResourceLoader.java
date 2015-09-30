package com.gainmatrix.lib.spring.resource;

import org.springframework.core.io.DefaultResourceLoader;

/**
 * Загрузчик ресурсов, который загружает ресурсы через class-loader указанного класса
 */
public class ClassResourceLoader extends DefaultResourceLoader {

    public ClassResourceLoader(Class clazz) {
        super(clazz.getClassLoader());
    }

}
