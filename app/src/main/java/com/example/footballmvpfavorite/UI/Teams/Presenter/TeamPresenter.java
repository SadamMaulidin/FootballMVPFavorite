package com.example.footballmvpfavorite.UI.Teams.Presenter;

import com.example.footballmvpfavorite.Data.Remote.ApiClient;
import com.example.footballmvpfavorite.Data.Remote.ApiInterface;
import com.example.footballmvpfavorite.Model.TeamResponse;
import com.example.footballmvpfavorite.Utills.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamPresenter implements TeamContract.Presenter {

    private TeamContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public TeamPresenter(TeamContract.View view) {
        this.view = view;
    }

    @Override
    public void getTeams() {
        view.showProgress();

        Call<TeamResponse> call = apiInterface.getAllTeam(Constants.s, Constants.c);
        call.enqueue(new Callback<TeamResponse>() {
            @Override
            public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    view.showTeams(response.body().getTeams());
                } else {
                    view.showFailure("Tidak ada data");
                }
            }

            @Override
            public void onFailure(Call<TeamResponse> call, Throwable t) {
                view.hideProgress();
                view.showFailure(t.getMessage());
            }
        });

    }

    @Override
    public void getSearchTeams(String searchText) {
        view.showProgress();
        if (!searchText.isEmpty()) {
            Call<TeamResponse> call = apiInterface.getSearchTeams(searchText);
            call.enqueue(new Callback<TeamResponse>() {
                @Override
                public void onResponse(Call<TeamResponse> call, Response<TeamResponse> response) {
                    view.hideProgress();

                    if (response.body() != null) {
                        view.showTeams(response.body().getTeams());
                    } else {
                        view.showFailure("Tidak Ada Data");
                    }
                }

                @Override
                public void onFailure(Call<TeamResponse> call, Throwable t) {
                    view.hideProgress();
                    view.showFailure(t.getMessage());
                }
            });
        }
    }
}
