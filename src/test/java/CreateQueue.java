import com.cloopen.rest.sdk.agent.AgentBuilder;
import com.cloopen.rest.sdk.agent.AgentSDK;

import java.util.Iterator;
import java.util.Map;

public class CreateQueue {
    private static String AccountSid = "11";//主帐号
    private static String AccountToken = "11";//主帐号TOKEN
    private static String AppId = "11";//应用ID
    private static String ServerIP = "app.cloopen.com";//请求的URL

    public static void main(String[] args) {
        AgentSDK agentSDK = AgentBuilder.newBuilder(AccountSid, AccountToken, AppId, ServerIP).build();
//        Map<String, Object> result = agentSDK.queue().createqueue("0", "客服队列", "00:00-23:59", null, null, null);

//        Map<String, Object> result = agentSDK.queue().delqueue("0");
//        Map<String, Object> result = agentSDK.account().createSubAccount("1234");
//        Map<String, Object> result = agentSDK.account().querySubAccount("1234");
//        Map<String, Object> result = agentSDK.account().closeSubAccount("f29a1ca53b0e11e8a3e47cd30ac478d2");
//        Map<String, Object> result = agentSDK.workStatus().agentoffwork("8023312700000011", "6666", "0");
//        Map<String, Object> result = agentSDK.workStatus().agentready("8888", null, "false", null, "true");
        Map<String, Object> result = agentSDK.workStatus().agentonwork("13661097023", "6666", "0", "0");
        System.out.println(transMapToString(result));
    }

    private static String transMapToString(Map map) {
        java.util.Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (java.util.Map.Entry) iterator.next();
            sb.append(entry.getKey().toString()).append("'").append(null == entry.getValue() ? "" :
                    entry.getValue().toString()).append(iterator.hasNext() ? "^" : "");
        }
        return sb.toString();
    }
}
