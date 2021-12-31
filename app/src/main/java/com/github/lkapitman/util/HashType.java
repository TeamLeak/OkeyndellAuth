package com.github.lkapitman.util;

import org.apache.commons.codec.digest.DigestUtils;

public enum HashType {
    SHA256 {
        public String hash(String s) {
            return DigestUtils.sha256Hex(s);
        }    
    },
    MD_5 {
        public String hash(String s) {
            return DigestUtils.md5Hex(s);
        }
    };

    public abstract String hash(String s);
}
