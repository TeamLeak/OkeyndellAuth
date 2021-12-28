package com.github.lkapitman.json.userdata;

import com.fasterxml.jackson.annotation.*;

public class Player {
    private String username;
    private String password;
    private String lastIP;
    private String lastLogin;

    @JsonProperty("username")
    public String getUsername() { return username; }
    @JsonProperty("username")
    public void setUsername(String value) { this.username = value; }

    @JsonProperty("password")
    public String getPassword() { return password; }
    @JsonProperty("password")
    public void setPassword(String value) { this.password = value; }

    @JsonProperty("lastIP")
    public String getLastIP() { return lastIP; }
    @JsonProperty("lastIP")
    public void setLastIP(String value) { this.lastIP = value; }

    @JsonProperty("lastLogin")
    public String getLastLogin() { return lastLogin; }
    @JsonProperty("lastLogin")
    public void setLastLogin(String value) { this.lastLogin = value; }
}