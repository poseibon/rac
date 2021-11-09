package com.zwedu.rac.domain.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;

import java.util.List;

/**
 * jackson ObjectMapper实例的创建类
 * Created by xuyuepeng on 2019/9/29.
 */
public class ObjectMapperCreater {

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
