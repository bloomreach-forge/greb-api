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

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GenericResourceEntityBuilderExceptionTest {

    @Test
    void defaultConstructor_canBeThrown() {
        assertThrows(GenericResourceEntityBuilderException.class, () -> {
            throw new GenericResourceEntityBuilderException();
        });
    }

    @Test
    void messageConstructor_setsMessage() {
        GenericResourceEntityBuilderException ex = new GenericResourceEntityBuilderException("build failed");
        assertEquals("build failed", ex.getMessage());
    }

    @Test
    void causeConstructor_setsCause() {
        RuntimeException cause = new RuntimeException("root cause");
        GenericResourceEntityBuilderException ex = new GenericResourceEntityBuilderException(cause);
        assertSame(cause, ex.getCause());
    }

    @Test
    void messageAndCauseConstructor_setsBoth() {
        RuntimeException cause = new RuntimeException("underlying");
        GenericResourceEntityBuilderException ex = new GenericResourceEntityBuilderException("wrapper", cause);
        assertEquals("wrapper", ex.getMessage());
        assertSame(cause, ex.getCause());
    }

    @Test
    void isRuntimeException() {
        assertInstanceOf(RuntimeException.class, new GenericResourceEntityBuilderException("x"));
    }
}
