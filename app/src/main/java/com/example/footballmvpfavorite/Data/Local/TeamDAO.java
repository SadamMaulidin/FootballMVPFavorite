package com.example.footballmvpfavorite.Data.Local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.footballmvpfavorite.Model.TeamItem;
import com.example.footballmvpfavorite.Utills.Constants;

import java.util.List;

@Dao
public interface TeamDAO {

    @Insert
    void insertItem(TeamItem teamItem);

    @Query("SELECT * FROM teams WHERE idTeam = :id")
    TeamItem selectedItem(String id);

    @Delete
    void delete(TeamItem teamItem);

    @Query("SELECT * FROM teams ORDER BY teamName ASC")
    List<TeamItem> selectFafvorite();
}
