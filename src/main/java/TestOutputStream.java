/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TestOutputStream
 * Author:   Administrator
 * Date:     2018/11/7 17:05
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
import  java.io.*;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/11/7
 * @since 1.0.0
 */
public class TestOutputStream {
    public  static void  Test() {

        try {
            String hello = "hello world Code";
            byte[] bytes = hello.getBytes();
            FileOutputStream fi = new FileOutputStream("D:/test.txt");
            fi.write(bytes);
            fi.close();

        } catch ( IOException e) {
            e.printStackTrace();
        }
    }

}

