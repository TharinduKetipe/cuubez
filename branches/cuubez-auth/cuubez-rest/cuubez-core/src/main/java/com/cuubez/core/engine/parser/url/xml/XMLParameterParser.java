/**
 *	Copyright [2013] [www.cuubez.com]
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package com.cuubez.core.engine.parser.url.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.engine.parser.url.URLParser;
import com.cuubez.core.engine.parser.util.XMLParserUtil;
import com.cuubez.core.exception.CuubezException;

public class XMLParameterParser implements URLParser {

    private static Log log = LogFactory.getLog(XMLParameterParser.class);
    private static final String PARAM_ELEMENT_NAME = "parameter";

    public void parse(ConfigurationContext configurationContext) throws CuubezException {

        try {

            InputStream inputStream = configurationContext.getRequest().getInputStream();
            GZIPInputStream gzipStream = new GZIPInputStream(inputStream);

            if (inputStream != null) {

                Document doc = XMLParserUtil.createDocument(gzipStream);

                if (doc != null) {
                    List<Element> elements = XMLParserUtil.getElements(PARAM_ELEMENT_NAME, doc);
                    populateParamObjects(elements, configurationContext);
                }

            }

        } catch (IOException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (ParserConfigurationException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        } catch (TransformerException e) {
            log.error(e);
            throw new CuubezException(e, CuubezException.INTERNAL_EXCEPTION);
        }

    }


    public void populateParamObjects(List<Element> elements, ConfigurationContext configurationContext) throws TransformerException {

        if (elements != null) {

            for (Element element : elements) {
                Object object = UnMarshaller.unMarshal(XMLParserUtil.getDirectChild(element));
                configurationContext.getUrlContext().addParameter(object);
            }

        }

    }


}
