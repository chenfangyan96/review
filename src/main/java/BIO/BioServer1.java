package BIO;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/*
*
* 支持多线程   多个客户端同时访问
* */

public class BioServer1 {
    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static void main(String[] args)  throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()){
            Socket accept = serverSocket.accept();
            System.out.println("收到新链接"+accept.toString());
            threadPool.execute(() -> {
            try {
                InputStream request = accept.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request,"utf-8"));
                String msg;
                while ((msg=bufferedReader.readLine())!=null){
                    if (msg.length()==0){
                        break;
                    }
                    System.out.println(msg);
                }
                System.out.println("收到消息"+request.toString());
                // 响应结果 200
                OutputStream outputStream = accept.getOutputStream();
                outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                outputStream.write("Content-Length: 11\r\n\r\n".getBytes());
                outputStream.write("Hello World".getBytes());
                outputStream.flush();
            }catch ( IOException e){
                e.printStackTrace();
            }finally {
                try{
                    accept.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }});
        }

        serverSocket.close();



    }
}
