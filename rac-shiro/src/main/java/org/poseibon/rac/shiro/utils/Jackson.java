package org.poseibon.rac.shiro.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.poseibon.common.utils.DateTimeUtils;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Jackson工具类 提供json和对象互相转换的基本方法
 *
 * @author qingchuan
 * @date 2020/5/15
 */
public final class Jackson {

    private Jackson() {
    }

    /**
     * 对象转换成json字符串,使用默认的objectMapper
     *
     * @param obj 需要转换的对象
     * @param <T>
     * @return json String
     */
    public static <T> String toJson(T obj) {
        return SimpleJacksonWrapper.toJson(obj);
    }


    /**
     * 对象转换成json字符串
     *
     * @param obj          需要转换的对象
     * @param objectMapper 自定义的mapper
     * @param <T>
     * @return json String
     */
    public static <T> String toJson(T obj, ObjectMapper objectMapper) {
        return SimpleJacksonWrapper.toJson(obj, objectMapper);
    }

    /**
     * json字符串转简单对象
     *
     * @param <T>
     * @param json  json
     * @param clazz 需要从json转换成的对象类型
     * @return 转换成的对象
     */
    public static <T> T toObject(String json, Class<T> clazz) {
        return SimpleJacksonWrapper.toObject(json, clazz);
    }

    /**
     * json字符串转简单对象
     *
     * @param <T>
     * @param json         json
     * @param clazz        需要从json转换成的对象类型
     * @param objectMapper 自定义的mapper
     * @return 转换成的对象
     */
    public static <T> T toObject(String json, Class<T> clazz, ObjectMapper objectMapper) {
        return SimpleJacksonWrapper.toObject(json, clazz, objectMapper);
    }

    /**
     * json字符串转复杂的泛型对象
     *
     * @param json          json
     * @param typeReference 例如：new TypeReference<List<User>>(){}
     * @param <T>           例如：List<User>
     * @return 转换成的对象
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        return SimpleJacksonWrapper.toGenericObject(json, typeReference);
    }

    /**
     * json字符串转复杂的泛型对象
     *
     * @param json          json
     * @param typeReference 例如：new TypeReference<List<User>>(){}
     * @param <T>           例如：List<User>
     * @param objectMapper  自定义的mapper
     * @return
     */
    public static <T> T toObject(String json, TypeReference<T> typeReference, ObjectMapper objectMapper) {
        return SimpleJacksonWrapper.toGenericObject(json, typeReference, objectMapper);
    }

    /**
     * json字符串转指定集合类型和元素类型的集合
     *
     * @param json            json
     * @param collectionClass 想转换的集合类型,例如:List.class|Set.class|ArrayList.class ..
     * @param elementClass    集合中的元素类型，例如：User
     * @param <T>
     * @return 指定类型的集合，例如 List<User>
     */
    public static <T> T toCollection(String json, Class<? extends Collection> collectionClass, Class<?>
            elementClass) {
        return SimpleJacksonWrapper.toCollection(json, collectionClass, elementClass);
    }

    /**
     * json字符串转指定集合类型和元素类型的集合
     *
     * @param json            json
     * @param collectionClass 想转换的集合类型,例如:List.class|Set.class|ArrayList.class ..
     * @param elementClass    集合中的元素类型，例如：User
     * @param objectMapper    自定义的mapper
     * @param <T>
     * @return 指定类型的集合，例如 List<User>
     */
    public static <T> T toCollection(String json, Class<? extends Collection> collectionClass, Class<?>
            elementClass, ObjectMapper objectMapper) {
        return SimpleJacksonWrapper.toCollection(json, collectionClass, elementClass, objectMapper);
    }

    /**
     * json字符串转指定元素类型的List
     *
     * @param json         json
     * @param elementClass 集合中的元素类型，例如：User
     * @param <T>
     * @return 例如 List<User>
     */
    public static <T> List<T> toList(String json, Class<T> elementClass) {
        return StringUtils.isBlank(json) ? Lists.newArrayList() : SimpleJacksonWrapper.toList(json, elementClass);
    }

