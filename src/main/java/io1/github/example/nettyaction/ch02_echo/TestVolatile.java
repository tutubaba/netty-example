package io1.github.example.nettyaction.ch02_echo;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tuyuelai on 2017/12/26.
 */
public class TestVolatile {
    private static boolean readFlag ;
    private static int num ;


   private static class MyThread extends Thread{
        //@Override
        public void run() {
            //System.out.println(readFlag);
           // System.out.println(num);

            while (!readFlag){
                //Thread.yield();
                System.out.println(num);
            }
        }
    }
    public static void main(String[] args) {
        final Set<String> myset = new HashSet<String>();
        final Set<String> myset2 = new HashSet<String>();

        myset.add("1");
        System.out.println(myset.size());
        myset.remove("1");
    }

}
