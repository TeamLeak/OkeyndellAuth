package com.github.lkapitman.json.messages;

import com.fasterxml.jackson.annotation.*;
import com.github.lkapitman.util.ColorUtil;

public class EventMessages {
    private String inputPassword;

    @JsonProperty("inputPassword")
    public String getInputPassword() { return ColorUtil.format(inputPassword); }
    @JsonProperty("inputPassword")
    public void setInputPassword(String value) { this.inputPassword = value; }
}