    /**
     * json字符串转指定元素类型的List
     *
     * @param json         json
     * @param elementClass 集合中的元素类型，例如：User
     * @param objectMapper 自定义的mapper
     * @param <T>
     * @return 例如 List<User>
     */
    public static <T> List<T> toList(String json, Class<T> elementClass, ObjectMapper objectMapper) {
        return StringUtils.isBlank(json) ? Lists.newArrayList() :
                SimpleJacksonWrapper.toList(json, elementClass, objectMapper);
    }

    /**
     * json字符串转成map
     *
     * @param json json
     * @return map
     */
    public static Map<String, Object> toMap(String json) {
        return SimpleJacksonWrapper.toMap(json);
    }

    /**
     * json字符串转成map
     *
     * @param json         json
     * @param objectMapper 自定义的mapper
     * @return map
     */
    public static Map<String, Object> toMap(String json, ObjectMapper objectMapper) {
        return SimpleJacksonWrapper.toMap(json, objectMapper);
    }

    /**
     * 日期类型的序列化格式
     *
     * @author qingchuan
     * @date 2020/5/15
     */
    public static class DateSerializer {

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

    /**
     * 错误信息配置类
     *
     * @author qingchuan
     * @date 2020/5/15
     */
    public static class ErrorMsgConstants {

        /**
         * objectMapper非空校验信息
         */
        public static final String OBJECT_MAPPER_IS_NULL = "the parameter objectMapper cannot be null";

        /**
         * typeReference非空校验信息
         */
        public static final String TYPEREFERENCE_IS_NULL = "the parameter typeReference cannot be null";
    }

    /**
     * jackson ObjectMapper实例的创建类
     *
     * @author qingchuan
     * @date 2020/5/15
     */
    public static class ObjectMapperCreater {

        private ObjectMapperCreater() {
        }

        /**
         * 大部分采用jackson标准设置，特殊设置如下
         * 反序列化 json to object ({@link DeserializationFeature}),
         * 1、json中多余的属性，在反序列化时不会报错
         * 2、在不指定具体类型时，浮点数使用BigDecimal反序列化成对象，防止精度丢失
         * 3、在不指定具体类型时，整数使用BigInteger反序列化成对象，防止精度丢失
         * <p>
         * 序列化 object to json ({@link SerializationFeature}),
         * 1、日期类型序列化默认为时间戳,(jackson默认)
         * 2、忽略空Bean转json的错误
         * 3、未设置值得属性也会进行json序列化，对象类型的属性默认值为null,基本类型的参考java规范，例如：int默认值为0(jackson默认)
         * 4、char数组类型属性会被序列换成String，例如  char[] chars = new char[]{1,2} 会转换成 "\u0001\u0002"(jackson默认)
         * 5、Date|LocalDate|LocalDateTime 都会序列成Long
         * Character数组类型属性，序列化后还是json数组 (jackson默认)
         *
         * @return ObjectMapper
         */
        public static ObjectMapper createStanderObjectMapper() {
            /**
             * 处理日期
             */
            List<JsonSerializer> serializerList =
                    Lists.newArrayList(DateSerializer.LOCAL_DATE_SERIALIZER, DateSerializer.LOCAL_DATE_TIME_SERIALIZER);
            List<JsonDeserializer> deserializerList =
                    Lists.newArrayList(DateSerializer.LOCAL_DATE_DESERIALIZER, DateSerializer.LOCAL_DATE_TIME_DESERIALIZER);
            return createStanderObjectMapper(serializerList, deserializerList);
        }

