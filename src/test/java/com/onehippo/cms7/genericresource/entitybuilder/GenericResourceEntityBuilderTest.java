/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder;

import java.io.StringWriter;

import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.mock.core.request.MockHstRequestContext;
import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericResourceEntityBuilderTest {

    private HstRequestContext requestContext;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        requestContext = new MockHstRequestContext();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testJsonOut() throws Exception {
        GenericResourceEntityBuilder builder = GenericResourceEntityBuilder.get(requestContext);

        builder.setResourceEntity("name", "Jane Doe");
        builder.setResourceEntity("age", 33);
        builder.setResourceEntity("gender", "female");

        Address addr = new Address();
        addr.setState("11 Oosteinde");
        addr.setCity("Amsterdam");
        addr.setCountry("Netherlands");
        builder.addResourceEntity("addresses", addr);

        addr = new Address();
        addr.setState("71 Summer St");
        addr.setCity("Boston");
        addr.setCountry("USA");
        builder.addResourceEntity("addresses", addr);

        StringWriter writer = new StringWriter();
        builder.write(objectMapper, writer);
        System.out.println(writer.toString());
    }

    public static class Address {

        private String street;
        private String city;
        private String state;
        private String country;

        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

    }
}
