/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoMirrorBean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HippoMirrorBeanMixin extends HippoMirrorBean, HippoBeanMixin {

    @JsonIgnore
    @Override
    public HippoBean getReferencedBean();

}
