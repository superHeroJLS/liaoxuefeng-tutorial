package com.javabase;

import com.javabase.accessmodifier.ProtectedClass;

public class HelloWorld {
    
    private String name;
    
	public HelloWorld(String name) {
        super();
        this.name = name;
    }

    public static void main(String[] args) {
		new HelloWorld("jianglinsheng").test();
	}
	
	public void test() {
	    System.out.println(HelloWorld.this instanceof HelloWorld);
	    System.out.println(HelloWorld.this.name);
	}
	
	
}
