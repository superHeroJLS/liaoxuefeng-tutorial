package com.javabase.stream;

import java.util.function.LongSupplier;
import java.util.stream.LongStream;

/**
 * <p>java.util.Stream的使用</p>
 * 参考链接：{@link https://www.liaoxuefeng.com/wiki/1252599548343744/1322402873081889}
 * @author jls
 *
 */
public class JavaUtilStreamTest {
	public static void main(String[] args) {
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