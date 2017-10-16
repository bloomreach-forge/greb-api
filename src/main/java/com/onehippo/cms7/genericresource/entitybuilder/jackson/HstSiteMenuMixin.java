/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.core.sitemenu.EditableMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenu;
import org.hippoecm.hst.core.sitemenu.HstSiteMenuItem;
import org.hippoecm.hst.core.sitemenu.HstSiteMenus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface HstSiteMenuMixin extends HstSiteMenu, CommonMenuMixin {

    @JsonIgnore
    @Override
    public HstSiteMenus getHstSiteMenus();

    @JsonIgnore
    @Override
    public HstSiteMenuItem getDeepestExpandedItem();

    @JsonIgnore
    @Override
    public EditableMenu getEditableMenu();
}
