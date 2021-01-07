package top.pythong.cqupt.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * ：
 *  gson 工具类
 * @author CJH
 * on 2019/3/14
 */

public class GsonUtils {

//    private static final Gson gson = new Gson();


    /**
     * 对象转josn字符串
     * @param object Any Object
     * @return json String
     */
    public static String toJsonString(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 反序列化
     */
    public static <T> T json2Obj(String json, Class<T> tClass){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
