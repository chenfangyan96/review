package NIO;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class NIOClient {
    public static void main(String[] args) throws  Exception {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect( new InetSocketAddress("127.0.0.1",8080));
        while (!socketChannel.finishConnect()){
            Thread.yield();
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入:");
        //发送内容
        String msg = scanner.nextLine();
        ByteBuffer wrap = ByteBuffer.wrap(msg.getBytes());
        while (wrap.hasRemaining()){//判断是否读取完成
                socketChannel.write(wrap);
        }
        System.out.println("收到服务端响应:");
        ByteBuffer requestBuffer = ByteBuffer.allocate(1024);
        while (socketChannel.isOpen() &&socketChannel.read(requestBuffer)!= -1){
            //长链接情况下   需要手动判断数据是否读取结束   （此处做简单判断  超过0字节就认为请求结束）
            if (requestBuffer.position()>0){
                break;
            }
        }
        //读取数据
        requestBuffer.flip();
        byte[] content = new byte[requestBuffer.limit()];
        requestBuffer.get(content);
        System.out.println( new String(content));
        scanner.close();
        socketChannel.close();
    }
}
