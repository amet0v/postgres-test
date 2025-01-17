package com.ametov.postgres_test.user.routes;

import com.ametov.postgres_test.base.routes.BaseRoutes;

public class UserRoutes {
    private final static String ROOT = BaseRoutes.API + "/user";
    public final static String REGISTRATION = BaseRoutes.NOT_SECURED + "/registration";
    public final static String EDIT = ROOT + "/edit";
    public final static String SEARCH = ROOT;
    public final static String BY_ID = ROOT + "/{id}";
    public final static String INIT = ROOT + "/init";

    public final static String NOT_SECURED_INIT = BaseRoutes.NOT_SECURED + "/init";
}
