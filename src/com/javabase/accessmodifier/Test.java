package com.javabase.accessmodifier;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

public class Test {
	public static void main(String[] args) {
//		Student s = new Student();
//		s.setName("jls");
//		System.out.println(s.getName());
		
		
		LongStream.generate(new FibonacciSupplier()).limit(10).forEach(System.out::println);
	}
}

class FibonacciSupplier implements LongSupplier {
	
	long count = 0;
	long firstNum = 1;
	long secondNum = 1;
	
	@Override
	public long getAsLong() {
		count++;
		if (count < 3) {
			return firstNum;
		} else {
			long sum = firstNum + secondNum;
			firstNum = secondNum;
			secondNum = sum;
			return sum;
		}
	}
	
}
