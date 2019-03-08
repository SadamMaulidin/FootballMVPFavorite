package com.example.footballmvpfavorite.Data.Local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.footballmvpfavorite.Model.TeamItem;

@Database(entities = TeamItem.class, version = 1)
public abstract class TeamDatabase extends RoomDatabase {

    public abstract TeamDAO teamDAO();
    private static TeamDatabase teamDatabase;

    public static TeamDatabase getTeamDatabase(Context context) {
        synchronized (TeamDatabase.class){
            if (teamDatabase == null) {
                teamDatabase = Room.databaseBuilder(context, TeamDatabase.class, "dbTeam")
                        .allowMainThreadQueries()
                        .build();
            }
        } return teamDatabase;
    }
}
