package project.nathapong.scbchallengeapp.MobileLists.FavoriteListFragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.Utilities.Sessions;

public class FavoriteListPresenter implements FavoriteListInterface.ActionPresenter{

    private FavoriteListInterface.ActionView actionView;

    public FavoriteListPresenter(FavoriteListInterface.ActionView actionView) {
        this.actionView = actionView;
        getFavoriteLists();
    }

    @Override
    public void getFavoriteLists() {
        List<MobileListsModel> allFavorites = Sessions.readFavoriteLists();
        if (allFavorites != null && allFavorites.size() > 0){
            actionView.setFavoriteLists(allFavorites);
        }
    }
}
