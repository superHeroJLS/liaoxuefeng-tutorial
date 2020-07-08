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
 * <p>�ڿ�������Ӧ�ó����ʱ�������ֻ�����Socket������Socket��һ��������
 * һ��Ӧ�ó���ͨ��һ��Socket������һ��Զ�����ӣ���Socket�ڲ�ͨ��TCP/IPЭ������ݴ��䵽����</p>
 * 
 * <p>Socket��TCP�Ͳ���IP�Ĺ��ܶ����ɲ���ϵͳ�ṩ�ģ���ͬ�ı������ֻ���ṩ�˶Բ���ϵͳ���õļ򵥵ķ�װ��
 * ���磬Java�ṩ�ļ���Socket��ص���ͷ�װ�˲���ϵͳ�ṩ�Ľӿ�</p>
 * 
 * <p>Ϊʲô��ҪSocket��������ͨ�ţ���Ϊ����ͨ��IP��ַ����ͨ���ǲ����ģ�ͬһ̨�����ͬһʱ������ж������Ӧ�ó���
 * �����������QQ���ʼ��ͻ��˵ȡ�������ϵͳ���յ�һ�����ݰ���ʱ�����ֻ��IP��ַ����û���ж�Ӧ�÷����ĸ�Ӧ�ó���
 * ���ԣ�����ϵͳ�����Socket�ӿڣ�ÿ��Ӧ�ó�����Ҫ���Զ�Ӧ����ͬ��Socket�����ݰ����ܸ���Socket��ȷ�ط�����Ӧ��Ӧ�ó���</p>
 * 
 * <p>һ��Socket������IP��ַ�Ͷ˿ںţ���Χ��0��65535����ɣ����԰�Socket�����ΪIP��ַ�Ӷ˿ںš��˿ں������ɲ���ϵͳ���䣬
 * ����һ��0��65535֮������֣����У�С��1024�Ķ˿�������Ȩ�˿ڣ���Ҫ����ԱȨ�ޣ�����1024�Ķ˿ڿ����������û���Ӧ�ó���򿪡�</p>
 * 
 * <p>ʹ��Socket����������ʱ�������Ͼ�����������֮�������ͨ�š�����һ�����̱���䵱�������ˣ�������������ĳ��ָ���Ķ˿ڣ���һ�����̱���䵱�ͻ��ˣ�
 * �������������ӷ�������IP��ַ��ָ���˿ڣ�������ӳɹ����������˺Ϳͻ��˾ͳɹ��ؽ�����һ��TCP���ӣ�˫�������Ϳ�����ʱ���ͺͽ������ݡ�</p>
 * 
 * <ol>��ˣ���Socket���ӳɹ����ڷ������˺Ϳͻ���֮�佨����
 * <li>�Է���������˵������Socket��ָ����IP��ַ��ָ����Port��</li>
 * <li>�Կͻ�����˵������Socket�������ڼ������IP��ַ��һ���ɲ���ϵͳ��������Port��</li>
 * </ol>
 * 
 * <p>Java ��д��TCP Server</p>
 * �ο����ӣ�{@link https://www.liaoxuefeng.com/wiki/1252599548343744/1305207629676577}
 * @author jls
 *
 */
public class Server {
    public static void main(String[] args) throws IOException {
    	// ��ָ���˿�6666��������������û��ָ��IP��ַ����ʾ�ڼ��������������ӿ�(��IP��һ̨����������ж��IP)�Ͻ��м�����
        ServerSocket ss = new ServerSocket(6666); 
        System.out.println("server is running...");
        
        // ���ServerSocket�����ɹ������Ǿ�ʹ��һ������ѭ��������ͻ��˵�����
        // ��������滻���̳߳�������ͻ������ӣ��������������ʣ���������û��
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
     * ����Client����
     * 
     * <p>Ϊʲôд����������ʱ��Ҫ����flush()���������������flush()�����Ǻܿ��ܻᷢ�֣��ͻ��˺ͷ��������ղ������ݣ�
     * �Ⲣ����Java��׼���������⣬����������������ʽд�����ݵ�ʱ�򣬲�����һд������̷��͵����磬������д���ڴ滺������
     * ֱ�������������Ժ󣬲Ż�һ�����������͵����磬������Ƶ�Ŀ����Ϊ����ߴ���Ч�ʡ���������������ݺ��٣�
     * ����������ǿ�ư���Щ���ݷ��͵����磬�ͱ������flush()ǿ�ưѻ��������ݷ��ͳ�ȥ��</p>
     * @param input
     * @param output
     * @throws IOException
     */
    private void handle(InputStream input, OutputStream output) throws IOException {
    	BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
    	BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        writer.write("hello\n");
        writer.flush();
        
        // ��ȡ�������
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
