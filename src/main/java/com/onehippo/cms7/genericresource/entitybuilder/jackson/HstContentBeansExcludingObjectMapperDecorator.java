/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.hippoecm.hst.content.beans.manager.ObjectConverter;
import org.hippoecm.hst.content.beans.standard.HippoAvailableTranslationsBean;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetChildNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.content.beans.standard.HippoMirrorBean;
import org.hippoecm.hst.content.beans.standard.HippoRequestBean;
import org.hippoecm.hst.content.beans.standard.HippoResultSetBean;
import org.hippoecm.hst.content.beans.standard.HippoVirtualOnlyBean;
import org.hippoecm.hst.provider.ValueProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HstContentBeansExcludingObjectMapperDecorator {

    private Map<Class<?>, Class<?>> defaultMixins;
    private Map<Class<?>, Class<?>> extraMixins;

    public HstContentBeansExcludingObjectMapperDecorator() {
        defaultMixins = new LinkedHashMap<>();

        defaultMixins.put(Class.class, DefaultJsonIgnoreTypeMixin.class);

        defaultMixins.put(Session.class, DefaultJsonIgnoreTypeMixin.class);
        defaultMixins.put(Node.class, DefaultJsonIgnoreTypeMixin.class);

        defaultMixins.put(ValueProvider.class, DefaultJsonIgnoreTypeMixin.class);
        defaultMixins.put(ObjectConverter.class, DefaultJsonIgnoreTypeMixin.class);

        defaultMixins.put(HippoBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoFolderBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoFacetNavigationBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoFacetChildNavigationBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoResultSetBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoMirrorBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoDocumentBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoRequestBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoVirtualOnlyBean.class, DefaultHstContentBeanIgnoreMixin.class);
        defaultMixins.put(HippoAvailableTranslationsBean.class, DefaultHstContentBeanIgnoreMixin.class);
    }

    public Map<Class<?>, Class<?>> getDefaultMixins() {
        return defaultMixins;
    }

    public void setDefaultMixins(Map<Class<?>, Class<?>> defaultMixins) {
        this.defaultMixins = defaultMixins;
    }

    public Map<Class<?>, Class<?>> getExtraMixins() {
        return extraMixins;
    }

    public void setExtraMixins(Map<Class<?>, Class<?>> extraMixins) {
        this.extraMixins = extraMixins;
    }

    public ObjectMapper decorate(final ObjectMapper objectMapper) {
        if (defaultMixins != null) {
            defaultMixins.forEach((clazz, mixin) -> {
                objectMapper.addMixIn(clazz, mixin);
            });
        }

        if (extraMixins != null) {
            extraMixins.forEach((clazz, mixin) -> {
                objectMapper.addMixIn(clazz, mixin);
            });
        }

        return objectMapper;
    }
}
