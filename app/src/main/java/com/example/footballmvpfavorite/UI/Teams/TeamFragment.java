package com.example.footballmvpfavorite.UI.Teams;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.footballmvpfavorite.Model.TeamItem;
import com.example.footballmvpfavorite.R;
import com.example.footballmvpfavorite.UI.Teams.Adapter.TeamAdapter;
import com.example.footballmvpfavorite.UI.Teams.Presenter.TeamContract;
import com.example.footballmvpfavorite.UI.Teams.Presenter.TeamPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamFragment extends Fragment implements TeamContract.View {

    @BindView(R.id.edtSearch)
    EditText edtSearch;
    @BindView(R.id.btnSearch)
    ImageButton btnSearch;
    @BindView(R.id.rv_team)
    RecyclerView rvTeam;
    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefresh;
    Unbinder unbinder;

    private TeamPresenter teamPresenter = new TeamPresenter(this);

    public TeamFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_team, container, false);
        unbinder = ButterKnife.bind(this, view);

        teamPresenter.getTeams();

        setUpList();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                teamPresenter.getTeams();
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    private void setUpList() {
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = edtSearch.getText().toString().toLowerCase();
                teamPresenter.getSearchTeams(searchText);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {
        swipeRefresh.setRefreshing(true);
    }

    @Override
    public void hideProgress() {
        swipeRefresh.setRefreshing(false);
    }

    @Override
    public void showTeams(List<TeamItem> teamItems) {
        rvTeam.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvTeam.setAdapter(new TeamAdapter(getActivity(), teamItems));
    }

    @Override
    public void showFailure(String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }
}
