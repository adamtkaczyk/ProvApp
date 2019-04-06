package com.ita.adamtkaczyk.provapp;

public class AccountGeneral {
    public static final String ACCOUNT_TYPE = "com.ita.adamtkaczyk.provapp";

    /**
     * Account name
     */
    public static final String ACCOUNT_NAME = "ProvApp";

    /**
     * Auth token types
     */
    public static final String AUTHTOKEN_TYPE_READ_ONLY = "Read only";
    public static final String AUTHTOKEN_TYPE_READ_ONLY_LABEL = "Read only access to an ProvApp account";

    public static final String AUTHTOKEN_TYPE_FULL_ACCESS = "Full access";
    public static final String AUTHTOKEN_TYPE_FULL_ACCESS_LABEL = "Full access to an ProvApp account";

    public static final ServerAuthenticate sServerAuthenticate = new DummyServerAuthenticate();
}
