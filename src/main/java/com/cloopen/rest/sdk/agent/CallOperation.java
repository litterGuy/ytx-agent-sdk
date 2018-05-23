package com.cloopen.rest.sdk.agent;

import java.util.Map;

//TODO 其他待完善
public class CallOperation extends Component {

    /**
     * IVR外呼
     *
     * @param number   待呼叫号码，为Dial节点的属性
     * @param userdata 用户数据，在<startservice>通知中返回，只允许填写数字字符，为Dial节点的属性
     * @param record   是否录音，可填项为true和false，默认值为false不录音，为Dial节点的属性
     */
    public Map<String, Object> dial(String number, String userdata, String record) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><Dial number='" + number + "' userdata='" + userdata + "' record='" + record + "'/></Request>";
        String url = getURL("/ivr/dial", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * 座席人员主动发起外呼。座席状态只有在准备中、准备就绪、状态下才能进行外呼。
     *
     * @param number  外呼号码，手机号或座机号或通讯账号。
     * @param agentId 座席Id，4位正整数
     * @param disnumber 用户方的显号号码，根据平台侧显号规则控制。
     * @return
     */
    public Map<String, Object> agentmakecall(String number, String agentId, String disnumber) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><AgentMakeCall agentid='" + agentId + "' number='" + number + "' disnumber='"+disnumber+"'/></Request>";
        String url = getURL("/ivr/agentmakecall", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * 座席结束通话
     * 征得用户同意后座席可主动结速通话，但用户通话未挂断，应用侧可继续为用户执行后续业务逻辑。若action内容为空则使用进入排队系统命令中agenthangupurl参数作为回调地址，若都为空则挂断用户电话。
     *
     * @param callid  平台侧为每个用户呼叫分配唯一Id
     * @param agentid 座席Id，4位正整数
     * @param action  结束成功的回调url，默认值为空
     * @return
     */
    public Map<String, Object> serviceEnd(String callid, String agentid, String action) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><AgentServiceEnd callid='" + callid + "' agentid='" + agentid + "' action='" + action + "'/></Request>";
        String url = getURL("/ivr/call", signature, "callid=" + callid);
        return sendPost(url, requsetbody);
    }

    /**
     * 座席呼叫转接
     * 其他参数待完善
     *
     * @param callid  平台侧为每个用户呼叫分配唯一Id
     * @param agentid 座席Id，4位正整数。agentid和number二者必须有一个不为空，当agentid不为空时number无效。
     * @return
     */
    public Map<String, Object> transfer(String callid, String agentid) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><Transfer callid='" + callid + "' agentid='" + agentid + "'/></Request>";
        String url = getURL("/ivr/call", signature, "callid=" + callid);
        return sendPost(url, requsetbody);
    }

    /**
     * 座席与用户通话过程中可以发送此请求咨询其他座席或电话。
     * 注：此接口不可以与用户静音接口一起使用，坐席咨询默认会将用户端静音，如果调用用户静音会导致无法转接。
     *
     * @param callid  平台侧为每个用户呼叫分配唯一Id
     * @param agentid 座席Id，4位正整数，为空则呼叫number参数号码
     * @param number  电话号码或通讯账号
     * @return
     */
    public Map<String, Object> agentConsult(String callid, String agentid, String number) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><AgentConsult callid='" + callid + "' agentid='" + agentid + "' number='" + number + "'/></Request>";
        String url = getURL("/ivr/call", signature, "callid=" + callid);
        return sendPost(url, requsetbody);
    }

    /**
     * 三方通话
     * 座席咨询后使用，可进行三方通话。　若想退出与用户的通话调用座席结束通话即可。
     *
     * @param callid 平台侧为每个用户呼叫分配唯一Id
     * @param action 三方通话操作结果通知的回调url，默认值为空
     * @return
     */
    public Map<String, Object> tripartiteTalk(String callid, String action) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><TripartiteTalk callid='" + callid + "' action='" + action + "'/></Request>";
        String url = getURL("/ivr/call", signature, "callid=" + callid);
        return sendPost(url, requsetbody);
    }

}
