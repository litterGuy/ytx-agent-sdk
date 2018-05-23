package com.cloopen.rest.sdk.agent;

import java.util.Map;

public class WorkStatus extends Component {

    /**
     * 座席上班
     *
     * @param number     座席号码
     * @param agentid    座席ID
     * @param agenttype  座席所在队列
     * @param agentstate 座席状态
     */
    public Map<String, Object> agentonwork(String number, String agentid, String agenttype, String agentstate) {
        String signature = getSignature();
        //拼接请求包体
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><AgentOnWork number='" + number + "' agentid='" + agentid + "' agenttype='" + agenttype + "' agentstate='" + agentstate + "'/></Request>";
        String url = getURL("/ivr/agentonwork", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * 座席下班
     *
     * @param number    座席号码
     * @param agentid   座席ID
     * @param agenttype 座席所在队列
     */
    public Map<String, Object> agentoffwork(String number, String agentid, String agenttype) {
        String signature = getSignature();
        //拼接请求包体
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><AgentOffWork number='" + number + "' agentid='" + agentid + "' agenttype='" + agenttype + "'/></Request>";
        String url = getURL("/ivr/agentoffwork", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * 座席准备就绪
     *
     * @param agentid  座席id
     * @param action   就绪失败的回调url
     * @param state    true表示状态更新为准备就绪，false表示状态更新为准备中
     * @param priority 是否优先接听客户电话
     * @param force    是否强制设置状态
     */
    public Map<String, Object> agentready(String agentid, String action, String state, String priority, String force) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><AgentReady agentid='" + agentid + "' action='" + action + "' state='" + state + "' priority='" + priority + "' force='" + force + "'/></Request>";
        String url = getURL("/ivr/agentready", signature);
        return sendPost(url, requsetbody);
    }

}
