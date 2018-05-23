package com.cloopen.rest.sdk.agent;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.lang.reflect.Field;

/**
 * 文档参见：
 * http://doc.yuntongxun.com/p/5a534cde3b8496dd00dce22d#h4-5-6%20%E5%BA%A7%E5%B8%AD%E5%87%86%E5%A4%87%E5%B0%B1%E7%BB%AA
 */
public class AgentSDK {
    private String AccountSid = "";
    private String AccountToken = "";
    private String AppId = "";
    private String ServerIP = "";
    private String ServerPort = "8883";
    private String SoftVersion = "2013-12-26";
    private String timestamp = "";
    private LoadingCache<String, Component> components;

    private static final String QUEUESDK = "com.cloopen.rest.sdk.agent.Queue";
    private static final String WORKSTATUS = "com.cloopen.rest.sdk.agent.WorkStatus";
    private static final String CALLOPERATION = "com.cloopen.rest.sdk.agent.CallOperation";
    private static final String ACCOUNT = "com.cloopen.rest.sdk.agent.Account";

    public AgentSDK(String accountsSid, String accountToken, String appId, String serverIP) {
        AccountSid = accountsSid;
        AccountToken = accountToken;
        AppId = appId;
        ServerIP = serverIP;
        this.components = CacheBuilder.newBuilder().build(new CacheLoader<String, Component>() {
            public Component load(String classFullName) throws Exception {
                Class clazz = Class.forName(classFullName);
                Object comp = clazz.newInstance();
                AgentSDK.this.injectAgentSDK(clazz, comp);
                return (Component) comp;
            }
        });
    }

    private void injectAgentSDK(Class clazz, Object comp) throws NoSuchFieldException, IllegalAccessException {
        Field field = clazz.getSuperclass().getDeclaredField("agentSDK");
        field.setAccessible(true);
        field.set(comp, this);
    }

    public Queue queue() {
        return (Queue) this.components.getUnchecked(QUEUESDK);
    }

    public WorkStatus workStatus() {
        return (WorkStatus) this.components.getUnchecked(WORKSTATUS);
    }

    public CallOperation callOperation() {
        return (CallOperation) this.components.getUnchecked(CALLOPERATION);
    }

    public Account account() {
        return (Account) this.components.getUnchecked(ACCOUNT);
    }

    public String getAccountSid() {
        return AccountSid;
    }

    public void setAccountSid(String accountSid) {
        AccountSid = accountSid;
    }

    public String getAccountToken() {
        return AccountToken;
    }

    public void setAccountToken(String accountToken) {
        AccountToken = accountToken;
    }

    public String getAppId() {
        return AppId;
    }

    public void setAppId(String appId) {
        AppId = appId;
    }

    public String getServerIP() {
        return ServerIP;
    }

    public void setServerIP(String serverIP) {
        ServerIP = serverIP;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getServerPort() {
        return ServerPort;
    }

    public String getSoftVersion() {
        return SoftVersion;
    }
}
