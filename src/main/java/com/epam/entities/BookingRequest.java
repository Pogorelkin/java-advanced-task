package com.epam.entities;

import java.time.LocalDate;

public class BookingRequest {
    private Integer id;
    private String ad;
    private String hotel;
    private LocalDate date;
    private String requestSender;

    public BookingRequest(Integer id, String ad, String hotel, LocalDate date, String requestSender) {
        this.id = id;
        this.ad = ad;
        this.hotel = hotel;
        this.date = date;
        this.requestSender = requestSender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getRequestSender() {
        return requestSender;
    }

    public void setRequestSender(String requestSender) {
        this.requestSender = requestSender;
    }

    @Override
    public String toString() {
        return "BookingRequest{" +
                "id=" + id +
                ", hotel='" + hotel + '\'' +
                ", date=" + date +
                ", requestSender='" + requestSender + '\'' +
                '}';
    }
}
