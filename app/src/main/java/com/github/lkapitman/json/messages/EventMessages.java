package com.github.lkapitman.json.messages;

import com.fasterxml.jackson.annotation.*;

public class EventMessages {
    private String inputPassword;

    @JsonProperty("inputPassword")
    public String getInputPassword() { return inputPassword; }
    @JsonProperty("inputPassword")
    public void setInputPassword(String value) { this.inputPassword = value; }
}

