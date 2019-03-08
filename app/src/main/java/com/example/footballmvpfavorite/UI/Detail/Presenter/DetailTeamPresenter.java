package com.example.footballmvpfavorite.UI.Detail.Presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.footballmvpfavorite.Data.Local.TeamDatabase;
import com.example.footballmvpfavorite.Model.TeamItem;
import com.example.footballmvpfavorite.Utills.Constants;

public class DetailTeamPresenter implements DetailTeamContract.Presenter {

    private TeamDatabase teamDatabase;

    private DetailTeamContract.View view;

    public DetailTeamPresenter(DetailTeamContract.View view) {
        this.view = view;
    }

    @Override
    public void getDetailTeam(Bundle bundle) {
        if (bundle != null) {
            TeamItem teamItem = (TeamItem) bundle.getSerializable(Constants.KEY_DATA);
            view.showDetailTeam(teamItem);
        } else {
            view.showFailure("Data Kosong");
        }

    }

    @Override
    public void addToFavorite(Context context, TeamItem teamItem) {
        teamDatabase = TeamDatabase.getTeamDatabase(context);
        teamDatabase.teamDAO().insertItem(teamItem);
        view.showSucces("Success to Add");
    }

    @Override
    public void removeFromFavorite(Context context, TeamItem teamItem) {
        teamDatabase = TeamDatabase.getTeamDatabase(context);
        teamDatabase.teamDAO().delete(teamItem);
        view.showSucces("Success to Delete");
    }

    @Override
    public Boolean checkFavorite(Context context, TeamItem teamItem) {
        Boolean bFafvorite = false;
        teamDatabase = TeamDatabase.getTeamDatabase(context);
        return bFafvorite = teamDatabase.teamDAO().selectedItem(teamItem.getIdTeam()) != null;
    }
}
