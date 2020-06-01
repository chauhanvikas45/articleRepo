package com.book.dto;

import java.util.UUID;

public class TimeToReadResponseDTO {

    private UUID id;
    private TimeToRead timeToRead;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TimeToRead getTimeToRead() {
        return timeToRead;
    }

    public void setTimeToRead(TimeToRead timeToRead) {
        this.timeToRead = timeToRead;
    }
}
