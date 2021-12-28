package com.github.lkapitman.util;

import org.apache.commons.codec.digest.DigestUtils;

public class HashUtil {
    
    public static String hashSHA256(String s) {
        return DigestUtils.sha256Hex(s);
    }

    public static String hashMD5(String s) {
        return DigestUtils.md5Hex(s);
    }
}
