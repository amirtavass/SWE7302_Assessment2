package com.cinema.modern.observer;

import java.util.ArrayList;
import java.util.List;

public class BookingEventManager {
    private final List<BookingObserver> listeners = new ArrayList<>();

    public void subscribe(BookingObserver listener) {
        listeners.add(listener);
    }

    public void unsubscribe(BookingObserver listener) {
        listeners.remove(listener);
    }


    public List<String> notifySubscribers(String bookingDescription, double price) {
        List<String> logs = new ArrayList<>();
        for (BookingObserver listener : listeners) {
            logs.add(listener.update(bookingDescription, price));
        }
        return logs;
    }
}