/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.jcr.Node;

import org.hippoecm.hst.content.beans.standard.HippoAvailableTranslationsBean;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoDocumentBean;
import org.hippoecm.hst.content.beans.standard.HippoFacetNavigationBean;
import org.hippoecm.hst.content.beans.standard.HippoFolderBean;
import org.hippoecm.hst.content.beans.standard.HippoResultSetBean;
import org.hippoecm.hst.provider.jcr.JCRValueProvider;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface DefaultHstContentBeanIgnoreMixin {

    @JsonIgnore
    public Node getNode();

    @JsonIgnore
    public JCRValueProvider getValueProvider();

    @Deprecated
    @JsonIgnore
    public String getLocalizedName();

    @JsonIgnore
    public Map<String, Object> getProperties();

    @JsonIgnore
    public Map<String, Object> getProperty();

    @JsonIgnore
    public HippoBean getParentBean();

    @JsonIgnore
    public <T extends HippoBean> T getCanonicalBean();

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
    public Locale getLocale();

    @JsonIgnore
    public List<HippoFolderBean> getFolders();

    @JsonIgnore
    public List<HippoDocumentBean> getDocuments();

    @JsonIgnore
    public HippoBean getReferencedBean();

    @Deprecated
    @JsonIgnore
    public HippoBean getDeref();

    @JsonIgnore
    public HippoResultSetBean getResultSet();

    @JsonIgnore
    public HippoFacetNavigationBean getRootFacetNavigationBean();

    @JsonIgnore
    public HippoBean getDocument();

    @JsonIgnore
    public String getComparePath();

}
