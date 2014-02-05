package com.cuubez.core.engine.transform;


public interface Transformer {

    public String marshal(Object object);

    public Object unMarshal(String content, Class<?> targetClass);
}
