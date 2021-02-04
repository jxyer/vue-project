package com.jxy.blog.common;

import com.jxy.blog.domain.Article;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TypeConversion {

    /**
     * json转实体类
     * @param object json数据
     * @param bean 实体类
     * @return
     */
    public static Object ObjToBean(Object object, Class bean) throws IllegalAccessException, InstantiationException {
        String s = object.toString();
        String replace = s.replace("{", "").replace("}", "");
        String[] split = replace.split(",");
        Map<String,String> map=new HashMap<>();
        for (String ss : split) {
            String key=ss.split("=")[0].trim();
            String value=ss.substring(ss.indexOf('=')+1);
            map.put(key,value);
        }
        Object o = bean.newInstance();
        Field[] fields = bean.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);
            if (map.containsKey(fields[i].getName())){
                String s1 = map.get(fields[i].getName());
                if (fields[i].getType().getCanonicalName().equals("java.lang.String")){
                    fields[i].set(o,s1);
                }else if (fields[i].getType().getCanonicalName().equals("java.lang.Long")){
                    Long aLong = Long.valueOf(s1);
                    fields[i].setLong(o, aLong);
                }else if (fields[i].getType().getCanonicalName().equals("java.lang.Boolean")){
                    Boolean aBoolean = Boolean.valueOf(s1);
                    fields[i].setBoolean(o, aBoolean);
                }
            }
        }
        return o;
    }


}
