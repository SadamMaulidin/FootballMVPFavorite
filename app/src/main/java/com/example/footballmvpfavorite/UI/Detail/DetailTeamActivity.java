package com.example.footballmvpfavorite.UI.Detail;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.footballmvpfavorite.Model.TeamItem;
import com.example.footballmvpfavorite.R;
import com.example.footballmvpfavorite.UI.Detail.Presenter.DetailTeamContract;
import com.example.footballmvpfavorite.UI.Detail.Presenter.DetailTeamPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailTeamActivity extends AppCompatActivity implements DetailTeamContract.View {

    @BindView(R.id.img_logo_detail)
    ImageView imgLogoDetail;
    @BindView(R.id.txt_name_team_detail)
    TextView txtNameTeamDetail;
    @BindView(R.id.txt_desc)
    TextView txtDesc;
    @BindView(R.id.card_view_detail)
    CardView cardViewDetail;
    @BindView(R.id.sv_detail)
    ScrollView svDetail;

    private Bundle bundle;
    private Menu menu;
    private Boolean isFavorite = false;
    private TeamItem teamItem;
    private DetailTeamPresenter detailTeamPresenter = new DetailTeamPresenter(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_team);
        ButterKnife.bind(this);

        bundle = getIntent().getExtras();
        detailTeamPresenter.getDetailTeam(bundle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.favorite, menu);
        setFavorite();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.favorite:
                if (isFavorite) {
                    detailTeamPresenter.removeFromFavorite(this, teamItem);
                } else {
                    detailTeamPresenter.addToFavorite(this, teamItem);
                }
                isFavorite = detailTeamPresenter.checkFavorite(this, teamItem);
                setFavorite();
                break;

            case android.R.id.home:
                finish();
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
        return true;
    }

    private void setFavorite() {
        if (isFavorite) {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
        } else {
            menu.getItem(0).setIcon(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
        }
    }

    @Override
    public void showDetailTeam(TeamItem teamItem) {
        this.teamItem = teamItem;
        Glide.with(this)
                .load(teamItem.getTeamLogo())
                .into(imgLogoDetail);

        txtNameTeamDetail.setText(teamItem.getTeamName());
        txtDesc.setText(teamItem.getDesc());

        isFavorite = detailTeamPresenter.checkFavorite(this, teamItem);

    }

    @Override
    public void showSucces(String msg) {
        Snackbar.make(svDetail, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showFailure(String msg) {
        Snackbar.make(svDetail, msg, Snackbar.LENGTH_SHORT).show();

    }
}
