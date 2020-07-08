package com.javabase.networkprogramming.tcp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * <p>在开发网络应用程序的时候，我们又会遇到Socket这个概念。Socket是一个抽象概念，
 * 一个应用程序通过一个Socket来建立一个远程连接，而Socket内部通过TCP/IP协议把数据传输到网络</p>
 * 
 * <p>Socket、TCP和部分IP的功能都是由操作系统提供的，不同的编程语言只是提供了对操作系统调用的简单的封装。
 * 例如，Java提供的几个Socket相关的类就封装了操作系统提供的接口</p>
 * 
 * <p>为什么需要Socket进行网络通信？因为仅仅通过IP地址进行通信是不够的，同一台计算机同一时间会运行多个网络应用程序，
 * 例如浏览器、QQ、邮件客户端等。当操作系统接收到一个数据包的时候，如果只有IP地址，它没法判断应该发给哪个应用程序，
 * 所以，操作系统抽象出Socket接口，每个应用程序需要各自对应到不同的Socket，数据包才能根据Socket正确地发到对应的应用程序。</p>
 * 
 * <p>一个Socket就是由IP地址和端口号（范围是0～65535）组成，可以把Socket简单理解为IP地址加端口号。端口号总是由操作系统分配，
 * 它是一个0～65535之间的数字，其中，小于1024的端口属于特权端口，需要管理员权限，大于1024的端口可以由任意用户的应用程序打开。</p>
 * 
 * <p>使用Socket进行网络编程时，本质上就是两个进程之间的网络通信。其中一个进程必须充当服务器端，它会主动监听某个指定的端口，另一个进程必须充当客户端，
 * 它必须主动连接服务器的IP地址和指定端口，如果连接成功，服务器端和客户端就成功地建立了一个TCP连接，双方后续就可以随时发送和接收数据。</p>
 * 
 * <ol>因此，当Socket连接成功地在服务器端和客户端之间建立后：
 * <li>对服务器端来说，它的Socket是指定的IP地址和指定的Port；</li>
 * <li>对客户端来说，它的Socket是它所在计算机的IP地址和一个由操作系统分配的随机Port。</li>
 * </ol>
 * 
 * <p>Java 编写的TCP Server</p>
 * 参考链接：{@link https://www.liaoxuefeng.com/wiki/1252599548343744/1305207629676577}
 * @author jls
 *
 */
public class Server {
    public static void main(String[] args) throws IOException {
    	// 在指定端口6666监听。这里我们没有指定IP地址，表示在计算机的所有网络接口(即IP，一台计算机可能有多个IP)上进行监听。
        ServerSocket ss = new ServerSocket(6666); 
        System.out.println("server is running...");
        
        // 如果ServerSocket监听成功，我们就使用一个无限循环来处理客户端的连接
        // 这里可以替换成线程池来处理客户端连接，大大提高运行速率，但是这里没用
        for (;;) {
            Socket sock = ss.accept();
            System.out.println("connected from " + sock.getRemoteSocketAddress());
            Thread t = new Handler(sock);
            t.start();
        }
    }
}

class Handler extends Thread {
    Socket sock;

    public Handler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try (InputStream input = this.sock.getInputStream()) {
            try (OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
            try {
                this.sock.close();
            } catch (IOException ioe) {
            }
            System.out.println("client disconnected.");
        }
    }

    /**
     * 处理Client请求
     * 
     * <p>为什么写入网络数据时，要调用flush()方法：如果不调用flush()，我们很可能会发现，客户端和服务器都收不到数据，
     * 这并不是Java标准库的设计问题，而是我们以流的形式写入数据的时候，并不是一写入就立刻发送到网络，而是先写入内存缓冲区，
     * 直到缓冲区满了以后，才会一次性真正发送到网络，这样设计的目的是为了提高传输效率。如果缓冲区的数据很少，
     * 而我们又想强制把这些数据发送到网络，就必须调用flush()强制把缓冲区数据发送出去。</p>
     * @param input
     * @param output
     * @throws IOException
     */
    private void handle(InputStream input, OutputStream output) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
    	BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        writer.write("hello\n");
        writer.flush();
        
        // 读取请求参数
        for (;;) {
            String s = reader.readLine();
            if (s.equals("bye")) {
                writer.write("bye\n");
                writer.flush();
                break;
            }
            writer.write("ok: " + s + "\n");
            writer.flush();
        }
    }
}
