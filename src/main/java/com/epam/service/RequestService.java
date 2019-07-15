package com.epam.service;

import com.epam.entities.BookingRequest;

public interface RequestService {
    BookingRequest receiveRequest() throws InterruptedException;

    public void sendRequest(BookingRequest bookingRequest) throws InterruptedException;
}
