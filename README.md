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
