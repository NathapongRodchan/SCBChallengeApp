package project.nathapong.scbchallengeapp.Network;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;
import retrofit2.Call;
import retrofit2.http.GET;

public interface API_Services {

    @GET("mobiles/")
    Call<List<MobileListsModel>> getMobileList();


}
