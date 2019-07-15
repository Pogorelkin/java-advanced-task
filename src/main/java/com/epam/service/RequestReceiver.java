package com.epam.service;

public interface RequestReceiver extends Runnable {
    @Override
    void run();
}
