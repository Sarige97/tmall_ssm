package com.sarige.tmall.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UrlBuilder {

    Map<String, String> urlParams = new ConcurrentHashMap<>();
    private final String baseUrl;

    public UrlBuilder(String url) {
        baseUrl = url;
    }

    public UrlBuilder addParam(String key, String value) {
        urlParams.put(key, value);
        return this;
    }

    public UrlBuilder addParam(String key, int value) {
        urlParams.put(key, String.valueOf(value));
        return this;
    }

    public UrlBuilder addParam(String key, float value) {
        urlParams.put(key, String.valueOf(value));
        return this;
    }

    public String toString() {
        StringBuilder url = new StringBuilder();
        url.append(baseUrl);
        url.append("?");
        urlParams.forEach((key, value) -> {
            url.append(key).append("=").append(value).append("&");
        });
        //若url最后一个字符为‘&’，则删除这个‘&’
        if (url.lastIndexOf("&") == url.length() - 1) {
            url.deleteCharAt(url.length() - 1);
        }
        return url.toString();
    }

}
