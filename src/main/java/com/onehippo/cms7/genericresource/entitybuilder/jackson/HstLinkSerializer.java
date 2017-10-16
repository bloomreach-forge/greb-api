/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.io.IOException;

import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.request.HstRequestContext;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class HstLinkSerializer extends StdSerializer<HstLink> {

    private static final long serialVersionUID = 1L;

    public HstLinkSerializer() {
        this(null);
    }

    public HstLinkSerializer(Class<HstLink> type) {
        super(type);
    }

    @Override
    public void serialize(HstLink value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        final HstRequestContext requestContext = RequestContextProvider.get();

        gen.writeStartObject();

        if (value.getPath() != null) {
            gen.writeStringField("path", value.getPath());
        }
        if (value.getSubPath() != null) {
            gen.writeStringField("subPath", value.getSubPath());
        }
        if (requestContext != null) {
            gen.writeStringField("url", value.toUrlForm(requestContext, true));
        }

        gen.writeEndObject();
    }

}