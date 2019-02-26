package project.nathapong.scbchallengeapp;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.MobileListFragment.MobileListInterface;
import project.nathapong.scbchallengeapp.MobileLists.MobileListFragment.MobileListPresenter;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;

public class MobileListPresenterTest {

    @Mock private MobileListInterface.ActionView actionView;
    @Mock private MobileListPresenter presenter;
    private List<MobileListsModel> allMobiles = new ArrayList<>(), allFavorites = new ArrayList<>();

    @Before
    public void setUp(){
        for (int i = 0; i < 5; i++){
            allMobiles.add(new MobileListsModel("image", "brand","description","Mobile " + i,100.00, i, 3.0, false));
            if ((i % 2) == 0){
                allFavorites.add(new MobileListsModel("image", "brand","description","Mobile " + i,100.00, i, 3.0, true));
            }
        }
        MockitoAnnotations.initMocks(this);
        presenter = new MobileListPresenter(actionView, allMobiles, allFavorites);
    }

    @Test
    public void checkFavoritesTest() {
        List<MobileListsModel> results;

        results = presenter.checkFavorites(allMobiles, allFavorites);
        Assert.assertEquals(true, results.get(0).isFavorite());
        Assert.assertEquals(true, results.get(2).isFavorite());
        Assert.assertEquals(true, results.get(4).isFavorite());

        allFavorites.clear();
        results = presenter.checkFavorites(allMobiles, allFavorites);
        Assert.assertEquals(false, results.get(0).isFavorite());
        Assert.assertEquals(false, results.get(1).isFavorite());
        Assert.assertEquals(false, results.get(2).isFavorite());
        Assert.assertEquals(false, results.get(3).isFavorite());
        Assert.assertEquals(false, results.get(4).isFavorite());
    }

}
