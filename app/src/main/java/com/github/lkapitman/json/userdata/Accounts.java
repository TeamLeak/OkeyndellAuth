package com.github.lkapitman.json.userdata;

import com.fasterxml.jackson.annotation.*;
import java.util.List;

public class Accounts {
    private List<Player> players;

    @JsonProperty("players")
    public List<Player> getPlayers() { return players; }
    @JsonProperty("players")
    public void setPlayers(List<Player> value) { this.players = value; }
}
