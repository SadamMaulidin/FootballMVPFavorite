package com.example.footballmvpfavorite.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TeamResponse {

    @SerializedName("teams")
    List<TeamItem> teams;

    public List<TeamItem> getTeams() {
        return teams;
    }

    public void setTeams(List<TeamItem> teams) {
        this.teams = teams;
    }
}
