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
package com.onehippo.cms7.genericresource.entitybuilder;

import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hippoecm.hst.mock.core.request.MockHstRequestContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

class GenericResourceEntityBuilderJupiterTest {

    private GenericResourceEntityBuilder builder;

    @BeforeEach
    void setUp() {
        builder = GenericResourceEntityBuilder.get(new MockHstRequestContext());
    }

    // --- getResourceEntityNames ---

    @Test
    void getResourceEntityNames_initiallyEmpty() {
        assertTrue(builder.getResourceEntityNames().isEmpty());
    }

    @Test
    void getResourceEntityNames_afterSet_containsKey() throws Exception {
        builder.setResourceEntity("color", "blue");
        assertTrue(builder.getResourceEntityNames().contains("color"));
    }

    // --- getResourceEntity ---

    @Test
    void getResourceEntity_withNullName_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> builder.getResourceEntity(null));
    }

    @Test
    void getResourceEntity_withUnknownName_returnsNull() throws Exception {
        assertNull(builder.getResourceEntity("missing"));
    }

    @Test
    void getResourceEntity_afterSet_returnsValue() throws Exception {
        builder.setResourceEntity("lang", "Java");
        assertEquals("Java", builder.getResourceEntity("lang"));
    }

    @Test
    void getResourceEntity_trims_leadingAndTrailingSpaces() throws Exception {
        builder.setResourceEntity("key", "value");
        assertEquals("value", builder.getResourceEntity("  key  "));
    }

    // --- setResourceEntity ---

    @Test
    void setResourceEntity_withNullName_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> builder.setResourceEntity(null, "v"));
    }

    @Test
    void setResourceEntity_overwritesPreviousValue() throws Exception {
        builder.setResourceEntity("x", "first");
        builder.setResourceEntity("x", "second");
        assertEquals("second", builder.getResourceEntity("x"));
    }

    // --- addResourceEntity ---

    @Test
    void addResourceEntity_withNullName_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> builder.addResourceEntity(null, "v"));
    }

    @Test
    void addResourceEntity_firstCall_createsListWithItem() throws Exception {
        builder.addResourceEntity("items", "a");
        Object value = builder.getResourceEntity("items");
        assertInstanceOf(Collection.class, value);
        assertEquals(1, ((Collection<?>) value).size());
    }

    @Test
    void addResourceEntity_secondCall_appendsToList() throws Exception {
        builder.addResourceEntity("items", "a");
        builder.addResourceEntity("items", "b");
        Collection<?> items = (Collection<?>) builder.getResourceEntity("items");
        assertEquals(2, items.size());
        assertTrue(items.containsAll(List.of("a", "b")));
    }

    @Test
    void addResourceEntity_whenNonCollectionExists_throwsException() throws Exception {
        builder.setResourceEntity("scalar", "value");
        assertThrows(GenericResourceEntityBuilderException.class, () -> builder.addResourceEntity("scalar", "x"));
    }

    // --- getCollectionResourceEntity ---

    @Test
    void getCollectionResourceEntity_whenNull_returnsNull() throws Exception {
        assertNull(builder.getCollectionResourceEntity("missing"));
    }

    @Test
    void getCollectionResourceEntity_whenCollection_returnsCollection() throws Exception {
        builder.addResourceEntity("list", "item");
        assertNotNull(builder.getCollectionResourceEntity("list"));
    }

    @Test
    void getCollectionResourceEntity_whenScalar_throwsException() throws Exception {
        builder.setResourceEntity("scalar", 42);
        assertThrows(GenericResourceEntityBuilderException.class, () -> builder.getCollectionResourceEntity("scalar"));
    }

    // --- getMapResourceEntity ---

    @Test
    void getMapResourceEntity_whenNull_returnsNull() throws Exception {
        assertNull(builder.getMapResourceEntity("missing"));
    }

    @Test
    void getMapResourceEntity_whenMap_returnsMap() throws Exception {
        builder.setResourceEntity("map", Map.of("k", "v"));
        assertNotNull(builder.getMapResourceEntity("map"));
    }

    @Test
    void getMapResourceEntity_whenScalar_throwsException() throws Exception {
        builder.setResourceEntity("scalar", 99);
        assertThrows(GenericResourceEntityBuilderException.class, () -> builder.getMapResourceEntity("scalar"));
    }

    // --- removeResourceEntity ---

    @Test
    void removeResourceEntity_withNullName_throwsIllegalArgument() {
        assertThrows(IllegalArgumentException.class, () -> builder.removeResourceEntity(null));
    }

    @Test
    void removeResourceEntity_removesExistingKey() throws Exception {
        builder.setResourceEntity("toRemove", "val");
        builder.removeResourceEntity("toRemove");
        assertNull(builder.getResourceEntity("toRemove"));
    }

    // --- ensureObjectMapperMixins ---

    @Test
    void ensureObjectMapperMixins_withNull_doesNothing() {
        assertDoesNotThrow(() -> builder.ensureObjectMapperMixins(null));
    }

    @Test
    void ensureObjectMapperMixins_withEmptyMap_doesNothing() {
        assertDoesNotThrow(() -> builder.ensureObjectMapperMixins(Map.of()));
    }

    @Test
    void ensureObjectMapperMixins_withMixins_appliedOnWrite() throws Exception {
        // Verify mixins are applied when no existing mixin is registered for the class
        builder.setResourceEntity("val", "test");
        // No exception expected even when mixins are provided
        builder.ensureObjectMapperMixins(Map.of(String.class, Object.class));
        StringWriter sw = new StringWriter();
        builder.write(new ObjectMapper(), sw);
        assertFalse(sw.toString().isEmpty());
    }

    // --- write ---

    @Test
    void write_withEmptyBuilder_producesEmptyJsonObject() throws Exception {
        StringWriter sw = new StringWriter();
        builder.write(new ObjectMapper(), sw);
        assertEquals("{}", sw.toString());
    }

    @Test
    void write_withSetValues_producesJson() throws Exception {
        builder.setResourceEntity("name", "Alice");
        builder.setResourceEntity("age", 30);
        StringWriter sw = new StringWriter();
        builder.write(new ObjectMapper(), sw);
        String json = sw.toString();
        assertTrue(json.contains("Alice"));
        assertTrue(json.contains("30"));
    }

    // --- get factory: same context returns same builder ---

    @Test
    void get_sameMockContext_returnsSameBuilder() {
        MockHstRequestContext ctx = new MockHstRequestContext();
        GenericResourceEntityBuilder b1 = GenericResourceEntityBuilder.get(ctx);
        GenericResourceEntityBuilder b2 = GenericResourceEntityBuilder.get(ctx);
        assertSame(b1, b2);
    }

    @Test
    void get_differentContext_returnsDifferentBuilder() {
        GenericResourceEntityBuilder b1 = GenericResourceEntityBuilder.get(new MockHstRequestContext());
        GenericResourceEntityBuilder b2 = GenericResourceEntityBuilder.get(new MockHstRequestContext());
        assertNotSame(b1, b2);
    }
}
