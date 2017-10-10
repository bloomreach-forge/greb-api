/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.container;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
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

    private static Logger log = LoggerFactory.getLogger(GenericResourceEntityAggregationValve.class);

    private ObjectMapper objectMapper;

    public ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
        }
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void processWindowsRender(final HstContainerConfig requestContainerConfig,
            final HstComponentWindow[] sortedComponentWindows, final Map<HstComponentWindow, HstRequest> requestMap,
            final Map<HstComponentWindow, HstResponse> responseMap) throws ContainerException {

        final HstRequestContext requestContext = RequestContextProvider.get();
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
