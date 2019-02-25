package project.nathapong.scbchallengeapp;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.Network.API_Services;
import retrofit2.Response;

public class ListPresenterTest {

    private MockWebServer server;
    private API_Services services;

    @Before
    public void setUp(){
        server = new MockWebServer();
        services = RestServiceTestHelper.callServices(server);
    }

    @Test
    public void testGetMobileList() throws Exception {
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile("mobile_lists.json")));

        Response<List<MobileListsModel>> response = services.getMobileList().execute();

        Assert.assertNotNull(response.body());
        Assert.assertEquals(200, response.code());
    }

    @After
    public void shutDown() throws IOException {
        server.shutdown();
        server = null;
        services = null;
    }
}
