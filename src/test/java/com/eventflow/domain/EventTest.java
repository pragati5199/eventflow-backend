package com.eventflow.domain;

import com.eventflow.exception.CapacityExceededException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    void shouldCreateEventWithValidCapacity() {
        Event event = new Event("Concert", 100, LocalDateTime.now().plusDays(1));

        assertEquals(100, event.getTotalCapacity());
        assertEquals(100, event.getAvailableCapacity());
    }

    @Test
    void shouldFailWhenCreatingEventWithZeroCapacity() {
        assertThrows(IllegalArgumentException.class,
                () -> new Event("Concert", 0, LocalDateTime.now().plusDays(1)));
    }

    @Test
    void shouldReserveSeatsSuccessfully() {
        Event event = new Event("Concert", 100, LocalDateTime.now().plusDays(1));

        event.reserveSeats(30);

        assertEquals(70, event.getAvailableCapacity());
    }

    @Test
    void shouldFailWhenReservingMoreSeatsThanAvailable() {
        Event event = new Event("Concert", 50, LocalDateTime.now().plusDays(1));

        assertThrows(CapacityExceededException.class,
                () -> event.reserveSeats(60));
    }

    @Test
    void shouldReleaseSeatsSuccessfully() {
        Event event = new Event("Concert", 100, LocalDateTime.now().plusDays(1));

        event.reserveSeats(40);
        event.releaseSeats(20);

        assertEquals(80, event.getAvailableCapacity());
    }

    @Test
    void shouldFailWhenReleasingMoreThanTotalCapacity() {
        Event event = new Event("Concert", 100, LocalDateTime.now().plusDays(1));

        assertThrows(IllegalStateException.class,
                () -> event.releaseSeats(110));
    }
}
