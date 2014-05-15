package com.cuubez.core.transform.json;


import com.cuubez.core.transform.Transformer;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.json.JettisonMappedXmlDriver;
import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class JSONTransformer implements Transformer {

    @Override
    public String marshal(Object object) {
        XStream xstream = new XStream(new JsonHierarchicalStreamDriver());
        xstream.alias(object.getClass().getSimpleName(), object.getClass());
        xstream.setMode(XStream.NO_REFERENCES);
        return xstream.toXML(object);
    }

    @Override
    public Object unMarshal(String content) {
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        return xstream.fromXML(content);
    }

    @Override
    public Object unMarshal(String rootNode, String content, Class<?> targetClass) {
        XStream xstream = new XStream(new JettisonMappedXmlDriver());
        return targetClass.cast(xstream.fromXML(content));
    }
}
