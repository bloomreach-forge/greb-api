/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.Map;

import javax.jcr.Node;

import org.hippoecm.hst.content.beans.standard.HippoAvailableTranslationsBean;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.provider.jcr.JCRValueProvider;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public interface HippoBeanMixin extends HippoBean {

    @JsonProperty("id")
    @Override
    public String getIdentifier();

    @JsonIgnore
    @Override
    public Node getNode();

    @JsonIgnore
    @Override
    public JCRValueProvider getValueProvider();

    @Deprecated
    @JsonIgnore
    @Override
    public String getLocalizedName();

    @JsonIgnore
    @Override
    public Map<String, Object> getProperties();

    @JsonIgnore
    @Override
    public Map<String, Object> getProperty();

    @JsonIgnore
    @Override
    public HippoBean getParentBean();

    @JsonIgnore
    @Override
    public <T extends HippoBean> T getCanonicalBean();

    @JsonIgnore
    @Override
    public boolean isHippoDocumentBean();

    @JsonIgnore
    @Override
    public boolean isHippoFolderBean();

    @JsonIgnore
    @Override
    public boolean isLeaf();

    @JsonIgnore
    @Override
    public <T extends HippoBean> HippoAvailableTranslationsBean<T> getAvailableTranslations();

    @JsonIgnore
    @Override
    public Map<Object, Object> getEqualComparator();

}
