/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.content.beans.standard.HippoGalleryImageBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSetBean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.None.class)
public interface HippoGalleryImageSetBeanMixin extends HippoGalleryImageSetBean, HippoDocumentBeanMixin {

    @JsonProperty
    @Override
    String getCanonicalHandleUUID();

    @JsonProperty
    @Override
    String getCanonicalHandlePath();

    @JsonProperty
    @Override
    public String getFileName();

    @JsonProperty
    @Override
    public String getDescription();

    @JsonProperty
    @Override
    public HippoGalleryImageBean getThumbnail();

    @JsonProperty
    @Override
    public HippoGalleryImageBean getOriginal();

}
