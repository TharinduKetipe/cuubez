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
package com.cuubez.core.engine.parser;

import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.context.MediaType;
import com.cuubez.core.engine.parser.url.URLParser;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.engine.parser.content.ContentParser;
import com.cuubez.core.engine.parser.content.xml.XMLContentParser;
import com.cuubez.core.engine.parser.content.xml.XMLExceptionParser;
import com.cuubez.core.engine.parser.url.xml.XMLParameterParser;

public class ParserFactory {

    public void parse(ConfigurationContext configurationContext, MediaType mediaType, int parserType) throws CuubezException {

        if (MediaType.XML.equals(mediaType)) {

            if (parserType == Parser.CONTENT) {

                ContentParser parser = new XMLContentParser();
                parser.parse(configurationContext);

            } else if (parserType == Parser.PARAMETER) {

               new XMLParameterParser().parse(configurationContext);

            } else if (parserType == Parser.URL) {

                URLParser parser = new URLDetailParser();
                new URLDetailParser().parse(configurationContext);

            } else if (parserType == Parser.EXCEPTION) {

                ContentParser parser = new XMLExceptionParser();
                parser.parse(configurationContext);
            }

        }


    }

}
