package com.javabase;

import java.util.Arrays;
import java.util.List;

public class FunctionalProgrammingTest {
    public static void main(String[] args) {
        
        List<String> list = Arrays.asList(new String[]{"Apple", "Banana", "Orange"});
        String[] array = list.stream().toArray(String[]::new);// 调用String[]构造方法
        System.out.println(Arrays.toString(array));
    }
}
