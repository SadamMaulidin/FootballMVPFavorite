package com.example.footballmvpfavorite.UI.Detail.Presenter;

import android.content.Context;
import android.os.Bundle;

import com.example.footballmvpfavorite.Model.TeamItem;

public interface DetailTeamContract {
    interface View {
        void showDetailTeam(TeamItem teamItem);
        void showSucces(String msg);
        void showFailure(String msg);
    }
    interface Presenter {
        void getDetailTeam(Bundle bundle);
        void addToFavorite(Context context, TeamItem teamItem);
        void removeFromFavorite(Context context, TeamItem teamItem);
        Boolean checkFavorite(Context context, TeamItem teamItem);
    }
}
