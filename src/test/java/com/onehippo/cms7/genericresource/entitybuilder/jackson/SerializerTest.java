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

import java.io.StringWriter;

import org.hippoecm.hst.core.component.HstURL;
import org.hippoecm.hst.core.linking.HstLink;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SerializerTest {

    @Mock private HstLink hstLink;
    @Mock private HstURL hstURL;

    // --- HstLinkSerializer ---

    @Test
    void hstLinkSerializer_defaultConstructor_notNull() {
        assertNotNull(new HstLinkSerializer());
    }

    @Test
    void hstLinkSerializer_typeConstructor_notNull() {
        assertNotNull(new HstLinkSerializer(HstLink.class));
    }

    @Test
    void hstLinkSerializer_withPathAndSubPath_writesFields() throws Exception {
        when(hstLink.getPath()).thenReturn("/content/news");
        when(hstLink.getSubPath()).thenReturn("detail");

        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        SerializerProvider provider = new ObjectMapper().getSerializerProviderInstance();

        new HstLinkSerializer().serialize(hstLink, gen, provider);
        gen.flush();

        String json = sw.toString();
        assertTrue(json.contains("path"));
        assertTrue(json.contains("/content/news"));
        assertTrue(json.contains("subPath"));
        assertTrue(json.contains("detail"));
    }

    @Test
    void hstLinkSerializer_withNullPathAndSubPath_omitsFields() throws Exception {
        when(hstLink.getPath()).thenReturn(null);
        when(hstLink.getSubPath()).thenReturn(null);

        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        SerializerProvider provider = new ObjectMapper().getSerializerProviderInstance();

        new HstLinkSerializer().serialize(hstLink, gen, provider);
        gen.flush();

        String json = sw.toString();
        // path and subPath should not be present when null
        assertFalse(json.contains("\"path\""));
        assertFalse(json.contains("\"subPath\""));
        // RequestContextProvider.get() returns null in unit tests — url field is omitted
        assertFalse(json.contains("\"url\""));
    }

    // --- HstURLSerializer ---

    @Test
    void hstURLSerializer_defaultConstructor_notNull() {
        assertNotNull(new HstURLSerializer());
    }

    @Test
    void hstURLSerializer_typeConstructor_notNull() {
        assertNotNull(new HstURLSerializer(HstURL.class));
    }

    @Test
    void hstURLSerializer_writesTypeAndUrl() throws Exception {
        when(hstURL.getType()).thenReturn("action");
        when(hstURL.toString()).thenReturn("http://localhost/page?action=submit");

        StringWriter sw = new StringWriter();
        JsonGenerator gen = new JsonFactory().createGenerator(sw);
        SerializerProvider provider = new ObjectMapper().getSerializerProviderInstance();

        new HstURLSerializer().serialize(hstURL, gen, provider);
        gen.flush();

        String json = sw.toString();
        assertTrue(json.contains("\"type\""));
        assertTrue(json.contains("action"));
        assertTrue(json.contains("\"url\""));
        assertTrue(json.contains("http://localhost/page"));
    }
}
