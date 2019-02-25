package project.nathapong.scbchallengeapp.MobileDetail;

import android.content.Context;

public class MobileDetailPresenter implements MobileDetailInterface.ActionPresenter{

    private MobileDetailInterface.ActionView actionView;
    private Context context;

    public MobileDetailPresenter(MobileDetailInterface.ActionView actionView, Context context) {
        this.actionView = actionView;
        this.context = context;
    }

    @Override
    public void getImageLists(String mobileID) {

    }
}
