package top.pythong.cqupt.utils;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * ：
 * 加密算法类
 * @author ChangJiahong
 * @date 2019/7/16
 */
public class Encryption {

    public static boolean MD5Matches(String source, String ciphertext){
        String h=MD5Encode(source);
        return h.equals(ciphertext);
    }

    /**
     * 加密
     * @param source
     * @return
     */
    public static String MD5Encode(String source) {
        if (StringUtils.isBlank(source)) {
            return null;
        }
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ignored) {
        }
        byte[] encode = messageDigest.digest(source.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte anEncode : encode) {
            String hex = Integer.toHexString(0xff & anEncode);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }


    public static String Base64Encode(String source){
        try {
            return Base64.getUrlEncoder().encodeToString(source.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
