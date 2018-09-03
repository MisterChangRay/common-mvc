package com.github.misterchangray.common.utils;

import com.github.misterchangray.common.init.Init;
import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


public class HttpUtils {

    private static HttpUtils instance = new HttpUtils();
    private static HttpClient client;
    private static long startTime = System.currentTimeMillis();
    private static PoolingHttpClientConnectionManager cm ;


    public static void main(String[] a) {
        try {
            HttpUtils.getInstance().requestHttpGet("http://www.baidu.com",null,null);
        } catch (HttpException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    private static ConnectionKeepAliveStrategy keepAliveStrat = new DefaultConnectionKeepAliveStrategy() {
        @Override
        public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
            HeaderElementIterator it = new BasicHeaderElementIterator
                    (response.headerIterator(HTTP.CONN_KEEP_ALIVE));
            while (it.hasNext()) {
                HeaderElement he = it.nextElement();
                String param = he.getName();
                String value = he.getValue();
                if (value != null && param.equalsIgnoreCase
                        ("timeout")) {
                    return Long.parseLong(value) * 1000;
                }
            }
            return 3 * 60 * 1000;//如果没有约定，则默认定义时长为3分钟
        }

    };

    private HttpUtils() {
        cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(500);
        cm.setDefaultMaxPerRoute(100);//例如默认每路由最高50并发，具体依据业务来定
        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        client = httpClientBuilder
                .setConnectionManager(cm)
                .setKeepAliveStrategy(keepAliveStrat)
                .setMaxConnPerRoute(100)
                .setMaxConnTotal(100)
                .build();
    }

    public static void IdleConnectionMonitor(){

        if(System.currentTimeMillis()-startTime>30000){
            startTime = System.currentTimeMillis();
            cm.closeExpiredConnections();
            cm.closeIdleConnections(30, TimeUnit.SECONDS);
        }
    }

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setConnectTimeout(3000) //连接超时
            .setSocketTimeout(3000)//读取超时
            .setConnectionRequestTimeout(3000)
            .build();

    public static HttpUtils getInstance() {
        return instance;
    }

    private HttpPost httpPostMethod(String url) {
        return new HttpPost(url);
    }

    private  HttpRequestBase httpGetMethod(String url) {
        return new  HttpGet(url);
    }

    public String requestHttpGet(String url,Map<String, String> param, Map<String, String> header) throws HttpException, IOException{
        IdleConnectionMonitor();
        url=url;
        if(null != param){
            if(url.contains("?")) {
                if(!url.endsWith("&")) {
                    url += "&";
                }
            } else  {
                url += "?";
            }
            for (String key : param.keySet()) {
                url += key + "=" + param.get(key) + "&";
            }
        }


        HttpRequestBase method = this.httpGetMethod(url);
        //设置header
        if(null != header) {
            for(String key : header.keySet()) {
                method.setHeader(key, header.get(key));
            }
        }
        if(null == header ||  null == header.get("Connection")) {
            method.setHeader("Connection", "keep-alive");
        }
        method.setConfig(requestConfig);
        HttpResponse response = client.execute(method);

        String responseData = null;
        try{
//			if(response.getStatusLine().getStatusCode() == 200){
            responseData = EntityUtils.toString(response.getEntity());//获得返回的结果
//			}
        }catch(IOException e){
            e.printStackTrace();
            method.abort();
        }finally {
        }

        return responseData;
    }

    /**
     * 默认使用表单方式提交;如提交JSON请指定content-type
     * @param url
     * @param params
     * @param header
     * @return
     * @throws HttpException
     * @throws IOException
     */
    public String requestHttpPost(String  url,Map<String,String> params, Map<String, String> header) throws HttpException, IOException{

        url=url;
        HttpPost request = this.httpPostMethod(url);
        request.setConfig(requestConfig);
        String responseData = null;
        //设置header
        if(null != header) {
            for(String key : header.keySet()) {
                request.setHeader(key, header.get(key));
            }
        }
        if(null != params && null != header.get("Content-Type") && header.get("Content-Type").contains("json")) {
            request.setEntity(new StringEntity(JSONUtils.obj2json(params)));
        } else if(null != params) {
            List<NameValuePair> valuePairs = this.convertMap2PostParams(params);
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(valuePairs, Consts.UTF_8);
            request.setEntity(urlEncodedFormEntity);
        }

        try{
            HttpResponse response = client.execute(request);
//			if(response.getStatusLine().getStatusCode() == 200){
            responseData = EntityUtils.toString(response.getEntity());//获得返回的结果
//			}
        }catch(IOException e){
            e.printStackTrace();
            request.abort();
        }finally {
        }

        return responseData;

    }

    private List<NameValuePair> convertMap2PostParams(Map<String,String> params){
        List<String> keys = new ArrayList<String>(params.keySet());
        if(keys.isEmpty()){
            return null;
        }
        int keySize = keys.size();
        List<NameValuePair>  data = new LinkedList<NameValuePair>() ;
        for(int i=0;i<keySize;i++){
            String key = keys.get(i);
            String value = params.get(key);
            data.add(new BasicNameValuePair(key,value));
        }
        return data;
    }

}

