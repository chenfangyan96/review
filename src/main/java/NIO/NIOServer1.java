package NIO;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/*
*直接基于非阻塞的写法,一个线程处理轮询所有请求
*  当前弊端是需要一个线程要轮询处理多个请求
* */
public class NIOServer1 {
    public static List<SocketChannel> list1  = new ArrayList<SocketChannel>();

    public static void main(String[] args) throws  Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        System.out.println("服务器启动");
        while (true){
            SocketChannel accept = serverSocketChannel.accept();
            if (accept != null){
                System.out.println("收到新连接 : " + accept.getRemoteAddress());
                accept.configureBlocking(false); // 默认是阻塞的,一定要设置为非阻塞
                list1.add(accept);
            }else {
                Iterator<SocketChannel> iterator = list1.iterator();
                while (iterator.hasNext()){
                    SocketChannel channel = iterator.next();
                    try {
                        ByteBuffer allocate = ByteBuffer.allocate(1024);
                        if (channel.read(allocate) == 0) {
                            continue;// 等于0,代表这个通道没有数据需要处理,那就待会再处理
                        }
                        while (channel.isConnected() && channel.read(allocate) != -1) {
                            // 长连接情况下,需要手动判断数据有没有读取结束 (此处做一个简单的判断: 超过0字节就认为请求结束了)
                            if (allocate.position() > 0) break;
                        }
                        if (allocate.position() == 0) continue; // 如果没数据了, 则不继续后面的处理
                        allocate.flip();
                        byte[] content = new byte[allocate.limit()];
                        allocate.get(content);
                        System.out.println(new String(content));
                        System.out.println("收到数据,来自：" + channel.getRemoteAddress());
                        // 响应结果 200
                        String response = "HTTP/1.1 200 OK\r\n" +
                                "Content-Length: 11\r\n\r\n" +
                                "Hello World";
                        ByteBuffer buffer = ByteBuffer.wrap(response.getBytes());
                        while (buffer.hasRemaining()) {
                            channel.write(buffer);
                        }
                        iterator.remove();
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            }
        }

    }
}
