package project.nathapong.scbchallengeapp.Network;

import java.util.List;

import project.nathapong.scbchallengeapp.MobileDetail.Model.ImageModel;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API_Services {

    @GET("mobiles/")
    Call<List<MobileListsModel>> getMobileList();

    @GET("mobiles/{mobileId}/images/")
    Call<List<ImageModel>> getImages(@Path("mobileId") String mobileId);


}
