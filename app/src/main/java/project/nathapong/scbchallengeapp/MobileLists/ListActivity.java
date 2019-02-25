package project.nathapong.scbchallengeapp.MobileLists;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.paperdb.Paper;
import project.nathapong.scbchallengeapp.MobileLists.FavoriteListFragment.FavoriteListFragment;
import project.nathapong.scbchallengeapp.MobileLists.MobileListFragment.MobileListFragment;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Constants;
import project.nathapong.scbchallengeapp.Utilities.Public_Variables;

public class ListActivity extends AppCompatActivity implements ListInterface.ActionView, View.OnClickListener {

    @BindView(R.id.ivOptions) ImageView ivOptions;
    @BindView(R.id.tvMobileList) TextView tvMobileList;
    @BindView(R.id.tvFavorites) TextView tvFavorites;
    @BindView(R.id.flList) FrameLayout flList;

    private ListInterface.ActionPresenter actionPresenter;
    private List<MobileListsModel> allMobiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);
        Paper.init(this);
        actionPresenter = new ListPresenter(this,this);
        actionPresenter.getMobileLists();
    }

    @Override
    public void setEvent() {
        ivOptions.setOnClickListener(this);
        tvMobileList.setOnClickListener(this);
        tvFavorites.setOnClickListener(this);
    }

    @Override
    public void receiveDataFromApi(List<MobileListsModel> mobileLists) {
        if (mobileLists != null && mobileLists.size() > 0){
            allMobiles = mobileLists;
            setEvent();
            actionPresenter.showMobileLists(allMobiles);
        }
    }

    @Override
    public void callSortData(String option) {
        switch (option){
            case Constants.LOW_TO_HIGH:
                actionPresenter.sortDataLowToHigh(allMobiles);
                break;
            case Constants.HIGH_TO_LOW:
                actionPresenter.sortDataHighToLow(allMobiles);
                break;
            case Constants.RATING:
                actionPresenter.sortDataByRating(allMobiles);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivOptions:
                actionPresenter.showOptions();
                break;
            case R.id.tvMobileList:
                if (!(actionPresenter.getCurrentFragment() instanceof MobileListFragment)){
                    actionPresenter.showMobileLists(allMobiles);
                    tvMobileList.setTextColor(ContextCompat.getColor(this,R.color.text_white));
                    tvFavorites.setTextColor(ContextCompat.getColor(this,R.color.text_white_dark));
                }
                break;
            case R.id.tvFavorites:
                if (!(actionPresenter.getCurrentFragment() instanceof FavoriteListFragment)){
                    actionPresenter.showFavoriteLists();
                    tvFavorites.setTextColor(ContextCompat.getColor(this,R.color.text_white));
                    tvMobileList.setTextColor(ContextCompat.getColor(this,R.color.text_white_dark));
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Public_Variables.optionName = "";
    }
}
