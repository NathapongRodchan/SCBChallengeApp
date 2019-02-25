package project.nathapong.scbchallengeapp.MobileLists;

import android.support.v4.app.Fragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;

public interface ListInterface {

    interface ActionView{
        void setEvent();
        void receiveDataFromApi(List<MobileListsModel> mobileLists);
        void callSortData(String option);
    }

    interface ActionPresenter{
        Fragment getCurrentFragment();
        void getMobileLists();
        void showMobileLists(List<MobileListsModel> allMobiles);
        void showFavoriteLists();
        void replaceFragment(Fragment fragment);
        void removeFragment();
        void showOptions();
        void sortDataLowToHigh(List<MobileListsModel> allMobiles);
        void sortDataHighToLow(List<MobileListsModel> allMobiles);
        void sortDataByRating(List<MobileListsModel> allMobiles);
    }
}
