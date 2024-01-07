package com.api.apiestacionamento.config.datetime;

import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Configuration
public class DateTimeConfig {
    
    public static final String DATETIME_FORMAT = "YYYY-MM-dd'T'HH:mm:ss'Z'";
    public static LocalDateTimeSerializer DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        var javaTime = new JavaTimeModule();
        javaTime.addSerializer(DATETIME_SERIALIZER);
        return new ObjectMapper().registerModule(javaTime);

    }
}
