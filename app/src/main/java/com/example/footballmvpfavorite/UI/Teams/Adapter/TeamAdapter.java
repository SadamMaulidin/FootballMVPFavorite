package com.example.footballmvpfavorite.UI.Teams.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.footballmvpfavorite.Model.TeamItem;
import com.example.footballmvpfavorite.R;
import com.example.footballmvpfavorite.UI.Detail.DetailTeamActivity;
import com.example.footballmvpfavorite.Utills.Constants;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.ViewHolder> {

    private Context context;
    private List<TeamItem> teamItemList;

    public TeamAdapter(Context context, List<TeamItem> teamItemList) {
        this.context = context;
        this.teamItemList = teamItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_teams, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final TeamItem teamItem = teamItemList.get(i);

        viewHolder.txtNameTeam.setText(teamItem.getTeamName());

        Glide.with(context)
                .load(teamItem.getTeamLogo())
                .into(viewHolder.imgLogoTeam);

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, DetailTeamActivity.class).putExtra(Constants.KEY_DATA, teamItem));
            }
        });

    }

    @Override
    public int getItemCount() {
        return teamItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.img_logo_team)
        ImageView imgLogoTeam;
        @BindView(R.id.txt_name_team)
        TextView txtNameTeam;
        @BindView(R.id.card_view)
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}