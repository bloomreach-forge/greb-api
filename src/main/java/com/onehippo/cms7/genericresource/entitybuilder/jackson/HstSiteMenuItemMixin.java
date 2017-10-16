/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.Map;

import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenuItem;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HstSiteMenuItemMixin extends HstSiteMenuItem, CommonMenuItemMixin {

    @JsonIgnore
    @Override
    public HstSiteMenuItem getParentItem();

    @JsonIgnore
    @Override
    public HstSiteMenu getHstSiteMenu();

    @JsonIgnore
    @Override
    public Map<String, String> getLocalParameters();

    @JsonIgnore
    public HstSiteMenuItem getDeepestExpandedItem();

}
