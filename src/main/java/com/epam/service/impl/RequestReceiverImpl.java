package com.epam.service.impl;

import com.epam.entities.BookingRequest;
import com.epam.service.RequestReceiver;
import com.epam.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestReceiverImpl implements RequestReceiver {
    private RequestService requestService;
    private BookingRequest tempRequest;
    private Logger logger = LoggerFactory.getLogger(RequestReceiverImpl.class);
    private int requestsWasReceived = 0;
    private boolean receiveRequests = true;

    public RequestReceiverImpl(RequestService requestService) {
        this.requestService = requestService;
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        while (receiveRequests) {
            try {
                if (requestService.getReceivedRequestsAmount() == 15) {stopReceive(); break;}
                tempRequest = requestService.receiveRequest();
                logger.info(new StringBuilder().append("Request ").append(requestService.getReceivedRequestsAmount()).append(tempRequest.toString()).append(" were received. Receiver is ").append(threadId).toString());
            } catch (InterruptedException e) {
                logger.info(e.getMessage());
            }
        }
    }

    public void setReceiveRequests(boolean receiveRequests) {
        this.receiveRequests = receiveRequests;
    }

    public void stopReceive(){
        setReceiveRequests(false);
    }
}
