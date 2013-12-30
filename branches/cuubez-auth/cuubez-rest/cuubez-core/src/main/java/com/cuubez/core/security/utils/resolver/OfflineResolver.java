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

package com.cuubez.core.security.utils.resolver;


import org.apache.xml.security.signature.XMLSignatureInput;
import org.apache.xml.security.utils.resolver.ResourceResolverException;
import org.apache.xml.security.utils.resolver.ResourceResolverSpi;
import org.w3c.dom.Attr;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

public class OfflineResolver extends ResourceResolverSpi {


    /** Field _uriMap */
    static Map<String, String> _uriMap = null;

    /** Field _mimeMap */
    static Map<String, String> _mimeMap = null;

    static {
        org.apache.xml.security.Init.init();

        OfflineResolver._uriMap = new HashMap<String, String>();
        OfflineResolver._mimeMap = new HashMap<String, String>();

        OfflineResolver.register("http://www.w3.org/TR/xml-stylesheet",
                                 "/Users/prabath/Downloads/xml-security-1_5_2/samples/data/org/w3c/www/TR/xml-stylesheet.html",
                                 "text/html");
        OfflineResolver.register("http://www.w3.org/TR/2000/REC-xml-20001006",
                                 "/Users/prabath/Downloads/xml-security-1_5_2/samples/data/org/w3c/www/TR/2000/REC-xml-20001006",
                                 "text/xml");
        OfflineResolver.register("http://www.nue.et-inf.uni-siegen.de/index.html",
                                 "/Users/prabath/Downloads/xml-security-1_5_2/samples/data/org/apache/xml/security/temp/nuehomepage",
                                 "text/html");
        OfflineResolver.register("http://www.nue.et-inf.uni-siegen.de/~geuer-pollmann/id2.xml",
                                 "/Users/prabath/Downloads/xml-security-1_5_2/samples/data/org/apache/xml/security/temp/id2.xml", "text/xml");
        OfflineResolver.register("http://xmldsig.pothole.com/xml-stylesheet.txt",
                                 "/Users/prabath/Downloads/xml-security-1_5_2/samples/data/com/pothole/xmldsig/xml-stylesheet.txt", "text/xml");
        OfflineResolver.register("http://www.w3.org/Signature/2002/04/xml-stylesheet.b64",
                                 "/Users/prabath/Downloads/xml-security-1_5_2/samples/data/ie/baltimore/merlin-examples/merlin-xmldsig-twenty-three/xml-stylesheet.b64", "text/plain");
    }

    /**
     * Method engineResolve
     *
     * @param uri
     * @param BaseURI
     *
     * @throws ResourceResolverException
     */
    public XMLSignatureInput engineResolve(Attr uri, String BaseURI)
        throws ResourceResolverException {
        try {
            String URI = uri.getNodeValue();

            if (OfflineResolver._uriMap.containsKey(URI)) {
                String newURI = OfflineResolver._uriMap.get(URI);

                InputStream is = new FileInputStream(newURI);

                 XMLSignatureInput result = new XMLSignatureInput(is);

                // XMLSignatureInput result = new XMLSignatureInput(inputStream);
                result.setSourceURI(URI);
                result.setMIMEType((String) OfflineResolver._mimeMap.get(URI));

                return result;
            } else {
                Object exArgs[] = {"The URI " + URI + " is not configured for offline work"};

                throw new ResourceResolverException("generic.EmptyMessage", exArgs, uri, BaseURI);
            }
        } catch (IOException ex) {
            throw new ResourceResolverException("generic.EmptyMessage", ex, uri, BaseURI);
        }
    }

    /**
     * We resolve http URIs <I>without</I> fragment...
     *
     * @param uri
     * @param BaseURI
     *
     */
    public boolean engineCanResolve(Attr uri, String BaseURI) {

        String uriNodeValue = uri.getNodeValue();
        if (uriNodeValue.equals("") || uriNodeValue.startsWith("#")) {
            return false;
        }

        try {
            URI uriNew = getNewURI(uri.getNodeValue(), BaseURI);
            if (uriNew.getScheme().equals("http")) {
                return true;
            }

        } catch (URISyntaxException ex) {
            //
        }

        return false;
    }

    /**
     * Method register
     *
     * @param URI
     * @param filename
     * @param MIME
     */
    private static void register(String URI, String filename, String MIME) {
        OfflineResolver._uriMap.put(URI, filename);
        OfflineResolver._mimeMap.put(URI, MIME);
    }

    private static URI getNewURI(String uri, String baseURI) throws URISyntaxException {
        URI newUri = null;
        if (baseURI == null || "".equals(baseURI)) {
            newUri = new URI(uri);
        } else {
            newUri = new URI(baseURI).resolve(uri);
        }

        // if the URI contains a fragment, ignore it
        if (newUri.getFragment() != null) {
            URI uriNewNoFrag =
                new URI(newUri.getScheme(), newUri.getSchemeSpecificPart(), null);
            return uriNewNoFrag;
        }
        return newUri;
    }
}
