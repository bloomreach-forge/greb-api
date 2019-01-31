/*
 *  Copyright 2017-2019 BloomReach, Inc. (https://www.bloomreach.com)
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
package com.onehippo.cms7.genericresource.entitybuilder;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.hippoecm.hst.core.request.HstRequestContext;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Abstract Generic Resource Entity Builder.
 */
public abstract class GenericResourceEntityBuilder {

    private static final String NAME = GenericResourceEntityBuilder.class.getName();

    private static final Object lock = new Object();

    public static GenericResourceEntityBuilder get(final HstRequestContext requestContext) {
        GenericResourceEntityBuilder builder = (GenericResourceEntityBuilder) requestContext.getAttribute(NAME);

        if (builder == null) {
            synchronized (lock) {
                builder = (GenericResourceEntityBuilder) requestContext.getAttribute(NAME);

                if (builder == null) {
                    builder = new DefaultGenericResourceEntityBuilder();
                    requestContext.setAttribute(NAME, builder);
                }
            }
        }

        return builder;
    }

    public abstract Set<String> getResourceEntityNames();

    public abstract Object getResourceEntity(String name) throws GenericResourceEntityBuilderException;

    public abstract Collection<Object> getCollectionResourceEntity(String name)
            throws GenericResourceEntityBuilderException;

    public abstract Map<String, Object> getMapResourceEntity(String name) throws GenericResourceEntityBuilderException;

    public abstract Object addResourceEntity(String name, Object resourceEntity)
            throws GenericResourceEntityBuilderException;

    public abstract Object setResourceEntity(String name, Object resourceEntity)
            throws GenericResourceEntityBuilderException;

    public abstract Object removeResourceEntity(String name) throws GenericResourceEntityBuilderException;

    public abstract void ensureObjectMapperMixins(Map<Class<?>, Class<?>> mixins);

    public abstract void write(final ObjectMapper objectMapper, final Writer writer)
            throws GenericResourceEntityBuilderException, IOException;

}
