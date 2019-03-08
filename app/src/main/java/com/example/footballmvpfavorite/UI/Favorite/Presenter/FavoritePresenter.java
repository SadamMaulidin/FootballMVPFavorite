package com.example.footballmvpfavorite.UI.Favorite.Presenter;

import android.content.Context;

import com.example.footballmvpfavorite.Data.Local.TeamDatabase;
import com.example.footballmvpfavorite.Model.TeamItem;

import java.util.ArrayList;
import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter{

    private FavoriteContract.View view;

    public FavoritePresenter(FavoriteContract.View view) {
        this.view = view;
    }

    private TeamDatabase teamDatabase;


    @Override
    public void getDataList(Context context) {
        teamDatabase = TeamDatabase.getTeamDatabase(context);

        if (teamDatabase.teamDAO().selectFafvorite() != null) {
            view.showDataList(teamDatabase.teamDAO().selectFafvorite());
        } else {
            view.showFailureMessage("Data Kosong");
        }
        view.hideRefresh();
    }

    @Override
    public void searchList(Context context, String serachText) {
        // Check the data, is exist or null ?
        if (!serachText.isEmpty()) {
            teamDatabase = TeamDatabase.getTeamDatabase(context);
            // Insert all of favorite team into variable List
            List<TeamItem> teamItems = teamDatabase.teamDAO().selectFafvorite();
            // Make a container (Penampung) for accommodate (Menampung) data we have selected
            // corresponding(sesuai) input user
            List<TeamItem> mTeamItems = new ArrayList<>();
            // Do looping to selecting data which is in table favorite
            for (TeamItem data: teamItems) {
                // Check team based on input user
                String teamName = data.getTeamName().toLowerCase();
                if (teamName.contains(serachText.toLowerCase())) {
                    // Enter the result from selection accommodate with inputan user
                    mTeamItems.add(data);
                }
            }
            view.showDataList(mTeamItems);
        }
    }
}
