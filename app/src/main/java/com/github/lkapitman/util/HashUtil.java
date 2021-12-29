package com.github.lkapitman.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
    
    public static String hashSHA256(String s) {
        return DigestUtils.sha256Hex(s);
    }

    public static String hashMD5(String s) {
        return DigestUtils.md5Hex(s);
    }

    public static boolean isSame(String s, String s2) {
        if (s.equalsIgnoreCase(s2))
            return true;
        return false;
    }
}
