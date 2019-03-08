package com.example.footballmvpfavorite.UI.Favorite;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.footballmvpfavorite.Model.TeamItem;
import com.example.footballmvpfavorite.R;
import com.example.footballmvpfavorite.UI.Favorite.Presenter.FavoriteContract;
import com.example.footballmvpfavorite.UI.Favorite.Presenter.FavoritePresenter;
import com.example.footballmvpfavorite.UI.Teams.Adapter.TeamAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteFragment extends Fragment implements FavoriteContract.View {


    @BindView(R.id.rv_team_favorite)
    RecyclerView rvTeamFavorite;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    private FavoritePresenter favoritePresenter = new FavoritePresenter(this);

    public FavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        unbinder = ButterKnife.bind(this, view);

        setHasOptionsMenu(true);

        favoritePresenter.getDataList(getContext());
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                favoritePresenter.getDataList(getContext());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        favoritePresenter.getDataList(getContext());
    }

    @Override
    public void showDataList(List<TeamItem> teamItems) {
        rvTeamFavorite.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTeamFavorite.setAdapter(new TeamAdapter(getActivity(), teamItems));
    }

    @Override
    public void hideRefresh() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.search, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        final SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                favoritePresenter.searchList(getContext(), s);
                return true;
            }
        });
    }
}
