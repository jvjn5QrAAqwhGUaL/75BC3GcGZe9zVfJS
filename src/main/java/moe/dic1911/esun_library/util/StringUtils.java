package moe.dic1911.esun_library.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class StringUtils {
    public static String byteArrayToString(byte[] b) {
        return byteArrayToString(b, StandardCharsets.UTF_8);
    }

    public static String byteArrayToString(byte[] b, Charset cs) {
        return new String(b, cs);
    }

    public static boolean isNullOrEmpty(String s) {
        return s == null || s.isEmpty();
    }
}
