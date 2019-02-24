package project.nathapong.scbchallengeapp.MobileList.MobileListFragment;

import android.content.Context;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.Utilities.Sessions;

public class MobileListPresenter implements MobileListInterface.ActionPresenter{

    private MobileListInterface.ActionView actionView;
    private Context context;

    public MobileListPresenter(MobileListInterface.ActionView actionView, Context context) {
        this.actionView = actionView;
        this.context = context;
    }

    @Override
    public void checkFavorites(List<MobileListsModel> allMobiles) {
        List<Integer> allFavoriteID = Sessions.readFavoriteLists();
        for (int i = 0; i < allFavoriteID.size(); i++){
            for (int x = 0; x < allMobiles.size(); x++){
                if (allFavoriteID.get(i) == allMobiles.get(x).getId()){
                    allMobiles.get(x).setFavorite(true);
                    break;
                }
            }
        }
    }
}
