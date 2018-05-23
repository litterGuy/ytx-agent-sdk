package com.cloopen.rest.sdk.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class XMLUtil {
    /**
     * Xml string转换成Map
     *
     * @param xmlStr
     * @return
     */
    public static Map<String, Object> xmlString2Map(String xmlStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        Document doc;
        try {
            doc = DocumentHelper.parseText(xmlStr);
            Element el = doc.getRootElement();
            map = recGetXmlElementValue(el, map);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 循环解析xml
     *
     * @param ele
     * @param map
     * @return
     */
    @SuppressWarnings({"unchecked"})
    private static Map<String, Object> recGetXmlElementValue(Element ele, Map<String, Object> map) {
        List<Element> eleList = ele.elements();
        if (eleList.size() == 0) {
            map.put(ele.getName(), ele.getTextTrim());
            return map;
        } else {
            for (Iterator<Element> iter = eleList.iterator(); iter.hasNext(); ) {
                Element innerEle = iter.next();
                recGetXmlElementValue(innerEle, map);
            }
            return map;
        }
    }
}
