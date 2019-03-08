package com.example.footballmvpfavorite.UI.Favorite.Presenter;

import android.content.Context;

import com.example.footballmvpfavorite.Model.TeamItem;

import java.util.List;

public interface FavoriteContract {
    interface View {
        void showDataList(List<TeamItem> teamItems);
        void hideRefresh();
        void showFailureMessage(String msg);
    }
    interface Presenter {
        void getDataList(Context context);
        void searchList(Context context, String serachText);
    }
}
