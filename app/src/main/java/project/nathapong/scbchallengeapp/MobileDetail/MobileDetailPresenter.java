package project.nathapong.scbchallengeapp.MobileDetail;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import project.nathapong.scbchallengeapp.MobileDetail.Model.ImageModel;
import project.nathapong.scbchallengeapp.MobileDetail.Model.ResponseMessageModel;
import project.nathapong.scbchallengeapp.Network.ConnectionManager;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Public_Method;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MobileDetailPresenter implements MobileDetailInterface.ActionPresenter{

    private MobileDetailInterface.ActionView actionView;
    private Context context;

    public MobileDetailPresenter(MobileDetailInterface.ActionView actionView, Context context) {
        this.actionView = actionView;
        this.context = context;
    }

    @Override
    public void getImageLists(String mobileId) {
        Public_Method.showLoading(context);
        Call call = ConnectionManager.callServices().getImages(mobileId);
        call.enqueue(new Callback<List<ImageModel>>() {
            @Override
            public void onResponse(Call<List<ImageModel>> call, Response<List<ImageModel>> response) {
                Public_Method.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    actionView.setMobileDetail(response.body());
                } else {
                    if (response.code() == 404 && response.errorBody() != null){
                        Gson gson = new Gson();
                        ResponseMessageModel errorMessage = null;
                        try {
                            errorMessage = gson.fromJson(response.errorBody().string(), ResponseMessageModel.class);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Public_Method.showErrorDialog(context, errorMessage.getReason());
                    }else {
                        Public_Method.showErrorDialog(context, (String)context.getText(R.string.connection_error));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<ImageModel>> call, Throwable t) {
                Public_Method.hideLoading();
                Public_Method.showErrorDialog(context, (String)context.getText(R.string.connection_error));
                Log.d("CheckError", t.getMessage());
            }
        });
    }
}
