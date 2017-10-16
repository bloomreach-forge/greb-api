/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.io.IOException;

import org.hippoecm.hst.core.component.HstURL;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class HstURLSerializer extends StdSerializer<HstURL> {

    private static final long serialVersionUID = 1L;

    public HstURLSerializer() {
        this(null);
    }

    public HstURLSerializer(Class<HstURL> type) {
        super(type);
    }

    @Override
    public void serialize(HstURL value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeStartObject();

        gen.writeStringField("type", value.getType());
        gen.writeStringField("url", value.toString());

        gen.writeEndObject();
    }

}