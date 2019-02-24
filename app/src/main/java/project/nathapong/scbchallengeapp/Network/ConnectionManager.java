package project.nathapong.scbchallengeapp.Network;

import project.nathapong.scbchallengeapp.Utilities.Constants;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectionManager {

    public static API_Services callServices(){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API_Services api_services = retrofit.create(API_Services.class);
        return api_services;
    }

}
