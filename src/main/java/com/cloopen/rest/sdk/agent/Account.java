package com.cloopen.rest.sdk.agent;

import java.util.Map;

/**
 * @see: http://114.55.105.87:9001/p/5a531ff23b8496dd00dcdfec
 */
public class Account extends Component {


    /**
     * 创建子账户
     *
     * @return
     */
    public Map<String, Object> createSubAccount(String friendlyName) {
        //生成sig
        String signature = getSignature();
        //拼接请求包体
        String requsetbody = "<SubAccount><appId>" + agentSDK.getAppId() + "</appId><friendlyName>" + friendlyName + "</friendlyName></SubAccount>";

        String url = getURL("/SubAccounts", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * @param friendlyName
     * @return
     */
    public Map<String, Object> querySubAccount(String friendlyName) {
        //生成sig
        String signature = getSignature();
        //拼接请求包体
        String requsetbody = "<SubAccount><appId>" + agentSDK.getAppId() + "</appId><friendlyName>" + friendlyName + "</friendlyName></SubAccount>";

        String url = getURL("/QuerySubAccountByName", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * 关闭子账户
     *
     * @param subAccountSid
     * @return
     */
    public Map<String, Object> closeSubAccount(String subAccountSid) {
        //生成sig
        String signature = getSignature();
        //拼接请求包体
        String requsetbody = "<SubAccount><subAccountSid>" + subAccountSid + "</subAccountSid></SubAccount>";

        String url = getURL("/CloseSubAccount", signature);
        return sendPost(url, requsetbody);
    }
}
