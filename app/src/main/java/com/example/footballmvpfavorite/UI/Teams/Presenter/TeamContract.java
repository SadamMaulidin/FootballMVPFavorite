package com.example.footballmvpfavorite.UI.Teams.Presenter;

import com.example.footballmvpfavorite.Model.TeamItem;

import java.util.List;

public interface TeamContract {
    interface View {
        void showProgress();
        void hideProgress();
        void showTeams(List<TeamItem> teamItems);
        void showFailure(String msg);
    }

    interface Presenter {
        void getTeams();
        void getSearchTeams(String searchText);
    }
}
