/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.demo.components;

import org.apache.commons.lang.StringUtils;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.parameters.ParametersInfo;
import org.onehippo.cms7.essentials.components.EssentialsNewsComponent;
import org.onehippo.cms7.essentials.components.info.EssentialsNewsComponentInfo;
import org.onehippo.cms7.essentials.components.paging.Pageable;

import com.onehippo.cms7.genericresource.entitybuilder.GenericResourceEntityBuilder;

@ParametersInfo(type = EssentialsNewsComponentInfo.class)
public class DemoNewsListComponent extends EssentialsNewsComponent {

    @Override
    public void doBeforeRender(final HstRequest request, final HstResponse response) {
        super.doBeforeRender(request, response);

        contributeResourceEntities(request);
    }

    private void contributeResourceEntities(final HstRequest request) {
        GenericResourceEntityBuilder builder = GenericResourceEntityBuilder.get(request.getRequestContext());

        final EssentialsNewsComponentInfo paramInfo = getComponentParametersInfo(request);
        builder.setResourceEntity("documentTypes", StringUtils.defaultString(paramInfo.getDocumentTypes()));

        Pageable<? extends HippoBean> pageable = (Pageable<? extends HippoBean>) request.getAttribute(REQUEST_ATTR_PAGEABLE);

        if (pageable != null) {
            builder.setResourceEntity("page", pageable);
        }
    }
}
