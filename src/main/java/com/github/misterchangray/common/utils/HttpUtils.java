package com.github.misterchangray.common.utils;

import com.github.misterchangray.dao.entity.User;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 *
 * 提供java模拟http请求
 * @author Rui.Zhang/misterchangray@hotmail.com
 * @author Created on 4/29/2018.
 */
public class HttpUtils {


    public static void main(String[] a) {
//        InputStream inputStream = http("http://192.168.0.194:8080/v1/session/login", "POST", MapBuilder.build().add("token", "123").add("Content-Type", "application/x-www-form-urlencoded"), "username=zr&password=zr");
//        String s= "";
//        try {
//            int i;
//            while(-1 != (i = inputStream.read())) {
//                s += (char) i;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Map m = JSONUtils.json2map(s);
//        System.out.println("---" + m);
//        System.out.println(MapBuilder.build().add("a","b"));
//        JSONUtils.json2obj("\\\\", User.class);
        User user = (User) JSONUtils.json2obj("{\"id\":\"12\"}", new Object());
        System.out.println(user.getId());

    }

    /**
     * JSON格式提交;返回结果转换为Map
     * @param urlPath
     * @param header
     * @param data
     * @return
     */
    public static String jsonPost(String urlPath,  Map<String, String> header, String data) {
        if(null == header) header = MapBuilder.build();
        header.put("Content-Type", "application/json");

        return readStringFormInputStream(http(urlPath, "POST", header, data));
    }


    /**
     * 表单格式提交;返回结果转换为Map
     * @param urlPath
     * @param header
     * @param data
     * @return
     */
    public static String formPost(String urlPath,  Map<String, String> header, String data) {
        if(null == header) header = MapBuilder.build();
        header.put("Content-Type", "application/x-www-form-urlencoded");

        return readStringFormInputStream(http(urlPath, "POST", header, data));
    }


    /**
     * http请求核心方法
     * @param urlPath  请求地址
     * @param method  请求方式;GET;POST;DELETE;PUT
     * @param header  增加header头
     * @param data  请求数据;根据不同请求格式组织不同的数据格式
     * @return
     */
    public static InputStream http(String urlPath, String method, Map<String, String> header, String data) {
        if(null == method) method = "GET";
        try {
            // 统一资源
            URL url = new URL(urlPath);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod(method);
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("accept", "*/*");
            //设置header
            if(null != header) {
                for(String key : header.keySet()) {
                    httpURLConnection.setRequestProperty(key, header.get(key));
                }
            }
            if(null != data) {
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(data.getBytes());
                outputStream.flush();
            }
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            httpURLConnection.connect();
            return httpURLConnection.getInputStream();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从inputStream中读取字符串
     * @param inputStream
     * @return
     */
    private static String readStringFormInputStream(InputStream inputStream) {
        if(null == inputStream) return null;

        StringBuilder stringBuilder = new StringBuilder();
        try {
            int i;
            while(-1 != (i = inputStream.read())) {
                stringBuilder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }





}

