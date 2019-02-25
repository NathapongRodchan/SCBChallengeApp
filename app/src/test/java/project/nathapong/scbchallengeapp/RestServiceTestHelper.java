package project.nathapong.scbchallengeapp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import okhttp3.mockwebserver.MockWebServer;
import project.nathapong.scbchallengeapp.Network.API_Services;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RestServiceTestHelper {

    public static API_Services callServices(MockWebServer server){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(server.url("").toString())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API_Services services = retrofit.create(API_Services.class);
        return services;
    }

    private static String convertStreamToString(InputStream is) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }
        reader.close();
        return sb.toString();
    }

    public static String getStringFromFile(String filePath) throws Exception {
        String fileName = System.getProperty("user.dir") + "/src/test/data/";
        final InputStream stream = new FileInputStream(fileName + filePath);
        String ret = convertStreamToString(stream);
        stream.close();
        return ret;
    }
}
