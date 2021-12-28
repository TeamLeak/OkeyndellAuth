package com.github.lkapitman.json.config;

import com.fasterxml.jackson.annotation.*;

public class Encryption {
    private boolean useEncrypt;
    private String encryptType;

    @JsonProperty("useEncrypt")
    public boolean getUseEncrypt() { return useEncrypt; }
    @JsonProperty("useEncrypt")
    public void setUseEncrypt(boolean value) { this.useEncrypt = value; }

    @JsonProperty("encryptType")
    public String getEncryptType() { return encryptType; }
    @JsonProperty("encryptType")
    public void setEncryptType(String value) { this.encryptType = value; }
}
