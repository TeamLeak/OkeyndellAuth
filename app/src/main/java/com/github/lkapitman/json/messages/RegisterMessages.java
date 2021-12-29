package com.github.lkapitman.json.messages;

import com.fasterxml.jackson.annotation.*;

public class RegisterMessages {
    private String registerUsage;
    private String passwordNoEquals;
    private String haveAccount;
    private String registerError1;
    private String registerError2;

    @JsonProperty("registerUsage")
    public String getRegisterUsage() { return registerUsage; }
    @JsonProperty("registerUsage")
    public void setRegisterUsage(String value) { this.registerUsage = value; }

    @JsonProperty("passwordNoEquals")
    public String getPasswordNoEquals() { return passwordNoEquals; }
    @JsonProperty("passwordNoEquals")
    public void setPasswordNoEquals(String value) { this.passwordNoEquals = value; }

    @JsonProperty("haveAccount")
    public String getHaveAccount() { return haveAccount; }
    @JsonProperty("haveAccount")
    public void setHaveAccount(String value) { this.haveAccount = value; }

    @JsonProperty("registerError1")
    public String getRegisterError1() { return registerError1; }
    @JsonProperty("registerError1")
    public void setRegisterError1(String value) { this.registerError1 = value; }

    @JsonProperty("registerError2")
    public String getRegisterError2() { return registerError2; }
    @JsonProperty("registerError2")
    public void setRegisterError2(String value) { this.registerError2 = value; }
}



