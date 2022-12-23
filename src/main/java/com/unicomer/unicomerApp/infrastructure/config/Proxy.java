package com.unicomer.unicomerApp.infrastructure.config;

import feign.Response;
import org.apache.commons.text.StringEscapeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase encargada de escapar correctamente el json entregado por el endpoint
 */

public class Proxy {
    private static final String PARAM_VALUE = ",\"%s\":\"%s\"";
    private static final String OBJECT = ",{%s}";
    private static final String LIST_OF_OBJECTS = "[%s]";

    private static String getObjStr(String s){
        StringBuilder builder = new StringBuilder();
        String[] spl = s.split("\",\"");
        Arrays.stream(spl).forEach(st->{
            String[] spli = st.split("\":\"");
            if(spli.length>1) {
                String paramName = spli[0].replaceAll("\"", "");
                String paramValue = spli[1].replaceAll("\"", "").replaceAll("\t", " ");
                builder.append(String.format(PARAM_VALUE, paramName, paramValue));
            }
        });
        return String.format(OBJECT,builder.substring(1));
    }

    private static String getList(List<String> strList){
        StringBuilder builder = new StringBuilder();
        strList.stream().forEach(s -> {
            builder.append(getObjStr(s));
        });
        return String.format(LIST_OF_OBJECTS,builder.substring(1));
    }

    public static String getJsonStr(Response response){
        String bodyStr = response.body().toString();
        bodyStr = StringEscapeUtils.unescapeJava(bodyStr);
        bodyStr = bodyStr
                .replaceAll("\\[", "")
                .replaceAll("\\]","");

        String[] splitter = bodyStr.split("\\{");
        List<String> strList= Arrays.stream(splitter)
                .map(s -> {
                    s = s.replace("},", "");
                    return s;})
                .collect(Collectors.toList());
        strList.remove(0);
        return getList(strList);
    }
}
