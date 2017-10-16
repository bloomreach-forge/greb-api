/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.configuration.sitemap.HstSiteMapItem;
import org.hippoecm.hst.core.linking.HstLink;

import com.fasterxml.jackson.annotation.JsonIgnore;

//@JsonAppend(props = { @JsonAppend.Prop(value = HstLinkUrlPropertyWriter.class, name = "URL") })
public interface HstLinkMixin extends HstLink {

    @JsonIgnore
    @Override
    public String getSubPath();

    @JsonIgnore
    @Override
    public boolean getContainerResource();

    @JsonIgnore
    @Override
    public boolean isContainerResource();

    @JsonIgnore
    @Override
    public String[] getPathElements();

    @JsonIgnore
    @Override
    public Mount getMount();

    @JsonIgnore
    @Override
    public HstSiteMapItem getHstSiteMapItem();

    @JsonIgnore
    @Override
    public boolean isNotFound();
}
