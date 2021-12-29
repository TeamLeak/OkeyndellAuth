package com.github.lkapitman.json.messages;

import com.fasterxml.jackson.annotation.*;

public class Messages {
    private RegisterMessages registerMessages;
    private LoginMessages loginMessages;
    private String inputPassword;
    private String entryMessage;

    @JsonProperty("registerMessages")
    public RegisterMessages getRegisterMessages() { return registerMessages; }
    @JsonProperty("registerMessages")
    public void setRegisterMessages(RegisterMessages value) { this.registerMessages = value; }

    @JsonProperty("loginMessages")
    public LoginMessages getLoginMessages() { return loginMessages; }
    @JsonProperty("loginMessages")
    public void setLoginMessages(LoginMessages value) { this.loginMessages = value; }

    @JsonProperty("inputPassword")
    public String getInputPassword() { return inputPassword; }
    @JsonProperty("inputPassword")
    public void setInputPassword(String value) { this.inputPassword = value; }

    @JsonProperty("entryMessage")
    public String getEntryMessage() { return entryMessage; }
    @JsonProperty("entryMessage")
    public void setEntryMessage(String value) { this.entryMessage = value; }
}

