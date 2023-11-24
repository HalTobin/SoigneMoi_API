package com.soignemoi.soignemoiapi.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

@Service
public class DateService {

    private static final Logger logger = LoggerFactory.getLogger(DateService.class);

    public Long getAvailableTimestamp() {
        LocalDate currentDate = LocalDate.now();
        LocalTime availableTime = LocalTime.of(1, 0);

        LocalDateTime availableDateTime = LocalDateTime.of(currentDate, availableTime);
        availableDateTime = availableDateTime.plusDays(1);

        return availableDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

}
