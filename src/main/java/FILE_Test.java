/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: FILE_Test
 * Author:   Administrator
 * Date:     2018/11/6 14:04
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */



import  java.io.*;
import java.util.*;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author Administrator
 * @create 2018/11/6
 * @since 1.0.0
 */
public class FILE_Test {


    public   static void  repeatName(BufferedReader b) {
        try {
            File df = new File("D:/rang.txt");
            FileInputStream n1 = new FileInputStream(df);
            Reader r = new InputStreamReader(n1, "GBK");
            BufferedReader c = new BufferedReader(r);
            String line = null;

            //          去重
            //hashmap  允许为null
            //hashtable不能为 空
            // Hashtable<String, String> map = new Hashtable<String, String>();
            HashMap<String, String> map = new HashMap<String, String>();
            String name = "";
            while ((name =c.readLine()) != null) {
                map.put(name, "");
            }

            if (map != null) {
                Set<String> set = map.keySet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    System.out.println(it.next());
                }
                c.close();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public  static  void test2(){

            try {
                //打开文件
                //File df = new File("D:/rang.txt");
                FileInputStream n1 = new FileInputStream("D:/rang.txt");
                Reader r = new InputStreamReader(n1, "GBK");
                BufferedReader b = new BufferedReader(r);
                String line = null;



                HashSet set = new HashSet<String>();
                while ((line = b.readLine()) != null) {
                    set.add(line);

                }
                Iterator iter = set.iterator();
                while (iter.hasNext()) {
                    System.out.println(iter.next());
                }


            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    public static void main(String[] args) {

    TestOutputStream.Test();

        }
    }







