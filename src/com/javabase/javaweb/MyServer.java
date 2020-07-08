package com.javabase.javaweb;

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
 * <p>Java编写HTTP Server，一个HTTP Server本质上是一个TCP服务器</p>
 * <p>这里我们使用Java TCP编程的多线程实现服务器端框架</p>
 * <a>参考链接: https://www.liaoxuefeng.com/wiki/1252599548343744/1304265903570978</a>
 * 
 * @author Administrator
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(8888);
        System.err.println("Server is running...");
        
        for(;;) {
            Socket s = ss.accept();
            System.err.println("connected from: " + s.getRemoteSocketAddress());
            Thread t = new Handler(s);
            t.start();
        }
    }
}

class Handler extends Thread {
    Socket socket;
    
    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream in = this.socket.getInputStream()) {
            try (OutputStream out = this.socket.getOutputStream()) {
                handle(in, out);
            }
        } catch (Exception e) {
            try {
                this.socket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            System.err.println("client disconnected");
        }
        
    }
    
    /**
     * <ol>处理HTTP请求过程：
     * <li>读取HTTP请求</li>
     * <li>对HTTP请求进行业务处理</li>
     * <li>返回HTTP响应</li>
     * <ol>
     * 
     * @param in
     * @param out
     * @throws IOException
     */
    private void handle(InputStream in, OutputStream out) throws IOException {
        System.err.println("Processing new http request...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
        
        // 读取HTTP Request
        boolean requestOK = false;
        String first = reader.readLine();
        if (first.startsWith("GET / HTTP/1.")) {
            requestOK = true;
        }
        System.err.println(first);
        
        for(;;) {
            String header = reader.readLine();
            if (header.isEmpty()) {// 读取到空行，HTTP header读取完毕
                break;
            }
            System.out.println(header);
        }
        
        System.err.println(requestOK ? "Response OK" : "Response Error");
        
        // 返回HTTP Response
        if (!requestOK) {
            // 返回错误响应 
            writer.write("404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // TODO 业务处理
            
            // 返回成功响应
            String data = "<html><body><h1>Hello, Server!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // 空行标识Header和Body的分隔
            writer.write(data);
            writer.flush();
        }
    }
    
}






