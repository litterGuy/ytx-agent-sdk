package com.cloopen.rest.sdk.agent;

import java.util.Map;

public class Queue extends Component {

    /**
     * 创建队列
     *
     * @param queuetype      队列类型，定义为正整数(支持最大9位)，具体属性类型的含义由应用侧维护，例如：1英语技能组，2足球技能组，3法语技能组。队列类型与座席类型相一致。默认为0队列。
     * @param typedes        队列类型描述
     * @param worktime       队列工作时间，格式：hh:mi-hh:mi。非工作时间不接听电话，为用户播放提示音后挂机，若需设置多个时间段，用分号隔开。
     * @param offworkprompt  非工作时间队列提示音
     * @param offworkdate    非工作日期，默认值为空，格式为 yyyy-mm-dd:yyyy-mm-dd，开始及结束日期以英文冒号隔开。相同时表示为一天。
     * @param offworkweekday 每周的非工作时间，默认值为空。若是多天以#分隔，输入值为每日英文缩写，如：Mon、Tue、Wed、Thu、Fri、Sat、Sun
     */
    public Map<String, Object> createqueue(String queuetype, String typedes, String worktime, String offworkprompt, String offworkdate, String offworkweekday) {
        //生成sig
        String signature = getSignature();
        //拼接请求包体
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><CreateQueue queuetype='" + queuetype + "' typedes='" + typedes + "' worktime='" + worktime + "' offworkprompt='" + offworkprompt + "' offworkdate='" + offworkdate + "' offworkweekday='" + offworkweekday + "'/></Request>";
        String url = getURL("/ivr/createqueue", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * 删除队列
     *
     * @param queuetype
     * @return
     */
    public Map<String, Object> delqueue(String queuetype) {
        String signature = getSignature();
        //拼接请求包体
        String requsetbody = "";
        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><DelQueue queuetype='" + queuetype + "'/></Request>";
        String url = getURL("/ivr/delqueue", signature);
        return sendPost(url, requsetbody);
    }

    /**
     * 修改队列，参数数据同创建队列
     *
     * @param queuetype
     * @param typedes
     * @param worktime
     * @param offworkprompt
     * @param offworkdate
     * @param offworkweekday
     * @return
     */
    public Map<String, Object> modifyqueue(String queuetype, String typedes, String worktime, String offworkprompt, String offworkdate, String offworkweekday) {
        String signature = getSignature();
        String requsetbody = "";

        requsetbody = "<?xml version='1.0' encoding='utf-8'?><Request><Appid>" + agentSDK.getAppId() + "</Appid><ModifyQueue queuetype='" + queuetype + "' typedes='" + typedes + "' worktime='" + worktime + "' offworkprompt='" + offworkprompt + "' offworkdate='" + offworkdate + "' offworkweekday='" + offworkweekday + "'/></Request>";
        String url = getURL("/ivr/modifyqueue", signature);
        return sendPost(url, requsetbody);
    }

}
