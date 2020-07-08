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
 * <p>Java��дHTTP Server��һ��HTTP Server��������һ��TCP������</p>
 * <p>��������ʹ��Java TCP��̵Ķ��߳�ʵ�ַ������˿��</p>
 * <a>�ο�����: https://www.liaoxuefeng.com/wiki/1252599548343744/1304265903570978</a>
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
     * <ol>����HTTP������̣�
     * <li>��ȡHTTP����</li>
     * <li>��HTTP�������ҵ����</li>
     * <li>����HTTP��Ӧ</li>
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
        
        // ��ȡHTTP Request
        boolean requestOK = false;
        String first = reader.readLine();
        if (first.startsWith("GET / HTTP/1.")) {
            requestOK = true;
        }
        System.err.println(first);
        
        for(;;) {
            String header = reader.readLine();
            if (header.isEmpty()) {// ��ȡ�����У�HTTP header��ȡ���
                break;
            }
            System.out.println(header);
        }
        
        System.err.println(requestOK ? "Response OK" : "Response Error");
        
        // ����HTTP Response
        if (!requestOK) {
            // ���ش�����Ӧ 
            writer.write("404 Not Found\r\n");
            writer.write("Content-Length: 0\r\n");
            writer.write("\r\n");
            writer.flush();
        } else {
            // TODO ҵ����
            
            // ���سɹ���Ӧ
            String data = "<html><body><h1>Hello, Server!</h1></body></html>";
            int length = data.getBytes(StandardCharsets.UTF_8).length;
            writer.write("HTTP/1.0 200 OK\r\n");
            writer.write("Connection: close\r\n");
            writer.write("Content-Type: text/html\r\n");
            writer.write("Content-Length: " + length + "\r\n");
            writer.write("\r\n"); // ���б�ʶHeader��Body�ķָ�
            writer.write(data);
            writer.flush();
        }
    }
    
}






