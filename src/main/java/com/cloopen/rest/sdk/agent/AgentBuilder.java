package com.cloopen.rest.sdk.agent;

public class AgentBuilder {

    private AgentSDK agentSDK;

    public static AgentBuilder newBuilder(String accountsSid, String accountToken, String appId, String serverIP) {
        checkNotNullAndEmpty(accountsSid, "accountsSid");
        checkNotNullAndEmpty(accountToken, "accountToken");
        checkNotNullAndEmpty(appId, "appId");
        checkNotNullAndEmpty(serverIP, "serverIP");
        AgentBuilder builder = new AgentBuilder();
        builder.agentSDK = new AgentSDK(accountsSid, accountToken, appId, serverIP);
        return builder;
    }

    public AgentSDK build() {
        return this.agentSDK;
    }

    private static void checkNotNullAndEmpty(String checking, String field) {
        if (checking == null || "".equals(checking)) {
            throw new IllegalArgumentException(field + " can't be null or empty");
        }
    }
}
