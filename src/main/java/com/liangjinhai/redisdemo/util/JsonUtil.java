package com.liangjinhai.redisdemo.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;


public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private static final ObjectMapper mapper_nonnull = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String object2Json(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("对象转json异常", e);
        }
        return null;
    }

    /**
     * JSON转对象
     * @param json
     * @param valueType
     * @param <T>
     * @return
     */
    public static <T> T json2Object(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (IOException e) {
            logger.error("json转对象异常", e);
        }
        return null;
    }

    /**
     * JSON转List
     * @param json
     * @param valueTypeRef
     * @param <T>
     * @return
     */
    public static <T> T json2List(String json, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            logger.error("json转对象异常", e);
        }
        return null;
    }

    public static String object2JsonNoNull(Object obj) {
        try {
            return mapper_nonnull.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("对象转json异常", e);
        }
        return null;
    }
}
