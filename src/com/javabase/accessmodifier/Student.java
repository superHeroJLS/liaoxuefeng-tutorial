package com.javabase.accessmodifier;

/**
 * subClass�޷�����superClass��private���Ժͷ�����Ҳ�޷��̳�superClass��private���Ժͷ�����
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
