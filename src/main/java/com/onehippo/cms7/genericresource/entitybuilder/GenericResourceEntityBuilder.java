/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.core.request.HstRequestContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericResourceEntityBuilder {

    private static final String NAME = GenericResourceEntityBuilder.class.getName();

    private static final Object lock = new Object();

    public static GenericResourceEntityBuilder get(final HstRequestContext requestContext) {
        GenericResourceEntityBuilder builder = (GenericResourceEntityBuilder) requestContext.getAttribute(NAME);

        if (builder == null) {
            synchronized (lock) {
                builder = (GenericResourceEntityBuilder) requestContext.getAttribute(NAME);

                if (builder == null) {
                    builder = new GenericResourceEntityBuilder();
                    requestContext.setAttribute(NAME, builder);
                }
            }
        }

        return builder;
    }

    private final Map<String, Object> resourceEntityMap;

    private GenericResourceEntityBuilder() {
        resourceEntityMap = new LinkedHashMap<>();
    }

    @SuppressWarnings("unchecked")
    public void addResourceEntity(String resourcePath, Object resourceEntity) {
        if (resourcePath == null) {
            throw new IllegalArgumentException("resourcePath must be a non-null value.");
        }

        resourcePath = StringUtils.trim(resourcePath);
        Object value = resourceEntityMap.get(resourcePath);

        if (value == null) {
            List<Object> list = new LinkedList<>();
            list.add(resourceEntity);
            value = list;
            resourceEntityMap.put(resourcePath, value);
        } else if (value instanceof Collection) {
            ((Collection<Object>) value).add(resourceEntity);
        } else {
            List<Object> list = new LinkedList<>();
            list.add(value);
            list.add(resourceEntity);
            value = list;
            resourceEntityMap.put(resourcePath, value);
        }
    }

    public void setResourceEntity(String resourcePath, Object resourceEntity) {
        if (resourcePath == null) {
            throw new IllegalArgumentException("resourcePath must be a non-null value.");
        }

        resourcePath = StringUtils.trim(resourcePath);
        resourceEntityMap.put(resourcePath, resourceEntity);
    }

    public void removeResourceEntity(String resourcePath) {
        if (resourcePath == null) {
            throw new IllegalArgumentException("resourcePath must be a non-null value.");
        }

        resourcePath = StringUtils.trim(resourcePath);
        resourceEntityMap.remove(resourcePath);
    }

    public Set<String> getResourceEntityPathNames() {
        return Collections.unmodifiableSet(new LinkedHashSet<String>(resourceEntityMap.keySet()));
    }

    public void write(final ObjectMapper objectMapper, final Writer writer)
            throws JsonGenerationException, JsonMappingException, IOException {
        objectMapper.writeValue(writer, resourceEntityMap);
    }
}
