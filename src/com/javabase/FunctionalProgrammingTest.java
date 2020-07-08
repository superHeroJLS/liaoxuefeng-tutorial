package com.javabase;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Java函数式编程</p>
 * 参考链接：{@link https://www.liaoxuefeng.com/wiki/1252599548343744/1255943847278976}
 * @author jls
 */
public class FunctionalProgrammingTest {
    public static void main(String[] args) {
        // 函数式编程的方法调用，可以包括：静态方法，实例方法，构造方法
        List<String> list = Arrays.asList(new String[]{"Apple", "Banana", "Orange"});
        String[] array = list.stream().toArray(String[]::new);// 调用String[]构造方法
        System.out.println(Arrays.toString(array));
        
    }
}


