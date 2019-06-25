package com.example.querydsl.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by jiaoew on 2017/4/24.
 */
public class CustomModule extends SimpleModule {

    public static final String LOCAL_DATE_TIME_PATTERN = "yyyyMMdd'T'HHmmss";

    public static final String LOCAL_DATE_PATTERN = "yyyyMMdd";

    public static final String YEARMONTH_PATTERN = "yyyyMM";

    public CustomModule() {
        super(PackageVersion.VERSION);
        addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANT);
        addDeserializer(LocalDate.class, LocalDateDeserializer.INSTANT);
        addSerializer(LocalDateTime.class, LocalDateTimeSerializer.INSTANT);
        addSerializer(Timestamp.class, TimestampSerializer.INSTANT);
        addSerializer(LocalDate.class, LocalDateSerializer.INSTANT);
        addSerializer(Date.class, DateSerializer.INSTANT);
    }

    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

        static final LocalDateTimeSerializer INSTANT = new LocalDateTimeSerializer();

        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(value.atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN)));
        }
    }

    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

        static final LocalDateTimeDeserializer INSTANT = new LocalDateTimeDeserializer();

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String value = p.getValueAsString();
            return LocalDateTime.parse(value, DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN));
        }
    }

    public static class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

        static final LocalDateDeserializer INSTANT = new LocalDateDeserializer();

        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String value = p.getValueAsString();
            return LocalDate.parse(value, DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
        }
    }

    public static class TimestampSerializer extends JsonSerializer<Timestamp> {

        static final TimestampSerializer INSTANT = new TimestampSerializer();

        @Override
        public void serialize(Timestamp value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(value.toInstant().atZone(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN)));
        }
    }

    public static class LocalDateSerializer extends JsonSerializer<LocalDate> {

        static final LocalDateSerializer INSTANT = new LocalDateSerializer();

        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(value.atStartOfDay(ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN)));
        }
    }

    public static class DateSerializer extends JsonSerializer<Date> {

        static final DateSerializer INSTANT = new DateSerializer();

        @Override
        public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers) throws IOException, JsonProcessingException {
            gen.writeString(LocalDateTime.ofInstant(value.toInstant(), ZoneId.systemDefault())
                    .format(DateTimeFormatter.ofPattern(LOCAL_DATE_TIME_PATTERN)));
        }
    }

}
