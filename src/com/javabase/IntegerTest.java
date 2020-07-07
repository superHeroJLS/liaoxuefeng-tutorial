package com.javabase;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

public class IntegerTest {
    public static void main(String[] args) throws IntrospectionException {
//        Integer i1 = new Integer(127);
//        Integer i2 = new Integer(127);
//        Integer i3 = Integer.valueOf(127);
//        Integer i4 = Integer.valueOf(127);
//        System.out.println(i2 == i1);
//        System.out.println(i3 == i4);
        
        
        // 将有符号Byte值转成无符号Byte值
//        byte x = -1;
//        byte y = 127;
//        System.out.println(Byte.toUnsignedInt(x)); // 255
//        System.out.println(Byte.toUnsignedInt(y)); // 127
        
        // 内省-introspector
        BeanInfo info = Introspector.getBeanInfo(Person.class);
        for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("  " + pd.getReadMethod());
            System.out.println("  " + pd.getWriteMethod());
        }
    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
