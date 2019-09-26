package com.small.routing.common;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StringUtil {

    private static final Gson gson = new Gson();

    public static String obj2Str(Object object) {
        return obj2Str(object, "");
    }

    public static String obj2Str(Object target, String defaultValue) {
        String value = defaultValue;
        if (target != null) {
            value = String.valueOf(target);
        }
        return value;
    }

    public static String obj2Json(Object object) {
        return gson.toJson(object);
    }

    public static boolean isNotEmptyOrNull(Object object) {
        if (object instanceof List) {
            return ((List) object).size() != 0;
        }
        if (object instanceof Map) {
            return ((Map) object).size() != 0;
        }
        if (object instanceof Set) {
            return ((Set) object).size() != 0;
        }
        return object != null && !"".equals(object);
    }

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("1");
        Map map = new HashMap();
        String str = "";
//        System.out.println(isNotEmptyOrNull(list));
//        System.out.println(isNotEmptyOrNull(map));
//        System.out.println(isNotEmptyOrNull(str));
        System.out.println(obj2Json(list));
    }
}
