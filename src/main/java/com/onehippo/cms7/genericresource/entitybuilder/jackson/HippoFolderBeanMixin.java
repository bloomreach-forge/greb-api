/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.List;
import java.util.Locale;

import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HippoFolderBeanMixin extends HippoFolderBean, HippoBeanMixin {

    @JsonIgnore
    @Override
    public List<HippoFolderBean> getFolders();

    @JsonIgnore
    @Override
    public List<HippoDocumentBean> getDocuments();

    @JsonIgnore
    @Override
    public Locale getLocale();

}
