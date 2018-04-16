<#include "../include/imports.ftl">

<#-- @ftlvariable name="item" type="com.onehippo.cms7.genericresource.entitybuilder.demo.beans.NewsDocument" -->
<#-- @ftlvariable name="pageable" type="org.onehippo.cms7.essentials.components.paging.Pageable" -->

<#if pageable?? && pageable.items?has_content>

  <div>
    <a href="<@hst.link mount='grebapi' path='${hstRequest.pathInfo}' />" target="_blank">GREB API Link</a>
  </div>

  <div>
    <#list pageable.items as item>
      <@hst.link var="link" hippobean=item />
      <article class="has-edit-button">
        <@hst.cmseditlink hippobean=item/>
        <h3><a href="${link}">${item.title?html}</a></h3>
        <#if item.date?? && item.date.time??>
          <p><@fmt.formatDate value=item.date.time type="both" dateStyle="medium" timeStyle="short"/></p>
        </#if>
        <p>${item.location?html}</p>
        <p>${item.introduction?html}</p>
      </article>
    </#list>
    <#if cparam.showPagination>
      <#include "../include/pagination.ftl">
    </#if>
  </div>
<#-- @ftlvariable name="editMode" type="java.lang.Boolean"-->
<#elseif editMode>
  <div>
    <img src="<@hst.link path='/images/essentials/catalog-component-icons/news-list.png'/>"> Click to edit News List
  </div>
</#if>


