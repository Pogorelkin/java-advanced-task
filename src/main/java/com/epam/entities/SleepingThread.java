package com.epam.entities;

public class SleepingThread extends Thread  {
    @Override
    public void run() {
        super.run();
        try {
            sleep(500) ;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
