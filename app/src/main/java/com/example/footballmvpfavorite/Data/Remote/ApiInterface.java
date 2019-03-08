package com.example.footballmvpfavorite.Data.Remote;

import com.example.footballmvpfavorite.Model.TeamResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/api/v1/json/1/search_all_teams.php")
    Call<TeamResponse> getAllTeam(@Query("s") String s, @Query("c") String c);

    @GET("/api/v1/json/1/searchteams.php")
    Call<TeamResponse> getSearchTeams(@Query("t") String t);
}
