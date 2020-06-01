package com.book.utils;

import org.apache.commons.beanutils.BeanUtilsBean;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class BookUtils extends BeanUtilsBean {

    public static String[] toLowerCase(String[] args) {
        AtomicReference<ArrayList<String>> atomicReference = new AtomicReference(new ArrayList<String>());
        Stream.of(args).forEach(item -> {
            atomicReference.get().add(item.toLowerCase());
                }
        );
        return  atomicReference.get().toArray(new String[0]);
    }

    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if(value==null)return;
        super.copyProperty(dest, name, value);
    }

}
