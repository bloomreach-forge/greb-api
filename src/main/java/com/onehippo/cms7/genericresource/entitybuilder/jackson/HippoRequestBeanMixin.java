/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoRequestBean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HippoRequestBeanMixin extends HippoRequestBean, HippoBeanMixin {

    @JsonIgnore
    @Override
    public HippoBean getDocument();

}
