package com.eventflow.domain;

import com.eventflow.exception.CapacityExceededException;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(name = "total_capacity", nullable = false, updatable = false)
    private int totalCapacity;

    @Column(name = "available_capacity", nullable = false)
    private int availableCapacity;

    @Column(name = "event_time", nullable = false, updatable = false)
    private LocalDateTime eventTime;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public Event() {
    }

    public Event(String name, int totalCapacity, LocalDateTime eventTime ){
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Event name must not be Empty");
        }

        if (totalCapacity <=0){
            throw new IllegalArgumentException("Total Capacity must be greater than 0");
        }

        if (eventTime == null){
            throw new IllegalArgumentException("Event time must not be null");
        }

        this.id = UUID.randomUUID();
        this.name = name;
        this.totalCapacity = totalCapacity;
        this.availableCapacity = totalCapacity;
        this.eventTime = eventTime;
        this.createdAt = LocalDateTime.now();
    }

    public void reserveSeats(int seats){
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats to reserve must be greater than zero");
        }
        if (seats > availableCapacity) {
            throw new CapacityExceededException(
                    "Not enough available seats. Requested: " + seats +
                            ", Available: " + availableCapacity
            );
        }
        this.availableCapacity -= seats;
    }

    public void releaseSeats(int seats){
        if (seats <= 0) {
            throw new IllegalArgumentException("Seats to be released must be greater than zero");
        }
        if (seats + availableCapacity > totalCapacity) {
            throw new IllegalStateException("Releasing seats exceeds total capacity");
        }
        this.availableCapacity += seats;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public LocalDateTime getEventTime() {
        return eventTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
