/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.content.beans.standard.HippoGalleryImageBean;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface HippoGalleryImageBeanMixin extends HippoGalleryImageBean, HippoBeanMixin {

    @JsonProperty
    @Override
    public int getHeight();

    @JsonProperty
    @Override
    public int getWidth();

}
