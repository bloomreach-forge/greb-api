/*
 *  Copyright 2018 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.core.sitemenu.EditableMenu;
import org.hippoecm.hst.core.sitemenu.EditableMenuItem;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface EditableMenuItemMixin extends EditableMenuItem, CommonMenuItemMixin {

    @Override
    @JsonIgnore
    EditableMenu getEditableMenu();

    @Override
    @JsonIgnore
    EditableMenuItem getParentItem();

}
