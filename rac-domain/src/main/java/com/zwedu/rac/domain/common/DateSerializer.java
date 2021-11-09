package com.zwedu.rac.domain.common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.poseibon.common.utils.DateTimeUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 日期类型的序列化格式
 * Created by xuyuepeng on 2019/10/23.
 */
public class DateSerializer {

    public static JsonSerializer<LocalDate> LOCAL_DATE_SERIALIZER = new JsonSerializer<LocalDate>() {
        @Override
        public void serialize(LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            if (value != null) {
                gen.writeString(String.valueOf(DateTimeUtils.convertToTimeMillis(value)));
            }
        }

        @Override
        public Class<LocalDate> handledType() {
            return LocalDate.class;
        }
    };

    public static JsonDeserializer<LocalDate> LOCAL_DATE_DESERIALIZER = new JsonDeserializer<LocalDate>() {
        @Override
        public LocalDate deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            String times = p.getText();
            LocalDate result = null;
            if (StringUtils.isNotEmpty(times)) {
                result = DateTimeUtils.convertToLocalDate(Long.valueOf(times));
            }
            return result;
        }

        @Override
        public Class<LocalDate> handledType() {
            return LocalDate.class;
        }
    };

    public static JsonSerializer<LocalDateTime> LOCAL_DATE_TIME_SERIALIZER = new JsonSerializer<LocalDateTime>() {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            if (value != null) {
                gen.writeString(String.valueOf(DateTimeUtils.convertToTimeMillis(value)));
            }
        }

        @Override
        public Class<LocalDateTime> handledType() {
            return LocalDateTime.class;
        }
    };

    public static JsonDeserializer<LocalDateTime> LOCAL_DATE_TIME_DESERIALIZER = new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt)
                throws IOException, JsonProcessingException {
            String times = p.getText();
            LocalDateTime result = null;
            if (StringUtils.isNotEmpty(times)) {
                result = DateTimeUtils.convertToLocalDateTime(Long.valueOf(times));
            }
            return result;
        }

        @Override
        public Class<LocalDateTime> handledType() {
            return LocalDateTime.class;
        }
    };
}
