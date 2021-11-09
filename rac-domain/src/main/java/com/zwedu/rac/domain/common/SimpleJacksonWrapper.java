package com.zwedu.rac.domain.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.*;

/**
 * Jackson工具类 提供json和对象互相转换的基本方法
 * <p>
 * Created by xuyuepeng on 2019/9/29.
 */
public final class SimpleJacksonWrapper {

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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
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
     *
     * @return 例如 List<User>
     */
    public static <T> List<T> toList(String json, Class<T> elementClass, ObjectMapper objectMapper) {
        return toCollection(json, ArrayList.class, elementClass, objectMapper);
    }

    /**
     * json字符串转成map
     *
     * @param json
     *
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
     *
     * @return map
     */
    public static Map<String, Object> toMap(String json, ObjectMapper objectMapper) {
        assertNotNull(objectMapper, ErrorMsgConstants.OBJECT_MAPPER_IS_NULL);
        return toGenericObject(json, new TypeReference<LinkedHashMap<String, Object>>() {
        }, objectMapper);
    }
}
