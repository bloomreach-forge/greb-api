package com.onehippo.cms7.genericresource.entitybuilder.demo.beans;
/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import java.util.Calendar;
import java.util.List;

import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;

@HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:newsdocument")
@Node(jcrType="hippoaddongenericresourceentitybuilderdemo:newsdocument")
public class NewsDocument extends HippoDocument {

    /**
     * The document type of the news document.
     */
    public final static String DOCUMENT_TYPE = "hippoaddongenericresourceentitybuilderdemo:newsdocument";

    private final static String TITLE = "hippoaddongenericresourceentitybuilderdemo:title";
    private final static String DATE = "hippoaddongenericresourceentitybuilderdemo:date";
    private final static String INTRODUCTION = "hippoaddongenericresourceentitybuilderdemo:introduction";
    private final static String IMAGE = "hippoaddongenericresourceentitybuilderdemo:image";
    private final static String CONTENT = "hippoaddongenericresourceentitybuilderdemo:content";
    private final static String LOCATION = "hippoaddongenericresourceentitybuilderdemo:location";
    private final static String AUTHOR = "hippoaddongenericresourceentitybuilderdemo:author";
    private final static String SOURCE = "hippoaddongenericresourceentitybuilderdemo:source";

    /**
     * Get the title of the document.
     *
     * @return the title
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:title")
    public String getTitle() {
        return getProperty(TITLE);
    }

    /**
     * Get the date of the document.
     *
     * @return the date
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:date")
    public Calendar getDate() {
        return getProperty(DATE);
    }

    /**
     * Get the introduction of the document.
     *
     * @return the introduction
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:introduction")
    public String getIntroduction() {
        return getProperty(INTRODUCTION);
    }

    /**
     * Get the image of the document.
     *
     * @return the image
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:image")
    public HippoGalleryImageSet getImage() {
        return getLinkedBean(IMAGE, HippoGalleryImageSet.class);
    }

    /**
     * Get the main content of the document.
     *
     * @return the content
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:content")
    public HippoHtml getContent() {
        return getHippoHtml(CONTENT);
    }

    /**
     * Get the location of the document.
     *
     * @return the location
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:location")
    public String getLocation() {
        return getProperty(LOCATION);
    }

    /**
     * Get the author of the document.
     *
     * @return the author
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:author")
    public String getAuthor() {
        return getProperty(AUTHOR);
    }

    /**
     * Get the source of the document.
     *
     * @return the source
     */
    @HippoEssentialsGenerated(internalName = "hippoaddongenericresourceentitybuilderdemo:source")
    public String getSource() {
        return getProperty(SOURCE);
    }

    //@JsonIgnore
    public List<HippoDocument> getRelatedDocuments() {
        return getLinkedBeans("hippoaddongenericresourceentitybuilderdemo:relateddocs", HippoDocument.class);
    }
}

