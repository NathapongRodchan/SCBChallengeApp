package project.nathapong.scbchallengeapp.MobileList.MobileListFragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;

public interface MobileListInterface {

    interface ActionView{
        void initView();
        void setMobileLists();
    }

    interface ActionPresenter{
        void checkFavorites(List<MobileListsModel> allMobiles);
    }
}
