package com.github.misterchangray.service.common;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 *  DFA算法 敏感词过滤
 * @author Created by rui.zhang on 2018/6/25.
 * @author rui.zhang
 * @version ver1.0
 * @email misterchangray@hotmail.com
 * @description
 */
@Component
public class SensitiveWordService {
    private Map sensitiveWordMap = null;    //DNF关键词模型
    private static final String ENCODING = "utf-8"; //字符编码

    public static void main(String args[]) {
        SensitiveWordService sensitiveWordService = new SensitiveWordService();
        sensitiveWordService.initKeyWord();

        long start = System.currentTimeMillis();
        String string = "太多的伤感情怀也许只局限于饲养基地 荧幕中的情节，主人公尝试着去用某种方式渐渐的很潇洒地释自杀指南怀那些自己经历的伤感。"
                + "然后法轮功 我们的扮演的角色就是跟随着主人公的喜红客联盟 怒哀乐而过于牵强的把自己的情感也附加于银幕情节中，然后感动就流泪，"
                + "难过就躺在某一个人的怀里尽情的阐述心扉或者手机卡复制器一个人一杯红酒一部电影在夜三级片 深人静的晚上，关上电话静静的发呆着。";
        System.out.println(sensitiveWordService.getSensitiveWord(string, 0));
        System.out.println("关键词个数：" + sensitiveWordService.sensitiveWordMap.size());
        System.out.println("time--" + ( System.currentTimeMillis() - start));
    }

    /**
     * 初始化DNF关键词模型;全局仅用初始化一次
     * @return
     * @throws Exception
     * @since 1.8
     * @author whb
     */
    public void initKeyWord() {
        if(null != sensitiveWordMap) return;

        try {
            initSensitiveWordToHashMap(readSensitiveWordFormFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    /**
     * 读取敏感词库，并把内容放到set里
     * @return
     * @throws Exception
     * @since 1.8
     * @author whb
     */
    private Set<String> readSensitiveWordFormFile() throws Exception {
        Set<String> set = null;
        File file = new File("D:\\workspace\\common-mvc\\src\\main\\resources\\sensitiveWord.txt");
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), ENCODING))) {
            if (file.isFile() && file.exists()) {
                set = new HashSet<String>();
                String txt = null;
                while ((txt = bufferedReader.readLine()) != null) {
                    set.add(txt);
                }
            } else {
                throw new Exception("敏感词库文件不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return set;

    }

    /**
     * 获取文本中的敏感词汇列表
     * @param text 待获取词汇内容
     * @param startIndex 内容起始获取下标
     * @return
     */
    public Set<String> getSensitiveWord(String text, Integer startIndex) {
        if(null == startIndex) startIndex = 0;
        Set<String> sensitiveWords = new HashSet<String>();
        Map sensitiveMap = sensitiveWordMap;

        if(null != text &&  startIndex < text.length()) {
            String sensitiveWord = "";
            char tmp;
            for(int i=startIndex, j=text.length(); i<j; i++) {
                tmp = text.charAt(i);
                if(null != sensitiveMap.get(tmp)) {
                    sensitiveMap = (Map) sensitiveMap.get(tmp);
                    sensitiveWord += tmp;
                    if(isWordEnd(sensitiveMap)) {
                        sensitiveWords.add(sensitiveWord);
                    }
                } else {
                    sensitiveWord = "";
                    sensitiveMap = sensitiveWordMap;
                }
            }
        }
        return sensitiveWords;
    }


    private boolean isWordEnd(Map map) {
        if(null != map.get("isWordEnd") && (true == (Boolean) map.get("isWordEnd"))) {
            return true;
        }
        return false;
    }

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型<br>
     * @param keyWordList
     * @since 1.8
     * @author whb
     */
    private void initSensitiveWordToHashMap(Set<String> keyWordList) {
        sensitiveWordMap = new HashMap(keyWordList.size()); //初始化敏感词容器，避免扩容操作
        String key = null;
        Map nowMap = null;
        Map<String, Boolean> newWorMap = null;
        Iterator<String> iterator = keyWordList.iterator();
        while (iterator.hasNext()) {
            key = iterator.next();
            nowMap = sensitiveWordMap;
            for (int i = 0; i < key.length(); i++) {
                char charKey = key.charAt(i); //转换成char型
                Object wordMap = nowMap.get(charKey);
                if (wordMap != null) {
                    nowMap = (Map) wordMap; //一个一个放进Map中
                } else { //不存在，则构建一个Map,同时将isEnd设置为0，因为它不是最后一个
                    newWorMap = new HashMap<String, Boolean>();
                    newWorMap.put("isEnd", false);//不是最后一个
                    nowMap.put(charKey, newWorMap);//没有这个key，就把(isEnd，0) 放在Map中
                    nowMap = newWorMap;
                }
                if (i == key.length() - 1) { //最后一个
                    nowMap.put("isEnd", true);
                    nowMap.put("isWordEnd", true);
                }
            }
        }
    }



}
