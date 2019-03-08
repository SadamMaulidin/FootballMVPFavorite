package com.example.footballmvpfavorite.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.example.footballmvpfavorite.Utills.Constants;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "teams")
public class TeamItem implements Serializable {

    @PrimaryKey
    @ColumnInfo(name = Constants.COLUMN_ID)
    @SerializedName("idTeam")
    @NonNull private String idTeam;

    @ColumnInfo(name = Constants.COLUMN_TEAM_NAME)
    @SerializedName("strTeam")
    private String teamName;

    @ColumnInfo(name = Constants.COLUMN_TEAM_LOGO)
    @SerializedName("strTeamBadge")
    private String teamLogo;

    @ColumnInfo(name = Constants.COLUMN_LEAGUE)
    @SerializedName("strLeague")
    private String league;

    @ColumnInfo(name = Constants.COLUMN_DESCRIPT)
    @SerializedName("strDescriptionEN")
    private String desc;

    public TeamItem(@NonNull String idTeam, String teamName, String teamLogo, String league, String desc) {
        this.idTeam = idTeam;
        this.teamName = teamName;
        this.teamLogo = teamLogo;
        this.league = league;
        this.desc = desc;
    }

    @NonNull
    public String getIdTeam() {
        return idTeam;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamLogo() {
        return teamLogo;
    }

    public String getLeague() {
        return league;
    }

    public String getDesc() {
        return desc;
    }
}
