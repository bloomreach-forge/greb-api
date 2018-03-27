/*
 *  Copyright 2018 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.core.sitemenu.EditableMenu;
import org.hippoecm.hst.core.sitemenu.EditableMenuItem;
import org.hippoecm.hst.core.sitemenu.HstSiteMenus;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface EditableMenuMixin extends EditableMenu, CommonMenuMixin {

    @Override
    @JsonIgnore
    EditableMenuItem getSelectMenuItem();

    @Override
    @JsonIgnore
    HstSiteMenus getHstSiteMenus();

    @Override
    @JsonIgnore
    EditableMenuItem getDeepestExpandedItem();

}