        /**
         * 大部分采用jackson标准设置，特殊设置如下
         * 反序列化 json to object ({@link DeserializationFeature}),
         * 1、json中多余的属性，在反序列化时不会报错
         * 2、在不指定具体类型时，浮点数使用BigDecimal反序列化成对象，防止精度丢失
         * 3、在不指定具体类型时，整数使用BigInteger反序列化成对象，防止精度丢失
         * <p>
         * 序列化 object to json ({@link SerializationFeature}),
         * 1、日期类型序列化默认为时间戳,(jackson默认)
         * 2、忽略空Bean转json的错误
         * 3、未设置值得属性也会进行json序列化，对象类型的属性默认值为null,基本类型的参考java规范，例如：int默认值为0(jackson默认)
         * 4、char数组类型属性会被序列换成String，例如  char[] chars = new char[]{1,2} 会转换成 "\u0001\u0002"(jackson默认)
         * 5、Date 会序列成Long
         * Character数组类型属性，序列化后还是json数组 (jackson默认)
         *
         * @param serializerList   自定义的序列化处理器
         * @param deserializerList 自定义的反序列化处理器
         * @return ObjectMapper
         */
        public static ObjectMapper createStanderObjectMapper(List<JsonSerializer> serializerList,
                                                             List<JsonDeserializer> deserializerList) {
            ObjectMapper standerMapper = new ObjectMapper();

            /**
             * 从json字符串序列化成对象的配置
             */
            /**
             * 忽略反序列化中json字符串中存在，但java对象中不存在的字段
             */
            standerMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            /**
             * 浮点数使用BigDecimal反序列化成对象，防止精度丢失
             */
            standerMapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);

            /**
             * 整数使用BigInteger反序列化成对象，防止精度丢失
             */
            standerMapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true);

            /**
             * 匹配不上的枚举反序列化成null
             */
            standerMapper.configure(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL, true);

