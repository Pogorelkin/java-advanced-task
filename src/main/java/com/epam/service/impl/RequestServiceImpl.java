package com.epam.service.impl;

import com.epam.entities.BookingRequest;
import com.epam.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

public class RequestServiceImpl implements RequestService {
    private Logger logger = LoggerFactory.getLogger(RequestServiceImpl.class);
    private Queue<BookingRequest> queue = new ArrayBlockingQueue<>(5);
    private int receivedRequestsAmount = 0;
    private int sentRequestsAmount = 0;

    @Override
    public synchronized BookingRequest receiveRequest() throws InterruptedException {
        BookingRequest request;
        while (queue.size() < 1) {
            try {
                wait();
            } catch (InterruptedException exception) {
                logger.info(exception.getMessage());
                throw exception;
            }
        }
        request = queue.poll();
        receivedRequestsAmount++;
        notify();
        return request;
    }

    @Override
    public int getReceivedRequestsAmount() {
        return receivedRequestsAmount;
    }

    @Override
    public int getSentRequestsAmount() {
        return sentRequestsAmount;
    }

    @Override
    public synchronized void sendRequest(BookingRequest bookingRequest) throws InterruptedException {
        while (queue.size() == 5) {
            try {
                wait();
            } catch (InterruptedException exception) {
                logger.info(exception.getMessage());
                throw exception;
            }
        }
        queue.add(bookingRequest);
        sentRequestsAmount++;
        notify();
    }


}
