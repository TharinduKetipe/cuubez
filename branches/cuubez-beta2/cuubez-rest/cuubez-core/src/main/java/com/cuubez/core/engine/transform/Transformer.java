package com.cuubez.core.engine.transform;


public interface Transformer {

    public String marshal(Object object);

    public Object unMarshal(String content);

    public Object unMarshal(String rootNode, String content, Class<?> targetClass);
}
