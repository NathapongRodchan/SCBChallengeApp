package project.nathapong.scbchallengeapp.MobileDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.nathapong.scbchallengeapp.MobileDetail.Model.ImageModel;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Constants;

public class MobileDetailActivity extends AppCompatActivity implements MobileDetailInterface.ActionView, View.OnClickListener {

    @BindView(R.id.ivBack) ImageView ivBack;
    @BindView(R.id.sliderLayout) SliderLayout sliderLayout;
    @BindView(R.id.customIndicator) PagerIndicator customIndicator;
    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.tvRating) TextView tvRating;
    @BindView(R.id.tvMobileName) TextView tvMobileName;
    @BindView(R.id.tvDescription) TextView tvDescription;


    private MobileDetailInterface.ActionPresenter actionPresenter;
    private MobileListsModel mobileDetail;

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
        setEvents();
        mobileDetail = getIntent().getParcelableExtra(Constants.MOBILE_DETAIL_KEY);
        if (mobileDetail != null)
            actionPresenter.getImageLists(mobileDetail.getMobileId()+"");

    }

    @Override
    public void setEvents() {
        ivBack.setOnClickListener(this);
    }

    @Override
    public void setMobileDetail(List<ImageModel> allImages) {
        for (int i = 0; i < allImages.size(); i++) {
            DefaultSliderView sliderView = new DefaultSliderView(this);
            sliderView
                    .image(!TextUtils.isEmpty(allImages.get(i).getMobileImageUrl()) ? allImages.get(i).getMobileImageUrl() : "")
                    .empty(R.drawable.place_holder)
                    .setScaleType(BaseSliderView.ScaleType.CenterInside);
            sliderLayout.addSlider(sliderView);
        }
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomIndicator(customIndicator);
        sliderLayout.setCurrentPosition(0);
        sliderLayout.stopAutoCycle();

        tvPrice.setText("" + getText(R.string.price) + mobileDetail.getMobilePrice());
        tvRating.setText("" + getText(R.string.rating) + mobileDetail.getMobileRating());
        tvMobileName.setText(mobileDetail.getMobileName() + " (" + mobileDetail.getMobileBrand() + ")");
        tvDescription.setText(mobileDetail.getMobileDescription());
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
