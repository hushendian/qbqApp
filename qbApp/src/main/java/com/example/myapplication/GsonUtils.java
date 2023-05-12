package com.example.myapplication;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Map;

/**
 * @name jadl_afr
 * @time 2018/9/27 10:59
 */
public enum GsonUtils {
    INSTANCE;

    private Gson gson;

    public void init() {
        if (gson == null) {
            gson = new Gson();
        }
    }

    /**
     * 将object对象转成json字符串
     *
     * @return
     */
    public String toJson(Object object) {
        String gsonString = "";
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }


    /**
     * 将gsonString转成泛型bean
     */
    public <T> T fromJson(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    public AidlResult parseResult(String gsonString) {
        return GsonUtils.INSTANCE.fromJson(gsonString, AidlResult.class);

    }


    /**
     * 转成list中有map的
     */
    public <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }


    /**
     * 转成map的
     */
    public <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }
}
