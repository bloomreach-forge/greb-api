/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import java.util.Map;

import org.hippoecm.hst.core.sitemenu.CommonMenuItem;

import com.fasterxml.jackson.annotation.JsonIgnore;

public interface CommonMenuItemMixin extends CommonMenuItem {

    @JsonIgnore
    @Override
    public Map<String, Object> getProperties();

}
