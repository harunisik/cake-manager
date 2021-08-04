package com.harunisik.cakes.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * Json to String converter String to Json converter Object Mapper Common Class
 */
@Slf4j
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper();

    //Object to Json converter
    public static String objectToJson(Object jsonObject) {
        String json;
        if (jsonObject == null) {
            json = "Null Object";
        } else {
            try {
                json = mapper.writeValueAsString(jsonObject);
            } catch (Exception e) {
                json = "Object could not be converted to Json Format";
                log.error(e.getMessage());
            }
        }
        return json;
    }
}