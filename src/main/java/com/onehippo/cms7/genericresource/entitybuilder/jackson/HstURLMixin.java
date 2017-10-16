/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.jackson;

import org.hippoecm.hst.core.component.HstURL;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(using = HstURLSerializer.class)
public interface HstURLMixin extends HstURL {

}
