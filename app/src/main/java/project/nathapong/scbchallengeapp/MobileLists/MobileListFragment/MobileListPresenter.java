package project.nathapong.scbchallengeapp.MobileLists.MobileListFragment;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;

public class MobileListPresenter implements MobileListInterface.ActionPresenter{

    private MobileListInterface.ActionView actionView;

    public MobileListPresenter(MobileListInterface.ActionView actionView, List<MobileListsModel> allMobiles, List<MobileListsModel> allFavorites) {
        this.actionView = actionView;
        this.actionView.setMobileLists(checkFavorites(allMobiles, allFavorites));
    }

    @Override
    public List<MobileListsModel> checkFavorites(List<MobileListsModel> allMobiles, List<MobileListsModel> allFavorites) {
        for (int i = 0; i < allMobiles.size(); i++){
            allMobiles.get(i).setFavorite(false);
            for (MobileListsModel list : allFavorites){
                if (allMobiles.get(i).getMobileId() == list.getMobileId()){
                    allMobiles.get(i).setFavorite(true);
                    break;
                }
            }
        }
        return allMobiles;
    }
}
