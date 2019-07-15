package com.epam.service.impl;

import com.epam.entities.BookingRequest;
import com.epam.service.RequestGenerator;
import com.epam.service.RequestSender;
import com.epam.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestSenderImpl implements RequestSender {
    private RequestService requestService;
    private RequestGenerator requestGenerator;
    private Logger logger = LoggerFactory.getLogger(RequestSenderImpl.class);
    BookingRequest tempRequest;
    private boolean sendRequests = true;
    private int requestId = 1;

    public RequestSenderImpl(RequestService requestService) {
        this.requestService = requestService;
    }

    public RequestSenderImpl(RequestGenerator requestGenerator, RequestService requestService) {
        this.requestGenerator = requestGenerator;
        this.requestService = requestService;
    }

    @Override
    public void run() {
        long threadId = Thread.currentThread().getId();
        while (sendRequests) {
            try {
                if (requestService.getSentRequestsAmount() == 15) {
                    stopSending();
                    break;
                }
                tempRequest = requestGenerator.generateRequest(requestId);
                requestId++;
                tempRequest.setRequestSender(Long.toString(threadId));
                requestService.sendRequest(tempRequest);
                logger.info(new StringBuilder().append("Request").append(requestService.getSentRequestsAmount()).append(tempRequest.toString()).append("was sent").toString());
            } catch (InterruptedException exc) {
                logger.info(exc.getMessage());
            }
        }
    }

    public void stopSending() {
        setSendRequests(false);
    }

    public void setSendRequests(boolean sendRequests) {
        this.sendRequests = sendRequests;
    }
}
