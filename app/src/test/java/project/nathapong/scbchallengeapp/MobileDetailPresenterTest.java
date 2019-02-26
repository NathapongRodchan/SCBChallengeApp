package project.nathapong.scbchallengeapp;

import com.google.gson.Gson;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import project.nathapong.scbchallengeapp.MobileDetail.Model.ImageModel;
import project.nathapong.scbchallengeapp.MobileDetail.Model.ResponseMessageModel;
import project.nathapong.scbchallengeapp.Network.API_Services;
import retrofit2.Response;

public class MobileDetailPresenterTest {

    private MockWebServer server;
    private API_Services services;

    @Before
    public void setUp(){
        server = new MockWebServer();
        services = RestServiceTestHelper.callServices(server);

    }

    @Test
    public void GetImageListsTest() throws Exception {
        //Case success
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile("image_lists.json")));

        Response<List<ImageModel>> response = services.getImages("1").execute();

        Assert.assertNotNull(response.body());
        Assert.assertNull(response.errorBody());
        Assert.assertEquals(200, response.code());

        //Case error
        server.enqueue(new MockResponse()
                .setResponseCode(404)
                .setBody(RestServiceTestHelper.getStringFromFile("error_message.json")));

        Response<List<ImageModel>> response2 = services.getImages("999").execute();
        Gson gson = new Gson();
        ResponseMessageModel errorMessage = gson.fromJson(response2.errorBody().string(), ResponseMessageModel.class);

        Assert.assertNull(response2.body());
        Assert.assertNotNull(response2.errorBody());
        Assert.assertEquals(404, response2.code());
        Assert.assertEquals("No Mobile with that identifier was found.", errorMessage.getErrorMessage());
        Assert.assertEquals(true, errorMessage.isHasError());
    }

    @After
    public void shutDown() throws IOException {
        server.shutdown();
        server = null;
        services = null;
    }
}
