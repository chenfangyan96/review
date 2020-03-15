package BIO;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class BioClient {
    private  static Charset charset= Charset.forName("UTF-8");
    public static void main(String[] args) throws Exception {

        Socket socket = new Socket("localhost",8080);
        System.out.printf("请输入：");
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        if (s!=null){
            byte[] bytes = s.getBytes(charset);
            outputStream.write(bytes);
        }
        outputStream.flush();
        InputStream inputStream = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "utf-8"));
        String msg;
        while   ((msg=bufferedReader.readLine())!=null){
            System.out.println(msg);
        }
        inputStream.close();
        outputStream.close();
        socket.close();
    }
}
