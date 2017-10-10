/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.Locale;

import org.hippoecm.hst.content.beans.standard.HippoAvailableTranslationsBean;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HippoDocumentBeanMixin extends HippoDocumentBean, HippoBeanMixin {

    @JsonIgnore
    @Override
    public <T extends HippoBean> HippoAvailableTranslationsBean<T> getAvailableTranslations(Class<T> beanMappingClass);

    @JsonIgnore
    @Override
    public Locale getLocale();

}
