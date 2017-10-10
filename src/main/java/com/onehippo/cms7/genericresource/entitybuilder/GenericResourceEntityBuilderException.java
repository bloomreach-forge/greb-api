/*
 *  Copyright 2017 Hippo B.V. (http://www.onehippo.com)
 */
package com.onehippo.cms7.genericresource.entitybuilder;

public class GenericResourceEntityBuilderException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public GenericResourceEntityBuilderException() {
        super();
    }

    public GenericResourceEntityBuilderException(String message) {
        super(message);
    }

    public GenericResourceEntityBuilderException(Throwable nested) {
        super(nested);
    }

    public GenericResourceEntityBuilderException(String msg, Throwable nested) {
        super(msg, nested);
    }
}
