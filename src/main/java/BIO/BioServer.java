package BIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


public class BioServer {

    public static void main(String[] args)  throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动成功");
        while (!serverSocket.isClosed()){
            Socket accept = serverSocket.accept();//阻塞
            System.out.println("收到新链接"+accept.toString());
            try {
                InputStream request = accept.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(request,"utf-8"));
                String msg;
                while ((msg=bufferedReader.readLine())!=null){//阻塞
                    if (msg.length()==0){
                        break;
                    }
                    System.out.println(msg);
                }
                System.out.printf("收到消息"+request.toString());
            }catch ( IOException e){
                e.printStackTrace();
            }finally {
                accept.close();
            }
        }

        serverSocket.close();



    }
}
