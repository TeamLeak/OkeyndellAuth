package com.github.lkapitman.json.messages;

import com.fasterxml.jackson.annotation.*;

public class LoginMessages {
    private String loginUsage;
    private String noAccount;
    private String attempsError;
    private String passwordNoEqual;

    @JsonProperty("loginUsage")
    public String getLoginUsage() { return loginUsage; }
    @JsonProperty("loginUsage")
    public void setLoginUsage(String value) { this.loginUsage = value; }

    @JsonProperty("noAccount")
    public String getNoAccount() { return noAccount; }
    @JsonProperty("noAccount")
    public void setNoAccount(String value) { this.noAccount = value; }

    @JsonProperty("attempsError")
    public String getAttempsError() { return attempsError; }
    @JsonProperty("attempsError")
    public void setAttempsError(String value) { this.attempsError = value; }

    @JsonProperty("passwordNoEqual")
    public String getPasswordNoEqual() { return passwordNoEqual; }
    @JsonProperty("passwordNoEqual")
    public void setPasswordNoEqual(String value) { this.passwordNoEqual = value; }
}