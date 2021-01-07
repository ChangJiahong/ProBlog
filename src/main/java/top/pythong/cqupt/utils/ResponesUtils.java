package top.pythong.cqupt.utils;

import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 响应字符串
 * @author ChangJiahong
 * Create By Idea
 * @date 2021/1/7
 */
public class ResponesUtils {

    public static void print(HttpServletResponse response, Object obj) throws IOException {
        printJson(response, JSON.toJSONString(obj));
    }

    public static void printJson(HttpServletResponse response, String msg) throws IOException {
        print(response, msg, "application/json;charset=UTF-8");
    }

    public static void print(HttpServletResponse response, String msg) throws IOException {
        print(response, msg, "text/html;charset=UTF-8");
    }

    public static void print(HttpServletResponse response, String msg, String contentType) throws IOException {
        //在响应中主动告诉浏览器使用UTF-8编码格式来接收数据application/json;charset=UTF-8 text/html;charset=UTF-8
        response.setHeader("Content-Type", contentType);
        //可以使用封装类简写Content-Type，使用该方法则无需使用setCharacterEncoding
        response.setContentType(contentType);
        PrintWriter pw = response.getWriter();
        pw.print(msg);
    }
}
