package moe.dic1911.esun_library.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecurityUtils {
    public static String generateSalt() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] saltBytes = new byte[64];
        secureRandom.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static String getHashedPassword(String raw, String salt) throws NoSuchAlgorithmException {
        byte[] b = (raw + salt).getBytes(StandardCharsets.UTF_8);
        byte[] passHash = MessageDigest.getInstance("SHA-512").digest(b);
        return encodeHexString(passHash);
    }

    public static String byteToHex(byte num) {
        char[] hexDigits = new char[2];
        hexDigits[0] = Character.forDigit((num >> 4) & 0xF, 16);
        hexDigits[1] = Character.forDigit((num & 0xF), 16);
        return new String(hexDigits);
    }

    public static String encodeHexString(byte[] byteArray) {
        StringBuilder hexStringBuffer = new StringBuilder();
        for (int i = 0; i < byteArray.length; i++) {
            hexStringBuffer.append(byteToHex(byteArray[i]));
        }
        return hexStringBuffer.toString();
    }
}
