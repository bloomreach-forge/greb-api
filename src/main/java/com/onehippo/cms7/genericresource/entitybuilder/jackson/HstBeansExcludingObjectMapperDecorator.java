/*
 *  Copyright 2017-2019 BloomReach, Inc. (https://www.bloomreach.com)
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

import java.util.LinkedHashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Session;

import org.hippoecm.hst.content.beans.manager.ObjectConverter;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;
import org.hippoecm.hst.content.beans.standard.HippoHtmlBean;
import org.hippoecm.hst.content.beans.standard.HippoMirrorBean;
import org.hippoecm.hst.content.beans.standard.HippoRequestBean;
import org.hippoecm.hst.core.component.HstURL;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.sitemenu.EditableMenu;
import org.hippoecm.hst.core.sitemenu.EditableMenuItem;
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenuItem;
import org.hippoecm.hst.provider.ValueProvider;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HstBeansExcludingObjectMapperDecorator {

    private Map<Class<?>, Class<?>> defaultMixins;
    private Map<Class<?>, Class<?>> extraMixins;

    public HstBeansExcludingObjectMapperDecorator() {
        defaultMixins = new LinkedHashMap<>();

        defaultMixins.put(Session.class, DefaultJsonIgnoreTypeMixin.class);
        defaultMixins.put(Node.class, DefaultJsonIgnoreTypeMixin.class);

        defaultMixins.put(ValueProvider.class, DefaultJsonIgnoreTypeMixin.class);
        defaultMixins.put(ObjectConverter.class, DefaultJsonIgnoreTypeMixin.class);

        defaultMixins.put(HippoGalleryImageBean.class, HippoGalleryImageBeanMixin.class);
        defaultMixins.put(HippoGalleryImageSetBean.class, HippoGalleryImageSetBeanMixin.class);
        defaultMixins.put(HippoHtmlBean.class, HippoHtmlBeanMixin.class);
        defaultMixins.put(HippoRequestBean.class, HippoRequestBeanMixin.class);
        defaultMixins.put(HippoMirrorBean.class, HippoMirrorBeanMixin.class);
        defaultMixins.put(HippoFolderBean.class, HippoFolderBeanMixin.class);
        defaultMixins.put(HippoDocumentBean.class, HippoDocumentBeanMixin.class);
        defaultMixins.put(HippoBean.class, HippoBeanMixin.class);

        defaultMixins.put(HstURL.class, HstURLMixin.class);
        defaultMixins.put(HstLink.class, HstLinkMixin.class);
        defaultMixins.put(HstSiteMenuItem.class, HstSiteMenuItemMixin.class);
        defaultMixins.put(HstSiteMenu.class, HstSiteMenuMixin.class);
        defaultMixins.put(EditableMenuItem.class, EditableMenuItemMixin.class);
        defaultMixins.put(EditableMenu.class, EditableMenuMixin.class);
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
