package com.epam.service.impl;

import com.epam.entities.BookingRequest;
import com.epam.service.RequestGenerator;
import com.epam.service.RequestSender;
import com.epam.service.RequestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestSenderImpl  implements RequestSender {
    private RequestService requestService;
    private RequestGenerator requestGenerator;
    private Logger logger = LoggerFactory.getLogger(RequestSenderImpl.class);
    BookingRequest tempRequest;
    private int requestsWasSent = 0;

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
        while (requestsWasSent <= 3) {
            tempRequest = requestGenerator.generateRequest();
            tempRequest.setRequestSender(Long.toString(threadId));

            try {
                requestService.sendRequest(tempRequest);
            } catch (InterruptedException exc) {
                logger.info(exc.getMessage());
            }
            requestsWasSent++;
        }

    }
}
