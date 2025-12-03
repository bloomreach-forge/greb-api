/*
 *  Copyright 2017-2025 BloomReach, Inc. (https://www.bloomreach.com)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package com.onehippo.cms7.genericresource.entitybuilder.container;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import jakarta.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Strings;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.container.AggregationValve;
import org.hippoecm.hst.core.container.ContainerException;
import org.hippoecm.hst.core.container.HstComponentWindow;
import org.hippoecm.hst.core.container.HstContainerConfig;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onehippo.cms7.genericresource.entitybuilder.GenericResourceEntityBuilder;
import com.onehippo.cms7.genericresource.entitybuilder.GenericResourceEntityBuilderException;

public class GenericResourceEntityAggregationValve extends AggregationValve {

    private static final Logger log = LoggerFactory.getLogger(GenericResourceEntityAggregationValve.class);

    private static final String DEFAULT_OUTPUT_FORMAT_PARAM_NAME = "_format";
    private static final String OUTPUT_FORMAT_JSON = "JSON";

    private ObjectMapper objectMapper;
    private String outputFormatParamName = DEFAULT_OUTPUT_FORMAT_PARAM_NAME;

    public ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public String getOutputFormatParamName() {
        return outputFormatParamName;
    }

    public void setOutputFormatParamName(String outputFormatParamName) {
        this.outputFormatParamName = outputFormatParamName;
    }

    @Override
    protected void processWindowsRender(final HstContainerConfig requestContainerConfig,
            final HstComponentWindow[] sortedComponentWindows, final Map<HstComponentWindow, HstRequest> requestMap,
            final Map<HstComponentWindow, HstResponse> responseMap) throws ContainerException {

        final HstRequestContext requestContext = RequestContextProvider.get();
        final String paramName = getOutputFormatParamName();

        if (StringUtils.isNotBlank(paramName)) {
            final String paramValue = requestContext.getServletRequest().getParameter(paramName);

            if (StringUtils.isNotBlank(paramValue) && ! Strings.CI.equals(OUTPUT_FORMAT_JSON, paramValue)) {
                super.processWindowsRender(requestContainerConfig, sortedComponentWindows, requestMap, responseMap);
                return;
            }
        }

        final HttpServletResponse response = requestContext.getServletResponse();

        GenericResourceEntityBuilder builder = GenericResourceEntityBuilder.get(requestContext);

        PrintWriter writer = null;

        try {
            response.setContentType("application/json");
            writer = response.getWriter();
            builder.write(getObjectMapper(), writer);
        } catch (GenericResourceEntityBuilderException e) {
            log.warn("Failed to generate json.", e);
        } catch (IOException e) {
            log.warn("Failed to write json.", e);
        } finally {
            IOUtils.closeQuietly(writer);
        }
    }
}
