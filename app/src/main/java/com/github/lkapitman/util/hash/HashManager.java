package com.github.lkapitman.util.hash;

public class HashManager {

    private final HashType hashType;

    public HashManager(HashType hashType) {
        this.hashType = hashType;
    }

    public String hash(String s) {
        return switch (hashType) {
            case SHA256 -> HashType.SHA256.hash(s);
            case MD_5 -> HashType.MD_5.hash(s);
        };
    }
}
