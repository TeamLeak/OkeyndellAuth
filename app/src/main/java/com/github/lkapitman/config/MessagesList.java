package com.github.lkapitman.config;

import java.io.File;
import java.io.IOException;

import com.github.lkapitman.App;
import com.github.lkapitman.json.messages.EventMessages;
import com.github.lkapitman.json.messages.LoginMessages;
import com.github.lkapitman.json.messages.Messages;
import com.github.lkapitman.json.messages.MessagesConverter;
import com.github.lkapitman.json.messages.RegisterMessages;

public class MessagesList {
    
    private final App instance;
    
    private String entryMessage;
    private LoginMessages loginMessages;
    private RegisterMessages registerMessages;
    private String eventMessages;

    public MessagesList(App instance) {
        this.instance = instance;
    }

    public void init() throws IOException {
        String locale = instance.getSettings().getPluginSettings().getLanguage();
        if (locale.equalsIgnoreCase("en")) {
            Messages messages = MessagesConverter.fromJsonString(new File(instance.getDataFolder(), "/messages_en.json"));
            entryMessage = messages.getEntryMessage();
            loginMessages = messages.getLoginMessages();
            registerMessages = messages.getRegisterMessages();
            eventMessages = messages.getInputPassword();
        }
        if (locale.equalsIgnoreCase("ru")) {
            Messages messages = MessagesConverter.fromJsonString(new File(instance.getDataFolder(), "/messages_ru.json"));
            entryMessage = messages.getEntryMessage();
            loginMessages = messages.getLoginMessages();
            registerMessages = messages.getRegisterMessages();
            eventMessages = messages.getInputPassword();
        }                
    }

    public String getEntryMessage() {
        return entryMessage;
    }

    public LoginMessages getLoginMessages() {
        return loginMessages;
    }

    public RegisterMessages getRegisterMessages() {
        return registerMessages;
    }

    public String getInputPassword() {
        return eventMessages;
    }
    
}
