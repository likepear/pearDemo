package com.likepear;

import java.util.function.Consumer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Consumer[] consumers = new Consumer[3];
/*        for (int i=0;i<3;i++){
            int x = i;
            consumers[i] = new Consumer() {
                @Override
                public void accept(Object o) {
                    System.out.println(x);
                }
            };
        };

        for (int i=0;i<consumers.length;i++){
            consumers[i].accept("");
        }*/

        for(int i=0;i<consumers.length;i++){
            consumers[i] = System.out::println;
            consumers[i].accept(i);
        }

        System.out.println( "Hello World!" );
    }
}
