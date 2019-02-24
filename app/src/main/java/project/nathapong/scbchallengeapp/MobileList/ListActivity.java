package project.nathapong.scbchallengeapp.MobileList;

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
import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;
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
        allMobiles = mobileLists;
        setEvent();
        tvMobileList.performClick();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivOptions:
                break;
            case R.id.tvMobileList:
                actionPresenter.switchFragment(Constants.TAB_MOBILE, allMobiles);
                tvMobileList.setTextColor(ContextCompat.getColor(this,R.color.text_white));
                tvFavorites.setTextColor(ContextCompat.getColor(this,R.color.text_white_dark));
                break;
            case R.id.tvFavorites:
                actionPresenter.switchFragment(Constants.TAB_FAVORITE, allMobiles);
                tvFavorites.setTextColor(ContextCompat.getColor(this,R.color.text_white));
                tvMobileList.setTextColor(ContextCompat.getColor(this,R.color.text_white_dark));
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Public_Variables.tabName = "";
    }
}
