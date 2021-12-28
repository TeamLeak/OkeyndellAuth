package com.github.lkapitman.json.config;


import com.fasterxml.jackson.annotation.*;

public class Plugin {
    private boolean enabled;
    private boolean dedicatedLogs;

    @JsonProperty("enabled")
    public boolean getEnabled() { return enabled; }
    @JsonProperty("enabled")
    public void setEnabled(boolean value) { this.enabled = value; }

    @JsonProperty("dedicatedLogs")
    public boolean getDedicatedLogs() { return dedicatedLogs; }
    @JsonProperty("dedicatedLogs")
    public void setDedicatedLogs(boolean value) { this.dedicatedLogs = value; }
}