package com.cuubez.core.handler;


import com.cuubez.core.context.MessageContext;
import com.cuubez.core.engine.transform.Transformer;
import com.cuubez.core.engine.transform.xml.XMLTransformer;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.util.XMLTransformerUtil;
import org.w3c.dom.Document;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.InputStream;

public class RequestTransformHandler implements RequestHandler {

    @Override
    public void handleRequest(MessageContext messageContext) throws CuubezException {
        //TODO need to do this transformation proper way

        transformFromXML(messageContext);

    }


    private void transformFromXML(MessageContext messageContext) {

        String content = null;
        Object output;
        Document document = null;

        Transformer transformer = new XMLTransformer();

        try {

            document = XMLTransformerUtil.createDocument(messageContext.getRequestConfigurationContext().getRequest().getInputStream());
            content = XMLTransformerUtil.getDocumentAsString(document);

        } catch (IOException e) {

        } catch (ParserConfigurationException e) {

        } catch (TransformerException e) {
            // e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        if (content != null) {
            Class<?> inputObjectType = messageContext.getRequestContext().getSelectedResource().getSelectedMethodMetaData().getInputObjectType();
            String rootNode = XMLTransformerUtil.getRootNodeAsString(document);
            output = transformer.unMarshal(rootNode, content, inputObjectType);
            setInputObjectToParameterArray(messageContext, output);
        }

    }


    private void transformFromJSON(MessageContext messageContext) {



    }




    private void setInputObjectToParameterArray(MessageContext messageContext, Object input) {

        Object[] parameters = messageContext.getRequestContext().getSelectedResource().getSelectedMethodMetaData().getParameters();
        int inputObjectIndex =  messageContext.getRequestContext().getSelectedResource().getSelectedMethodMetaData().getInputObjectIndex();

        if(parameters == null) {
            parameters = new Object[1];
            messageContext.getRequestContext().getSelectedResource().getSelectedMethodMetaData().setParameters(parameters);
        }

        if(parameters.length > inputObjectIndex) {
            parameters[inputObjectIndex] = input;
        }
    }


}
