package com.step.lclib.type;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class TypeTest {

    //是ParameterizedType
    private HashMap<String, Object> map;
    private HashSet<String> set;
    private List<String> list;
    private Class<?> clz;

    //不是ParameterizedType
    private Integer i;
    private String str;


    @Test
    public void test() {
        printParameterizedType();
    }

    private static void printParameterizedType() {
        Field[] fields = TypeTest.class.getDeclaredFields();
        for (Field f : fields) {
            //打印是否是ParameterizedType类型
            System.out.println("FieldName:  " + f.getName() + " instanceof ParameterizedType is : " +
                    (f.getGenericType() instanceof ParameterizedType));
        }
        //取map这个类型中的实际参数类型的数组
        getParameterizedTypeWithName("map");
        getParameterizedTypeWithName("str");
    }

    private static void getParameterizedTypeWithName(String name) {
        Field f;
        try {
            //利用反射得到TestParameterizedTypeBean类中的所有变量
            f = TypeTest.class.getDeclaredField(name);
            f.setAccessible(true);
            Type type = f.getGenericType();
            if (type instanceof ParameterizedType) {
                for (Type param : ((ParameterizedType) type).getActualTypeArguments()) {
                    //打印实际参数类型
                    System.out.println("---type actualType---" + param.toString());
                }
                //打印所在的父类的类型
                System.out.println("---type ownerType0---" + ((ParameterizedType) type).getOwnerType());
                //打印其本身的类型
                System.out.println("---type rawType---" + ((ParameterizedType) type).getRawType());
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

}
