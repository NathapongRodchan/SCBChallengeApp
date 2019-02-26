package project.nathapong.scbchallengeapp.MobileLists.MobileListFragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;

public interface MobileListInterface {

    interface ActionView{
        void initView();
        void setMobileLists(List<MobileListsModel> allMobiles);
    }

    interface ActionPresenter{
        List<MobileListsModel> checkFavorites(List<MobileListsModel> allMobiles, List<MobileListsModel> allFavorites);
    }
}
