package io1.github.example.event;

/**
 * Created by tuyuelai on 2018/1/2.
 */
public interface Callback {
    public void onCall();
}

class A {
    Callback callback;
    public void register(Callback callback){
        this.callback = callback;
    }
    public void call(){
        callback.onCall();
    }
}

class Client{
    public static void main(String[] args) {
        A a = new A();
        a.register(new Callback() {
            @Override
            public void onCall() {

            }
        });
        a.call();
    }
}