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
package com.onehippo.cms7.genericresource.entitybuilder;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

/**
 * Default Generic Resource Entity Builder implementation.
 */
class DefaultGenericResourceEntityBuilder extends GenericResourceEntityBuilder {

    private final Map<String, Object> resourceEntityMap;

    private final Map<Class<?>, Class<?>> additionalMixins;

    DefaultGenericResourceEntityBuilder() {
        resourceEntityMap = new LinkedHashMap<>();
        additionalMixins = new LinkedHashMap<>();
    }

    @Override
    public Set<String> getResourceEntityNames() {
        return resourceEntityMap.keySet();
    }

    @Override
    public Object getResourceEntity(String name) throws GenericResourceEntityBuilderException {
        if (name == null) {
            throw new IllegalArgumentException("name must be a non-null value.");
        }

        name = StringUtils.trim(name);
        return resourceEntityMap.get(name);
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<Object> getCollectionResourceEntity(String name) throws GenericResourceEntityBuilderException {
        Object value = getResourceEntity(name);

        if (value != null) {
            if (value instanceof Collection) {
                return ((Collection<Object>) value);
            } else {
                throw new GenericResourceEntityBuilderException(
                        "A non-collection value exists by name, '" + name + "'.");
            }
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    @Override
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
    @Override
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
            throw new GenericResourceEntityBuilderException(
                    "A non-collection value already exists by name, '" + name + "'.");
        }
    }

    @Override
    public Object setResourceEntity(String name, Object resourceEntity) throws GenericResourceEntityBuilderException {
        if (name == null) {
            throw new IllegalArgumentException("name must be a non-null value.");
        }

        name = StringUtils.trim(name);
        return resourceEntityMap.put(name, resourceEntity);
    }

    @Override
    public Object removeResourceEntity(String name) throws GenericResourceEntityBuilderException {
        if (name == null) {
            throw new IllegalArgumentException("name must be a non-null value.");
        }

        name = StringUtils.trim(name);
        return resourceEntityMap.remove(name);
    }

    @Override
    public void ensureObjectMapperMixins(Map<Class<?>, Class<?>> additionalMixins) {
        if (additionalMixins == null || additionalMixins.isEmpty()) {
            return;
        }

        this.additionalMixins.putAll(additionalMixins);
    }

    @Override
    public void write(final ObjectMapper objectMapper, final Writer writer)
            throws GenericResourceEntityBuilderException, IOException {
        try {
            if (!additionalMixins.isEmpty()) {
                additionalMixins.forEach((clazz, mixin) -> {
                    if (objectMapper.findMixInClassFor(clazz) == null) {
                        objectMapper.addMixIn(clazz, mixin);
                    }
                });
            }

            objectMapper.writeValue(writer, resourceEntityMap);
        } catch (JsonGenerationException e) {
            throw new GenericResourceEntityBuilderException(e.getMessage(), e);
        } catch (JsonMappingException e) {
            throw new GenericResourceEntityBuilderException(e.getMessage(), e);
        }
    }
}
