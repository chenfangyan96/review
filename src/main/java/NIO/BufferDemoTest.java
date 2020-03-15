package NIO;

import java.nio.ByteBuffer;

public class BufferDemoTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4);//构建一个字节缓存区  容量为4
        //默认写入模式 查看三个重要指标
        System.out.println(String.format("初始化：capacity容量： %s，posotion位置：%s，limit限制： %s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 2);
        byteBuffer.put((byte) 3);
        System.out.println(String.format("存储后：capacity容量： %s，posotion位置：%s，limit限制： %s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));

        byteBuffer.flip();//转换成读取模式
        System.out.println("开始读取");
        byte b = byteBuffer.get();
        System.out.println(b);
        byte b1 = byteBuffer.get();
        System.out.println(b1);
        System.out.println(String.format("读取2字节后：capacity容量： %s，posotion位置：%s，limit限制： %s",byteBuffer.capacity(),byteBuffer.position(),byteBuffer.limit()));
        //继续写入3字节，此时读模式下，limit=3，position=2.继续写入只能覆盖写入一条数据
        //clear()方法清除整个缓冲区。compact()方法仅清除已阅读的数据。转为写入模式
        byteBuffer.compact();  // buffer : 1 , 3
        byteBuffer.put((byte) 3);
        byteBuffer.put((byte) 4);
        byteBuffer.put((byte) 5);
        System.out.println(String.format("最终的情况，capacity容量：%s, position位置：%s, limit限制：%s", byteBuffer.capacity(),
                byteBuffer.position(), byteBuffer.limit()));

        // rewind() 重置position为0
        // mark() 标记position的位置
        // reset() 重置position为上次mark()标记的位置

    }
}
