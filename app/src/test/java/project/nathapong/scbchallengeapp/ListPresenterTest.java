package project.nathapong.scbchallengeapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import project.nathapong.scbchallengeapp.MobileLists.ListActivity;
import project.nathapong.scbchallengeapp.MobileLists.ListInterface;
import project.nathapong.scbchallengeapp.MobileLists.ListPresenter;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.Network.API_Services;
import project.nathapong.scbchallengeapp.Utilities.Constants;
import retrofit2.Response;

public class ListPresenterTest {

    @Mock private ListInterface.ActionView actionView;
    @Mock private ListPresenter presenter;
    @Mock private ListActivity context;
    private List<MobileListsModel> allMobiles = new ArrayList<>();
    private MockWebServer server;
    private API_Services services;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        presenter = new ListPresenter(actionView, context);
        server = new MockWebServer();
        services = RestServiceTestHelper.callServices(server);
        allMobiles.add(new MobileListsModel("image", "brand","description","Mobile 1",100.00, 1, 3.0, false));
        allMobiles.add(new MobileListsModel("image", "brand","description","Mobile 2",200.00, 2, 5.0, false));
        allMobiles.add(new MobileListsModel("image", "brand","description","Mobile 3",300.00, 3, 4.0, false));
    }

    @Test
    public void GetMobileListTest() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile("mobile_lists.json")));

        Response<List<MobileListsModel>> response = services.getMobileList().execute();

        Assert.assertNotNull(response.body());
        Assert.assertNull(response.errorBody());
        Assert.assertEquals(200, response.code());
    }

    @After
    public void shutDown() throws IOException {
        server.shutdown();
        server = null;
        services = null;
    }

    @Test
    public void sortDataTest() {
        List<MobileListsModel> results;

        results = presenter.sortData(allMobiles, Constants.LOW_TO_HIGH);
        Assert.assertEquals("Mobile 1", results.get(0).getMobileName());
        Assert.assertEquals("Mobile 2", results.get(1).getMobileName());
        Assert.assertEquals("Mobile 3", results.get(2).getMobileName());

        results = presenter.sortData(allMobiles, Constants.HIGH_TO_LOW);
        Assert.assertEquals("Mobile 3", results.get(0).getMobileName());
        Assert.assertEquals("Mobile 2", results.get(1).getMobileName());
        Assert.assertEquals("Mobile 1", results.get(2).getMobileName());

        results = presenter.sortData(allMobiles, Constants.RATING);
        Assert.assertEquals("Mobile 2", results.get(0).getMobileName());
        Assert.assertEquals("Mobile 3", results.get(1).getMobileName());
        Assert.assertEquals("Mobile 1", results.get(2).getMobileName());

        results = presenter.sortData(allMobiles, "other");
        Assert.assertEquals("Mobile 1", results.get(0).getMobileName());
        Assert.assertEquals("Mobile 2", results.get(1).getMobileName());
        Assert.assertEquals("Mobile 3", results.get(2).getMobileName());

        allMobiles.clear();
        results = presenter.sortData(allMobiles, "any");
        Assert.assertEquals(new ArrayList<>(), results);
        Assert.assertEquals(0, results.size());
    }
}
