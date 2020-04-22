package com.test.nio;

import org.testng.annotations.Test;
import org.omg.Messaging.SyncScopeHelper;

import javax.sound.midi.Soundbank;
import java.nio.ByteBuffer;

/**
 * nio 测试案例
 * @author likepear
 *  4个重要参数   mark<= position(当前可操作元素的位置) <= limit <= capacity（容量）
 *  ByteBuffer  CharBuffer IntegerBuffer LongBuffer FloatBuffer DoubleBuffer  (boolean类型没有buffer)
 *
 */
public class NioDemo {

    public static void main(String[] args) {

        


    }



    @Test
    public void test(){

        //分配指定容量的缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        //--------------------------
        System.out.println(buffer.position());  //0
        System.out.println(buffer.limit());     //1024
        System.out.println(buffer.capacity());  //1024

        //存储数据
        buffer.put(new String("abcde").getBytes());


        //--------------------------
        System.out.println(buffer.position());  //change
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());

        //切换模式(读)
        buffer.flip();


        //--------------------------
        System.out.println(buffer.position()); //change
        System.out.println(buffer.limit());  //change
        System.out.println(buffer.capacity());



        //读取数据
        byte[] bytes = new byte[100];
        buffer.get(bytes, 0, 3);
        System.out.println(new String(bytes));
        buffer.mark(); //设定标记



        //--------------------------
        System.out.println("mark:"+buffer.mark().position()+"position:"+buffer.position()); //change
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());


        buffer.get(bytes,3,1);
        System.out.println(buffer.position()); //change
        System.out.println("-------------------");


        buffer.reset(); //position置为mark的位置
        System.out.println("mark:"+buffer.mark().position()+"position:"+buffer.position());


        buffer.rewind();   //倒带  （将position置为0，清空mark）


        //--------------------------
        System.out.println("mark:"+buffer.mark().position()+"position:"+buffer.position());
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());



        buffer.clear();  //重置指针的位置 (数据变为可覆盖状态,未被删除)



        //--------------------------
        System.out.println(buffer.position()); //change
        System.out.println(buffer.limit());
        System.out.println(buffer.capacity());


    }











}
