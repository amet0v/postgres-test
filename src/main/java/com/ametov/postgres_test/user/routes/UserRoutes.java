package com.ametov.postgres_test.user.routes;

public class UserRoutes {
    private final static String ROOT = "/api/v1/user";
    public final static String CREATE = ROOT;
    public final static String SEARCH = ROOT;
    public final static String BY_ID = ROOT + "/{id}";
    public final static String INIT = ROOT + "/init";
}
