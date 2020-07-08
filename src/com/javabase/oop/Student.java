package com.javabase.oop;

/**
 * subClass无法访问superClass的private属性和方法，也无法继承superClass的private属性和方法。
 * @author jls
 *
 */
public class Student extends Person{
	private int age;
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	

}
