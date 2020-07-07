package com.javabase;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class ThrowableTest {
    
    static int depth = 0;
    
    /**
     * 手动制造一个StackOverFlowError
     */
    public static void getStackOverFlowError(){
        depth ++ ;
        getStackOverFlowError();
    }
    
    /**
     * 手动制造一个OutofMemoryError，运行时jvm参数：-Xms10m -Xmx10m -XX:PermSize=1m -XX:MaxPermSize=2m
     */
    public static void getOutOfMemoryError() {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try {
            url = new File("D:\\tmp").toURI().toURL();
            URL[] urls = {url};
            while (true){
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.javabase.MyThrowable");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }   
    
    public static void main(String[] args) throws InterruptedException {
        
       
    }
    
    
}
