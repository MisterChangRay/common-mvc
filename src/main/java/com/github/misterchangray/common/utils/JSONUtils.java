package com.github.misterchangray.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.BCException;


/**
 * @author Created by rui.zhang on 2018/4/23.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description JSON相关操作工具
 */
public class JSONUtils {

    /**
     * 对象转换为JSON字符串
     * @param object
     * @return
     * @throws BCException
     */
    public static String obj2json(Object object) throws BCException {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

}
