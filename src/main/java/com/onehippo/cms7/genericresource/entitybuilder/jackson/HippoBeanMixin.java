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
public interface HippoBeanMixin {

    @JsonProperty("id")
    public String getIdentifier();

    @JsonIgnore
    public Node getNode();

    @JsonIgnore
    public JCRValueProvider getValueProvider();

    @Deprecated
    @JsonIgnore
    public String getLocalizedName();

    @JsonIgnore
    public String getPath();

    @JsonIgnore
    public Map<String, Object> getProperties();

    @JsonIgnore
    public Map<String, Object> getProperty();

    @JsonIgnore
    public HippoBean getParentBean();

    @JsonIgnore
    public <T extends HippoBean> T getCanonicalBean();

    @JsonIgnore
    public String getCanonicalPath();

    @JsonIgnore
    public String getCanonicalUUID();

    @JsonIgnore
    public boolean isHippoDocumentBean();

    @JsonIgnore
    public boolean isHippoFolderBean();

    @JsonIgnore
    public boolean isLeaf();

    @JsonIgnore
    public <T extends HippoBean> HippoAvailableTranslationsBean<T> getAvailableTranslations();

    @JsonIgnore
    public Map<Object, Object> getEqualComparator();

    @JsonIgnore
    public String getComparePath();
}
