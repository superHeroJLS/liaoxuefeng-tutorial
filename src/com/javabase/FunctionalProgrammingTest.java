package com.javabase;

import java.util.Arrays;
import java.util.List;

/**
 * <p>Java����ʽ���</p>
 * �ο����ӣ�{@link https://www.liaoxuefeng.com/wiki/1252599548343744/1255943847278976}
 * @author jls
 */
public class FunctionalProgrammingTest {
    public static void main(String[] args) {
        // ����ʽ��̵ķ������ã����԰�������̬������ʵ�����������췽��
        List<String> list = Arrays.asList(new String[]{"Apple", "Banana", "Orange"});
        String[] array = list.stream().toArray(String[]::new);// ����String[]���췽��
        System.out.println(Arrays.toString(array));
        
    }
}


