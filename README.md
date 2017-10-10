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
