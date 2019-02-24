package project.nathapong.scbchallengeapp.MobileList;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import project.nathapong.scbchallengeapp.MobileList.FavoriteListFragment.FavoriteListFragment;
import project.nathapong.scbchallengeapp.MobileList.MobileListFragment.MobileListFragment;
import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.Network.ConnectionManager;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Constants;
import project.nathapong.scbchallengeapp.Utilities.Public_Method;
import project.nathapong.scbchallengeapp.Utilities.Public_Variables;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter implements ListInterface.ActionPresenter {

    private ListInterface.ActionView actionView;
    private Context context;

    public ListPresenter(ListInterface.ActionView actionView, Context context) {
        this.actionView = actionView;
        this.context = context;
    }

    @Override
    public void getMobileLists() {
        Public_Method.showLoading(context);
        Call call = ConnectionManager.callServices().getMobileList();
        call.enqueue(new Callback<List<MobileListsModel>>() {
            @Override
            public void onResponse(Call<List<MobileListsModel>> call, Response<List<MobileListsModel>> response) {
                Public_Method.hideLoading();
                if (response.isSuccessful() && response.body() != null){
                    actionView.receiveDataFromApi(response.body());
                }else {
                    Public_Method.showErrorDialog(context);
                }
            }
            @Override
            public void onFailure(Call<List<MobileListsModel>> call, Throwable t) {
                Public_Method.hideLoading();
                Public_Method.showErrorDialog(context);
                Log.d("CheckError",t.getMessage());
            }
        });
    }

    @Override
    public void switchFragment(String tabName, List<MobileListsModel> allMobiles) {
        switch (tabName){
            case Constants.TAB_MOBILE:
                if (!Public_Variables.tabName.equals(tabName)) {
                    removeFragment();
                    Fragment fragment = new MobileListFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("ALL_MOBILES", (ArrayList<? extends Parcelable>) allMobiles);
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                    Public_Variables.tabName = tabName;
                }
                break;
            case Constants.TAB_FAVORITE:
                if (!Public_Variables.tabName.equals(tabName)) {
                    removeFragment();
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList("ALL_MOBILES", (ArrayList<? extends Parcelable>) allMobiles);
                    Fragment fragment = new FavoriteListFragment();
                    fragment.setArguments(bundle);
                    replaceFragment(fragment);
                    Public_Variables.tabName = tabName;
                }
                break;
        }
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.flList, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void removeFragment() {
        for (Fragment fragment : ((FragmentActivity) context).getSupportFragmentManager().getFragments()) {
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }
}
