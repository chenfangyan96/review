package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIOServer {
    public static void main(String[] args) throws  Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);  //默认为 阻塞模式  需要手动设置成非阻塞模式
        serverSocketChannel.socket().bind( new InetSocketAddress(8080));
        System.out.println("服务器启动成功");
        while(true){
            SocketChannel socketChannel = serverSocketChannel.accept();// 获取新tcp连接通道
            if (socketChannel!=null){
                socketChannel.configureBlocking(false);// 默认是阻塞的,一定要设置为非阻塞
                System.out.println("收到请求"+socketChannel.getRemoteAddress());
                try {
                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                    while (socketChannel.isConnected() && socketChannel.read(allocate)!=-1){
                        if(allocate.position()>0){
                            break;
                        }
                    }
                    if (allocate.position()==0 )continue; // 如果没数据了, 则不继续后面的处理
                    allocate.flip();
                    byte[] content = new byte[allocate.limit()];
                    allocate.get(content);
                    System.out.println(new String(content));
                    System.out.println("收到数据,来自："+ socketChannel.getRemoteAddress());
                    // 响应结果 200
                    String response = "HTTP/1.1 200 OK\r\n" +
                            "Content-Length: 11\r\n\r\n" +
                            "Hello World";
                    ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                    while (buffer.hasRemaining()) {
                        socketChannel.write(buffer);// 非阻塞
                    }
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        }
    }
}
