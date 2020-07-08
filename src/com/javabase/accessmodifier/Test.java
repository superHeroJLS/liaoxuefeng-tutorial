package com.javabase.accessmodifier;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * subClass无法访问superClass的private属性和方法，也无法继承superClass的private属性和方法。
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


