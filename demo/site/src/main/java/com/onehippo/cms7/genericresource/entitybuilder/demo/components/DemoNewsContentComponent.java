/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder.demo.components;

import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.onehippo.cms7.essentials.components.EssentialsContentComponent;

import com.onehippo.cms7.genericresource.entitybuilder.GenericResourceEntityBuilder;
import com.onehippo.cms7.genericresource.entitybuilder.demo.beans.NewsDocument;

public class DemoNewsContentComponent extends EssentialsContentComponent {

    @Override
    public void doBeforeRender(final HstRequest request, final HstResponse response) {
        super.doBeforeRender(request, response);

        contributeResourceEntities(request);
    }

    private void contributeResourceEntities(final HstRequest request) {
        GenericResourceEntityBuilder builder = GenericResourceEntityBuilder.get(request.getRequestContext());

        final NewsDocument document = (NewsDocument) request.getRequestContext().getContentBean();

        if (document != null) {
            builder.setResourceEntity("document", document);
        }
    }
}
