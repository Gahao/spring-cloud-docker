package com.musketeers.common.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
将传入request转为json对象
 */
public  class JsonTool {
    public static JSONObject parseRequestToJsonObject(HttpServletRequest request) throws IOException {
        BufferedReader streamReader = new BufferedReader( new InputStreamReader(request.getInputStream(),"UTF-8"));
        StringBuilder responseStrBuilder = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null)
            responseStrBuilder.append(inputStr);

        JSONObject jsonObject = JSONObject.parseObject(responseStrBuilder.toString());
        return jsonObject;
    }
}
