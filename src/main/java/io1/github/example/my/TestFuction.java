package io1.github.example.my;

/**
 * Created by tuyuelai on 2018/1/15.
 */
public class TestFuction {
    public static void main(String[] args) {
        new Thread( () -> {
            int i = 0;
            for(;;){
                i++;
                System.out.println(".");
                if (i >=100){
                    break;
                }
            }
        }).start();
    }
}


class e implements Runnable{

    @Override
    public void run() {

    }
}