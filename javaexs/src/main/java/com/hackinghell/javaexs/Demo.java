package com.hackinghell.javaexs;

public class Demo {
    public static void main(String[] args) {
        System.out.println("Main Thread has started");
        FeeThread feeThread = new FeeThread();
        feeThread.start(); // asking
        // feeThread.run() runs on main thread
    }
}
