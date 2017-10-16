/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.core.sitemenu.CommonMenu;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CommonMenuMixin extends CommonMenu {

    @JsonIgnore
    @Override
    public boolean isExpanded();

}
