package project.nathapong.scbchallengeapp.MobileDetail;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileDetail.Model.ImageModel;

public interface MobileDetailInterface {

    interface ActionView{
        void initView();
        void setEvents();
        void setMobileDetail(List<ImageModel> allImages);
    }

    interface ActionPresenter{
        void getImageLists(String mobileID);
    }
}
