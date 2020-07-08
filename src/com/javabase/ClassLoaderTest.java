package com.javabase;

/**
 * <p>Java中自带的ClassLoader加载的路径</p>
 * 参考链接{@link https://blog.csdn.net/briblue/article/details/54973413}
 * @author jls
 *
 */
public class ClassLoaderTest {
    public static void getClassLoaderLoadPath() throws InterruptedException {
        // BootstrapClassLoader加载class的路径
        for (String s : System.getProperty("sun.boot.class.path").split(";")) {
            System.out.println(s);
        }
        // ExtClassLoader加载class的路径
        Thread.sleep(10);
        for (String s : System.getProperty("java.ext.dirs").split(",")) {
            System.err.println(s);
        }
        // AppClassLoader加载class的路径
        Thread.sleep(10);
        for (String s : System.getProperty("java.class.path").split(",")) {
            System.err.println(s);
        }
    }
    
    public static void main(String[] args) {
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
        System.out.println("ClassLoader is:"+cl.toString());
        System.out.println("ClassLoader parent is:"+cl.getParent().toString());
        System.out.println("ClassLoader grand parent is:"+cl.getParent().getParent().toString());
        
//        ClassLoader cl2 = int.class.getClassLoader();
//        System.out.println("ClassLoader is:"+cl2.toString());
        
        
    }
}
