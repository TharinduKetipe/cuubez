package com.cuubez.core.engine.transform.xml;

import com.cuubez.core.engine.transform.Transformer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XMLTransformer implements Transformer {


    @Override
    public String marshal(Object object) {

        XStream xStream = new XStream(new DomDriver());
        xStream.alias(object.getClass().getSimpleName(), object.getClass());

        return xStream.toXML(object);
    }

    @Override
    public Object unMarshal(String content) {
        XStream xStream = new XStream(new DomDriver());
        return xStream.fromXML(content);
    }

    @Override
    public Object unMarshal(String rootNode, String content, Class<?> targetClass) {
        XStream xStream = new XStream(new DomDriver());
        xStream.alias(rootNode, targetClass);
        return targetClass.cast(xStream.fromXML(content));
    }
}
