package io1.github.example.nettyaction.ch02_echo;

import java.util.concurrent.TimeUnit;

/**
 * Created by tuyuelai on 2017/12/26.
 */
public class TestCancelThread extends Thread {
    private static volatile boolean cancelFlag = true;
    @Override
    public void run() {
        while (cancelFlag){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(".");

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new TestCancelThread().start();
        TimeUnit.SECONDS.sleep(10);
        cancelFlag = false;
    }
    public static void cancel(){
        cancelFlag = false;
    }
}
