# hippo-addon-generic-resource-entity-builder

Hippo Addon Generic Resource Entity Builder

## Release Version Compatibility

| Plugin Version | CMS Version  |
|:--------------:|:------------:|
| 0.9.x          | 12.x         |

## Build and install the module itself into local maven repository

```bash
    $ mvn clean install
```

## Running the demo locally

The demo project is located udner demo/ folder. So, move to the demo/ folder to run it locally.

```bash
    $ cd demo
    $ mvn clean verify
    $ mvn -P cargo.run
```

After startup, access the CMS at http://localhost:8080/cms and the site at http://localhost:8080/site.
Logs are located in target/tomcat8x/logs

### A Test Scenario

- Visit SITE at http://localhost:8080/site/
- Click on "news" menu.
- Click on "Resource API Link" link on the top. It will show the JSON output result from the configured
  generic resource entity builder API ("resourceapi") mount for the same page.
- Click on a news article.
- Click on "Resource API Link" link on the top. It will show the JSON output result from the configured
  generic resource entity builder API ("resourceapi") mount for the news article in the page.

## Installation

In site/pom.xml, include this dependency:

```xml
    <dependency>
      <groupId>com.onehippo.cms7</groupId>
      <artifactId>hippo-addon-generic-resource-entity-builder</artifactId>
    </dependency>

```

That's all!

## Generic Resource Entity Builder Mount Configuration

Create an ```hst:mount``` node under the ```hst:root``` node. e.g, ```resourceapi```.
And set ```@hst:mountalias``` to ```resourceapi``` for example, and set ```@hst:namedpipeline``` to ```GenericResourceEntitySitePipeline```.

See ```/hst:hst/hst:hosts/dev-localhost/localhost/hst:root/resourceapi``` node of the demo project through CMS Console for details.

## Contributing Resource Entities in HstComponents

See the example ```contributeResourceEntities(HstRequest)``` method implementations in
[DemoNewsContentComponent.java](demo/site/src/main/java/com/onehippo/cms7/genericresource/entitybuilder/demo/components/DemoNewsContentComponent.java) and
[DemoNewsListComponent.java](demo/site/src/main/java/com/onehippo/cms7/genericresource/entitybuilder/demo/components/DemoNewsListComponent.java).

## Enabling Relevance (```TargetingUpdateValve```) in ```GenericResourceEntitySitePipeline```

- Add an xml file in ```site/src/main/resources/META-INF/hst-assembly/overrides/``` folder. e.g, ```generic-resource-entity-site-pipeline-targeting.xml``` and add the following there:

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-4.1.xsd">

  <bean class="org.hippoecm.hst.site.container.TypeDeterminedMethodInvokingFactoryBean">
    <constructor-arg value="java.lang.Void" />
    <property name="targetObject">
      <bean class="org.springframework.beans.factory.config.MethodInvokingFactoryBean">
        <property name="targetObject" ref="org.hippoecm.hst.core.container.Pipelines"/>
        <property name="targetMethod" value="getPipeline"/>
        <property name="arguments" value="GenericResourceEntitySitePipeline"/>
      </bean>
    </property>
    <property name="targetMethod" value="addProcessingValve"/>
    <property name="arguments">
      <bean class="com.onehippo.cms7.targeting.hst.container.TargetingUpdateValve">
        <property name="valveName" value="targetingUpdateValve" />
        <property name="afterValves" value="contextResolvingValve, localizationValve" />
        <property name="beforeValves" value="actionValve, resourceServingValve" />
      </bean>
    </property>
  </bean>

</beans>
```


## Using GenericResourceEntityBuilder API

- You need to get ```GenericResourceEntityBuilder``` first like the following:
```java
    GenericResourceEntityBuilder builder = GenericResourceEntityBuilder.get(RequestContextProvider.get());
```

- You can add scalar values or object values:

```java
    builder.addResourceEntity("title", document.getTitle());
    builder.addResourceEntity("timestamp", System.currentTimeMillis());
    builder.addResourceEntity("document", document);
```

- You can add a container (which is either a ```java.util.Collection``` or ```java.util.Map```):

```java
    List<Object> references = new LinkedList<>();
    references.add(referenceDoc1);
    references.add(referenceDoc2);
    builder.addResourceEntity("references", references);

    Map<String, Object> images = new LinkedHashMap<>();
    images.put("portrait", portrait);
    images.put("landscape", landscape);
    builder.addResourceEntity("images", images);
```

You can nest as many objects as you want through container (either ```java.util.Collection``` or ```java.util.Map```) objects.

- You can retrieve a resource entity object whether it is a scalar value, object value, collection or map value:

```java
    Object value = builder.getResourceEntity(name)
    Collection<Object> collectionValue = builder.getCollectionResourceEntity(name);
    Map<Object> mapValue = builder.getMapResourceEntity(name);
 ```

## Special Output Format Query Parameter

In any URL from the mount configured with the Generic Resource Entity Builder API (e.g, "resourceapi") pipeline,
if you add ```_format=html``` (any other value different from ```_format=json``` actually) query parameter, the pipeline will render the normal
(HTML) output as same as how the parent mount produces.
This can be useful if you want to get an HTML output for an HST Component Rendering URL, for instance.


