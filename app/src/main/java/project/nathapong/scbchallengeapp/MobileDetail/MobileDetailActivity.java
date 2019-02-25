package project.nathapong.scbchallengeapp.MobileDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.nathapong.scbchallengeapp.R;

public class MobileDetailActivity extends AppCompatActivity implements MobileDetailInterface.ActionView, View.OnClickListener {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.sliderLayout) SliderLayout sliderLayout;
    @BindView(R.id.customIndicator) PagerIndicator customIndicator;
    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.tvRating) TextView tvRating;
    @BindView(R.id.tvDescription) TextView tvDescription;

    private MobileDetailInterface.ActionPresenter actionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_detail);
        ButterKnife.bind(this);
        actionPresenter = new MobileDetailPresenter(this,this);
        initView();
    }

    @Override
    public void initView() {

    }

    @Override
    public void setEvents() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void setMobileDetail() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivBack:
                onBackPressed();
                break;
        }
    }
}
