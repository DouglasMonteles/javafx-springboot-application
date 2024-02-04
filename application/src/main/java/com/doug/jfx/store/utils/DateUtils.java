package com.doug.jfx.store.utils;

import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    public static final ZoneId ZONE_ID = ZoneId.of("America/Sao_Paulo");
    public static final String DATE_BR_PATTERN = "dd/MM/yyyy";

    // HH: 24hrs
    // hh: 12hrs
    public static final String TIME_BR_PATTERN = "HH:mm:ss";

    public static Instant getDateTime() {
        return Instant.now().atZone(ZONE_ID).toInstant();
    }

    public static String datePtBr(Instant date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_BR_PATTERN);
        return date.atZone(ZONE_ID).format(dateFormatter);
    }

    public static String timePtBr(Instant date) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_BR_PATTERN);
        return date.atZone(ZONE_ID).format(timeFormatter);
    }
}
