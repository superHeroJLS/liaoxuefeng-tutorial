package com.javabase.accessmodifier;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * subClass�޷�����superClass��private���Ժͷ�����Ҳ�޷��̳�superClass��private���Ժͷ�����
 * @author jls
 *
 */
public class Test {
	public static void main(String[] args) {
		Student s = new Student();
		s.setName("jls");
		System.out.println(s.getName());
		
	}
}


