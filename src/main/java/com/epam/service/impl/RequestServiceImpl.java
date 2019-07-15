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

    @Override
    public synchronized BookingRequest receiveRequest() throws InterruptedException {
        BookingRequest request;
        while (queue.size() < 1) {
            try {
                logger.info("I wait for some requests");
                wait();
            } catch (InterruptedException exception) {
                logger.info(exception.getMessage());
                throw exception;
            }
        }
        request = queue.poll();
        logger.info(new StringBuilder().append("Request").append(request.toString()).append("was received").toString());
        notify();
        return request;
    }

    @Override
    public synchronized void sendRequest(BookingRequest bookingRequest) throws InterruptedException {
        while (queue.size() == 5) {
            try {
                logger.info("I wait for free queue space");
                wait();
            } catch (InterruptedException exception) {
                logger.info(exception.getMessage());
                throw exception;
            }
            queue.add(bookingRequest);
            logger.info(new StringBuilder().append("Request").append(bookingRequest.toString()).append("was sent").toString());
            notify();
        }
    }
}
