package project.nathapong.scbchallengeapp.MobileLists.MobileListFragment;

import android.content.Context;
import android.util.Log;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.Utilities.Sessions;

public class MobileListPresenter implements MobileListInterface.ActionPresenter{

    private MobileListInterface.ActionView actionView;

    public MobileListPresenter(MobileListInterface.ActionView actionView) {
        this.actionView = actionView;
    }

    @Override
    public void checkFavorites(List<MobileListsModel> allMobiles) {
        List<MobileListsModel> allFavorites = Sessions.readFavoriteLists();
        for (int i = 0; i < allMobiles.size(); i++){
            allMobiles.get(i).setFavorite(false);
            for (MobileListsModel list : allFavorites){
                if (allMobiles.get(i).getMobileId() == list.getMobileId()){
                    allMobiles.get(i).setFavorite(true);
                    break;
                }
            }
        }
    }

}
