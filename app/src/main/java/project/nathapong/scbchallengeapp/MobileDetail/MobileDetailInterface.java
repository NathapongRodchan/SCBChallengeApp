package project.nathapong.scbchallengeapp.MobileDetail;

public interface MobileDetailInterface {

    interface ActionView{
        void initView();
        void setEvents();
        void setMobileDetail();
    }

    interface ActionPresenter{
        void getImageLists(String mobileID);
    }
}
