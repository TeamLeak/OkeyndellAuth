package com.github.lkapitman.json.config;

import com.fasterxml.jackson.annotation.*;

public class Documentation {
    private String encryption;
    private String database;
    private String logs;

    @JsonProperty("encryption")
    public String getEncryption() { return encryption; }
    @JsonProperty("encryption")
    public void setEncryption(String value) { this.encryption = value; }

    @JsonProperty("database")
    public String getDatabase() { return database; }
    @JsonProperty("database")
    public void setDatabase(String value) { this.database = value; }

    @JsonProperty("logs")
    public String getLogs() { return logs; }
    @JsonProperty("logs")
    public void setLogs(String value) { this.logs = value; }
}
