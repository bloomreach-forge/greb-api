/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.core.request.HstRequestContext;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Generic Resource Entity Builder.
 */
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

    public Set<String> getResourceEntityNames() {
        return resourceEntityMap.keySet();
    }

    public Object getResourceEntity(String name) throws GenericResourceEntityBuilderException {
        if (name == null) {
            throw new IllegalArgumentException("name must be a non-null value.");
        }

        name = StringUtils.trim(name);
        return resourceEntityMap.get(name);
    }

    public Collection<Object> getCollectionResourceEntity(String name) throws GenericResourceEntityBuilderException {
        Object value = getResourceEntity(name);

        if (value != null) {
            if (value instanceof Collection) {
                return ((Collection<Object>) value);
            } else {
                throw new GenericResourceEntityBuilderException("A non-collection value exists by name, '" + name + "'.");
            }
        }

        return null;
    }

    public Map<String, Object> getMapResourceEntity(String name) throws GenericResourceEntityBuilderException {
        Object value = getResourceEntity(name);

        if (value != null) {
            if (value instanceof Map) {
                return ((Map<String, Object>) value);
            } else {
                throw new GenericResourceEntityBuilderException("A non-map value exists by name, '" + name + "'.");
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public Object addResourceEntity(String name, Object resourceEntity) throws GenericResourceEntityBuilderException {
        if (name == null) {
            throw new IllegalArgumentException("name must be a non-null value.");
        }

        name = StringUtils.trim(name);
        Object value = resourceEntityMap.get(name);

        if (value == null) {
            List<Object> list = new LinkedList<>();
            list.add(resourceEntity);
            value = list;
            return resourceEntityMap.put(name, value);
        } else if (value instanceof Collection) {
            ((Collection<Object>) value).add(resourceEntity);
            return resourceEntity;
        } else {
            throw new GenericResourceEntityBuilderException("A non-collection value already exists by name, '" + name + "'.");
        }
    }

    public Object setResourceEntity(String name, Object resourceEntity) throws GenericResourceEntityBuilderException {
        if (name == null) {
            throw new IllegalArgumentException("name must be a non-null value.");
        }

        name = StringUtils.trim(name);
        return resourceEntityMap.put(name, resourceEntity);
    }

    public Object removeResourceEntity(String name) throws GenericResourceEntityBuilderException {
        if (name == null) {
            throw new IllegalArgumentException("name must be a non-null value.");
        }

        name = StringUtils.trim(name);
        return resourceEntityMap.remove(name);
    }

    public void write(final ObjectMapper objectMapper, final Writer writer)
            throws GenericResourceEntityBuilderException, IOException {
        try {
            objectMapper.writeValue(writer, resourceEntityMap);
        } catch (JsonGenerationException e) {
            throw new GenericResourceEntityBuilderException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new GenericResourceEntityBuilderException(e.getMessage(), e);
        }
    }
}
