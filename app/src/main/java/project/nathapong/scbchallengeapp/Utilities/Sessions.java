package project.nathapong.scbchallengeapp.Utilities;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;

public class Sessions {

    // All Favorites
    public static void saveFavoriteLists(List<MobileListsModel> allFavoriteLists){
        Paper.book().write(Constants.FAVORITE_KEY, allFavoriteLists);
    }
    public static List<MobileListsModel> readFavoriteLists(){
        return Paper.book().read(Constants.FAVORITE_KEY, new ArrayList<MobileListsModel>());
    }

}
