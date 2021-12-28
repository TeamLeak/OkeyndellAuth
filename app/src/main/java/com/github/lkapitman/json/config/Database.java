package com.github.lkapitman.json.config;

import com.fasterxml.jackson.annotation.*;

public class Database {
    private String databaseType;
    private String host;
    private String port;
    private String user;
    private String password;

    @JsonProperty("databaseType")
    public String getDatabaseType() { return databaseType; }
    @JsonProperty("databaseType")
    public void setDatabaseType(String value) { this.databaseType = value; }

    @JsonProperty("host")
    public String getHost() { return host; }
    @JsonProperty("host")
    public void setHost(String value) { this.host = value; }

    @JsonProperty("port")
    public String getPort() { return port; }
    @JsonProperty("port")
    public void setPort(String value) { this.port = value; }

    @JsonProperty("user")
    public String getUser() { return user; }
    @JsonProperty("user")
    public void setUser(String value) { this.user = value; }

    @JsonProperty("password")
    public String getPassword() { return password; }
    @JsonProperty("password")
    public void setPassword(String value) { this.password = value; }
}
