/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.content.beans.standard.HippoHtmlBean;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface HippoHtmlBeanMixin extends HippoHtmlBean, HippoBeanMixin {

    @JsonProperty("value")
    @Override
    public String getContent();

}
