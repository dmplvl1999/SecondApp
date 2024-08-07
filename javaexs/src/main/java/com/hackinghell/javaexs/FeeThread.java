package com.hackinghell.javaexs;

public class FeeThread extends Thread {
    @Override
    public void run() {
        super.run();
        System.out.println("fee dept sign-- no dues");

        for (int i = 1; i < 20; i++) {
            try {
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "--" + i);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
