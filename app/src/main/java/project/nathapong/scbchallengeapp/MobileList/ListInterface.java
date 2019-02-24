package project.nathapong.scbchallengeapp.MobileList;

import android.support.v4.app.Fragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;

public interface ListInterface {

    interface ActionView{
        void setEvent();
        void receiveDataFromApi(List<MobileListsModel> mobileLists);
    }

    interface ActionPresenter{
        void getMobileLists();
        void switchFragment(String tabName, List<MobileListsModel> allMobiles);
        void replaceFragment(Fragment fragment);
        void removeFragment();
    }
}
