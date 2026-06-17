/*
 *  Copyright 2025 BloomReach, Inc. (https://www.bloomreach.com)
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
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstURL;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class HstBeansExcludingObjectMapperDecoratorTest {

    @Test
    void constructor_populatesDefaultMixinsMap() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        assertNotNull(decorator.getDefaultMixins());
        assertFalse(decorator.getDefaultMixins().isEmpty());
    }

    @Test
    void constructor_includesSessionMixin() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        assertTrue(decorator.getDefaultMixins().containsKey(Session.class));
    }

    @Test
    void constructor_includesNodeMixin() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        assertTrue(decorator.getDefaultMixins().containsKey(Node.class));
    }

    @Test
    void constructor_includesHippoBeanMixin() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        assertTrue(decorator.getDefaultMixins().containsKey(HippoBean.class));
    }

    @Test
    void constructor_includesHstUrlMixin() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        assertTrue(decorator.getDefaultMixins().containsKey(HstURL.class));
    }

    @Test
    void getExtraMixins_defaultsToNull() {
        assertNull(new HstBeansExcludingObjectMapperDecorator().getExtraMixins());
    }

    @Test
    void setDefaultMixins_replacesMap() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        Map<Class<?>, Class<?>> replacement = Map.of(String.class, Object.class);
        decorator.setDefaultMixins(replacement);
        assertSame(replacement, decorator.getDefaultMixins());
    }

    @Test
    void setExtraMixins_storesMap() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        Map<Class<?>, Class<?>> extras = Map.of(Integer.class, Object.class);
        decorator.setExtraMixins(extras);
        assertSame(extras, decorator.getExtraMixins());
    }

    @Test
    void decorate_withNullDefaultMixins_doesNotThrow() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        decorator.setDefaultMixins(null);
        ObjectMapper mapper = new ObjectMapper();
        assertDoesNotThrow(() -> decorator.decorate(mapper));
        assertSame(mapper, decorator.decorate(mapper));
    }

    @Test
    void decorate_appliesDefaultMixinsToObjectMapper() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        ObjectMapper mapper = new ObjectMapper();
        decorator.decorate(mapper);
        assertNotNull(mapper.findMixInClassFor(Session.class));
        assertNotNull(mapper.findMixInClassFor(Node.class));
    }

    @Test
    void decorate_appliesExtraMixinsToObjectMapper() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        decorator.setExtraMixins(Map.of(String.class, Object.class));
        ObjectMapper mapper = new ObjectMapper();
        decorator.decorate(mapper);
        assertNotNull(mapper.findMixInClassFor(String.class));
    }

    @Test
    void decorate_withNullExtraMixins_doesNotThrow() {
        HstBeansExcludingObjectMapperDecorator decorator = new HstBeansExcludingObjectMapperDecorator();
        decorator.setExtraMixins(null);
        assertDoesNotThrow(() -> decorator.decorate(new ObjectMapper()));
    }
}
