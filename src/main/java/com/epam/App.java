package com.epam;

import com.epam.entities.SleepingThread;
import com.epam.enums.Hotels;
import com.epam.service.RequestGenerator;
import com.epam.service.RequestReceiver;
import com.epam.service.RequestSender;
import com.epam.service.RequestService;
import com.epam.service.impl.RequestReceiverImpl;
import com.epam.service.impl.RequestSenderImpl;
import com.epam.service.impl.RequestServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        RequestService requestService = new RequestServiceImpl();

        List<String> hotels = new ArrayList<>();
        for (Hotels hotel: Hotels.values()) {
            hotels.add(hotel.getHotel());
        }
        RequestGenerator requestGenerator = new RequestGenerator(hotels);
        RequestSender requestSender = new RequestSenderImpl(requestGenerator, requestService);
        RequestReceiver requestReceiver = new RequestReceiverImpl(requestService);
        SleepingThread memoryConsumer = new SleepingThread();

        for (int i = 0; i < 3; i++) {
            new Thread(requestSender).start();
            new Thread(requestReceiver).start();
            new Thread(memoryConsumer).start();
        }
    }
}
