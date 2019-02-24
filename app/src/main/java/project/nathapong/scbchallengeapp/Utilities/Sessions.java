package project.nathapong.scbchallengeapp.Utilities;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;

public class Sessions {

    // All Favorite ID
    public static void saveFavoriteLists(List<Integer> allFavoriteLists){
        Paper.book().write("FAVORITE_LISTS", allFavoriteLists);
    }
    public static List<Integer> readFavoriteLists(){
        return Paper.book().read("FAVORITE_LISTS", new ArrayList<Integer>());
    }
}
