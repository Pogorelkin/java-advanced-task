package com.epam.service;

import com.epam.entities.BookingRequest;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class RequestGenerator {
    private List<String> hotels;
    private Random random = new Random();
    private LocalDate date;
    private String hotel;

    public BookingRequest generateRequest(int id) {
        hotel = hotels.get(random.nextInt(hotels.size()));
        date = LocalDate.of(2019, random.nextInt(11) + 1, random.nextInt(28) + 1);
        return new BookingRequest(id, "meh", hotel, date, "null");
    }

    public RequestGenerator(List<String> hotels) {
        this.hotels = hotels;
    }
}
