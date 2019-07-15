package com.epam.service;

public interface RequestSender extends Runnable {
    @Override
    void run();
}
