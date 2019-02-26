package project.nathapong.scbchallengeapp.MobileLists;

import android.support.v4.app.Fragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;

public interface ListInterface {

    interface ActionView{
        void setEvent();
        void setHighlight(String tabName);
    }

    interface ActionPresenter{
        void checkCurrentTab(String tabName);
        Fragment getCurrentFragment();
        void getMobileLists();
        void showMobileLists(List<MobileListsModel> allMobiles);
        void showFavoriteLists();
        void replaceFragment(Fragment fragment);
        void removeFragment();
        void showOptions();
        List<MobileListsModel> sortData(List<MobileListsModel> allMobiles, String options);
        void sortDataFavorites(String options);
        void refreshTabAfterOptionIsSelected();
    }
}
