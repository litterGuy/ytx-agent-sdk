package com.cloopen.rest.sdk.agent;

import com.cloopen.rest.sdk.utils.XMLUtil;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public abstract class Component {

    protected AgentSDK agentSDK;

    /**
     * 向指定 URL 发送POST方法的请求
     *
     * @param url   发送请求的 URL
     * @param param 请求参数
     * @return 所代表远程资源的响应结果
     */
    protected Map<String, Object> sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {

            //生成auth
            String src = agentSDK.getAccountSid() + ":" + agentSDK.getTimestamp();
            String auth = new sun.misc.BASE64Encoder().encode(src.getBytes());
            URL realUrl = new URL(url);
            // 打开和URL之间的连接
            URLConnection conn = realUrl.openConnection();

            // 设置通用的请求属性
            conn.setRequestProperty("accept", "application/xml");
            conn.setRequestProperty("connection", "close");
            conn.setRequestProperty("content-type", "application/xml;charset=utf-8");
            conn.setRequestProperty("authorization", auth);
            // 发送POST请求必须设置如下两行
            conn.setDoOutput(true);
            conn.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(conn.getOutputStream());
            // 发送请求参数
            out.print(param);
            // flush输出流的缓冲
            out.flush();
            // 定义BufferedReader输入流来读取URL的响应
            in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！" + e);
            e.printStackTrace();
        }
        //使用finally块来关闭输出流、输入流
        finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return XMLUtil.xmlString2Map(result);
    }

    protected String getSignature() {
        //获取系统时间
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");//设置日期格式
        agentSDK.setTimestamp(df.format(new Date()));
        //生成sig
        String signature = "";
        String sig = agentSDK.getAccountSid() + agentSDK.getAccountToken() + agentSDK.getTimestamp();
        try {
            signature = md5Digest(sig);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return signature;
    }

    protected String getURL(String uri, String signature) {
        return this.getURL(uri, signature, null);
    }

    protected String getURL(String uri, String signature, String params) {
        String url = "https://" + agentSDK.getServerIP() + ":" + agentSDK.getServerPort() + "/" + agentSDK.getSoftVersion() + "/Accounts/" + agentSDK.getAccountSid() + uri + "?sig=" + signature;
        if (params != null && params.length() > 0) {
            url += "&" + params;
        }
        return url;
    }

    //MD5加密
    protected String md5Digest(String src) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(src.getBytes("utf-8"));
        return byte2HexStr(b);
    }

    protected String byte2HexStr(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; ++i) {
            String s = Integer.toHexString(b[i] & 0xFF);
            if (s.length() == 1)
                sb.append("0");

            sb.append(s.toUpperCase());
        }
        return sb.toString();
    }
}
