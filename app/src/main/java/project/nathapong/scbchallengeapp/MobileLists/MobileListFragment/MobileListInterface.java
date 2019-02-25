package project.nathapong.scbchallengeapp.MobileLists.MobileListFragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;

public interface MobileListInterface {

    interface ActionView{
        void initView();
        void setMobileLists();
    }

    interface ActionPresenter{
        void checkFavorites(List<MobileListsModel> allMobiles);
    }
}
