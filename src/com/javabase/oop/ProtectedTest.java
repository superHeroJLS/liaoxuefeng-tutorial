package com.javabase.oop;

public class ProtectedTest {
	
	public void method() {
		String name = new ProtectedClass().name;// 同一个package下可以访问protected属性，方法
	}
}