            /**
             * 从对象序列化成json字符串的配置
             */
            /**
             * 日期类型序列化默认为时间戳,(jackson默认)
             */
            standerMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
            /**
             * 忽略空Bean转json的错误
             */
            standerMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            /**
             * 对象序列化成json字符串时，为空的属性不会序列化
             */
            standerMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            SimpleModule module = new SimpleModule();
            if (CollectionUtils.isNotEmpty(serializerList)) {
                serializerList.forEach(item -> {
                    module.addSerializer(item.handledType(), item);
                });
            }
            if (CollectionUtils.isNotEmpty(deserializerList)) {
                deserializerList.forEach(item -> {
                    module.addDeserializer(item.handledType(), item);
                });
            }
            standerMapper.registerModule(module);
            return standerMapper;
        }
    }

    /**
     * Jackson工具类 提供json和对象互相转换的基本方法
     * <p>
     *
     * @author qingchuan
     * @date 2020/5/15
     */
    public static final class SimpleJacksonWrapper {

        private SimpleJacksonWrapper() {
        }

        private static ObjectMapper DEFAULT_OBJECT_MAPPER = ObjectMapperCreater.createStanderObjectMapper();

        /**
         * 验证必要参数是否为空，如果为空直接抛出IllegalArgumentException
         *
         * @param obj
         * @param errorMsg
         */
        private static void assertNotNull(Object obj, String errorMsg) {
            if (Objects.isNull(obj)) {
                throw new IllegalArgumentException(errorMsg);
            }
        }

        /**
         * 对象转换成json字符串，使用默认的objectMapper
         *
         * @param obj 需要转换的对象
         * @param <T>
         * @return json String
         */
        public static <T> String toJson(T obj) {
            return toJson(obj, DEFAULT_OBJECT_MAPPER);
        }

        /**
         * 对象转换成json字符串
         *
         * @param obj          需要转换的对象
         * @param objectMapper 自定义的mapper
         * @param <T>
         * @return json String
         */
        public static <T> String toJson(T obj, ObjectMapper objectMapper) {
            assertNotNull(objectMapper, ErrorMsgConstants.OBJECT_MAPPER_IS_NULL);
            try {
                return objectMapper.writeValueAsString(obj);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("toJson error.", e);
            }
        }

        /**
         * json字符串转简单对象
         *
         * @param <T>
         * @param json
         * @param clazz 需要从json转换成的对象类型
         * @return 转换成的对象
         */
        public static <T> T toObject(String json, Class<T> clazz) {
            return toObject(json, clazz, DEFAULT_OBJECT_MAPPER);
        }

        /**
         * json字符串转简单对象
         *
         * @param <T>
         * @param json
         * @param clazz        需要从json转换成的对象类型
         * @param objectMapper 自定义的mapper
         * @return 转换成的对象
         */
        public static <T> T toObject(String json, Class<T> clazz, ObjectMapper objectMapper) {
            assertNotNull(objectMapper, ErrorMsgConstants.OBJECT_MAPPER_IS_NULL);
            try {
                return objectMapper.readValue(json, clazz);
            } catch (IOException e) {
                throw new RuntimeException("toObject error.", e);
            }
        }

        /**
         * json字符串转复杂的泛型对象
         *
         * @param json
         * @param typeReference 例如：new TypeReference<List<User>>(){}
         * @param <T>           例如：List<User>
         * @return 转换成的对象
         */
        public static <T> T toGenericObject(String json, TypeReference<T> typeReference) {
            return toGenericObject(json, typeReference, DEFAULT_OBJECT_MAPPER);
        }

        /**
         * json字符串转复杂的泛型对象
         *
         * @param json
         * @param typeReference 例如：new TypeReference<List<User>>(){}
         * @param <T>           例如：List<User>
         * @param objectMapper  自定义的mapper
         * @return
         */
        public static <T> T toGenericObject(String json, TypeReference<T> typeReference, ObjectMapper objectMapper) {
            assertNotNull(objectMapper, ErrorMsgConstants.OBJECT_MAPPER_IS_NULL);
            assertNotNull(objectMapper, ErrorMsgConstants.TYPEREFERENCE_IS_NULL);
            try {
                return (T) (typeReference.getType().equals(String.class) ? (T) json
                        : objectMapper.readValue(json, typeReference));
            } catch (Exception e) {
                throw new RuntimeException("toGenericObject error.", e);
            }
        }

        /**
         * json字符串转指定集合类型和元素类型的集合
         *
         * @param json
         * @param collectionClass 想转换的集合类型,例如:List.class|Set.class|ArrayList.class ..
         * @param elementClass    集合中的元素类型，例如：User
         * @param <T>
         * @return 指定类型的集合，例如 List<User>
         */
        public static <T> T toCollection(String json, Class<? extends Collection> collectionClass, Class<?>
                elementClass) {
            return toCollection(json, collectionClass, elementClass, DEFAULT_OBJECT_MAPPER);
        }

        /**
         * json字符串转指定集合类型和元素类型的集合
         *
         * @param json
         * @param collectionClass 想转换的集合类型,例如:List.class|Set.class|ArrayList.class ..
         * @param elementClass    集合中的元素类型，例如：User
         * @param objectMapper    自定义的mapper
         * @param <T>
         * @return 指定类型的集合，例如 List<User>
         */
        public static <T> T toCollection(String json, Class<? extends Collection> collectionClass, Class<?>
                elementClass, ObjectMapper objectMapper) {
            assertNotNull(objectMapper, ErrorMsgConstants.OBJECT_MAPPER_IS_NULL);
            try {
                JavaType javaType =
                        objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClass);
                return objectMapper.readValue(json, javaType);
            } catch (Exception e) {
                throw new RuntimeException("toCollection error.", e);
            }
        }

        /**
         * json字符串转指定元素类型的List
         *
         * @param json
         * @param elementClass 集合中的元素类型，例如：User
         * @param <T>
         * @return 例如 List<User>
         */
        public static <T> List<T> toList(String json, Class<T> elementClass) {
            return toList(json, elementClass, DEFAULT_OBJECT_MAPPER);
        }

        /**
         * json字符串转指定元素类型的List
         *
         * @param json
         * @param elementClass 集合中的元素类型，例如：User
         * @param objectMapper 自定义的mapper
         * @param <T>
         * @return 例如 List<User>
         */
        public static <T> List<T> toList(String json, Class<T> elementClass, ObjectMapper objectMapper) {
            return toCollection(json, ArrayList.class, elementClass, objectMapper);
        }

        /**
         * json字符串转成map
         *
         * @param json
         * @return map
         */
        public static Map<String, Object> toMap(String json) {
            return toMap(json, DEFAULT_OBJECT_MAPPER);
        }

        /**
         * json字符串转成map
         *
         * @param json
         * @param objectMapper 自定义的mapper
         * @return map
         */
        public static Map<String, Object> toMap(String json, ObjectMapper objectMapper) {
            assertNotNull(objectMapper, ErrorMsgConstants.OBJECT_MAPPER_IS_NULL);
            return toGenericObject(json, new TypeReference<LinkedHashMap<String, Object>>() {
            }, objectMapper);
        }
    }
}
