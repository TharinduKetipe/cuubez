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
package com.cuubez.core.engine.parser.url;

import com.cuubez.core.context.ConfigurationContext;
import com.cuubez.core.exception.CuubezException;
import com.cuubez.core.engine.parser.Parser;

public interface URLParser extends Parser {

    public void parse(ConfigurationContext configurationContext) throws CuubezException;

}
