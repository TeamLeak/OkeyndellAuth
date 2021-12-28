package com.github.lkapitman.json.config;

import com.fasterxml.jackson.annotation.*;

public class Player {
    private Plugin plugin;
    private Encryption encryption;
    private Database database;
    private Documentation documentation;

    @JsonProperty("plugin")
    public Plugin getPlugin() { return plugin; }
    @JsonProperty("plugin")
    public void setPlugin(Plugin value) { this.plugin = value; }

    @JsonProperty("encryption")
    public Encryption getEncryption() { return encryption; }
    @JsonProperty("encryption")
    public void setEncryption(Encryption value) { this.encryption = value; }

    @JsonProperty("database")
    public Database getDatabase() { return database; }
    @JsonProperty("database")
    public void setDatabase(Database value) { this.database = value; }

    @JsonProperty("Documentation")
    public Documentation getDocumentation() { return documentation; }
    @JsonProperty("Documentation")
    public void setDocumentation(Documentation value) { this.documentation = value; }
}

