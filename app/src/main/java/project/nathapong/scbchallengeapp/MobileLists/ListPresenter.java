package project.nathapong.scbchallengeapp.MobileLists;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.FavoriteListFragment.FavoriteListFragment;
import project.nathapong.scbchallengeapp.MobileLists.MobileListFragment.MobileListFragment;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.Network.ConnectionManager;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Constants;
import project.nathapong.scbchallengeapp.Utilities.Public_Method;
import project.nathapong.scbchallengeapp.Utilities.Public_Variables;
import project.nathapong.scbchallengeapp.Utilities.Sessions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListPresenter implements ListInterface.ActionPresenter {

    private ListInterface.ActionView actionView;
    private Context context;
    private List<MobileListsModel> allMobiles;

    public ListPresenter(ListInterface.ActionView actionView, Context context) {
        this.actionView = actionView;
        this.context = context;
    }

    @Override
    public void checkCurrentTab(String tabName) {
        if (!(getCurrentFragment() instanceof MobileListFragment) && tabName.equals(Constants.TAB_MOBILE)){
            showMobileLists(allMobiles);
        }
        else if (!(getCurrentFragment() instanceof FavoriteListFragment) && tabName.equals(Constants.TAB_FAVORITE)){
            showFavoriteLists();
        }
    }

    @Override
    public Fragment getCurrentFragment() {
        Fragment fragment = ((FragmentActivity)context).getSupportFragmentManager().findFragmentById(R.id.flList);
        return fragment;
    }

    @Override
    public void getMobileLists() {
        Public_Method.showLoading(context);
        Call call = ConnectionManager.callServices().getMobileList();
        call.enqueue(new Callback<List<MobileListsModel>>() {
            @Override
            public void onResponse(Call<List<MobileListsModel>> call, Response<List<MobileListsModel>> response) {
                Public_Method.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    allMobiles = response.body();
                    showMobileLists(allMobiles);
                    actionView.setEvent();
                } else {
                    Public_Method.showErrorDialog(context, (String)context.getText(R.string.connection_error));
                }
            }

            @Override
            public void onFailure(Call<List<MobileListsModel>> call, Throwable t) {
                Public_Method.hideLoading();
                Public_Method.showErrorDialog(context, (String)context.getText(R.string.connection_error));
                Log.d("CheckError", t.getMessage());
            }
        });
    }

    @Override
    public void showMobileLists(List<MobileListsModel> allMobiles) {
        actionView.setHighlight(Constants.TAB_MOBILE);
        removeFragment();
        Fragment fragment = new MobileListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList(Constants.MOBILE_KEY, (ArrayList<? extends Parcelable>) allMobiles);
        fragment.setArguments(bundle);
        replaceFragment(fragment);
    }

    @Override
    public void showFavoriteLists() {
        actionView.setHighlight(Constants.TAB_FAVORITE);
        removeFragment();
        replaceFragment(new FavoriteListFragment());
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = ((FragmentActivity) context).getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flList, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void removeFragment() {
        for (Fragment fragment : ((FragmentActivity) context).getSupportFragmentManager().getFragments()) {
            ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    public void showOptions() {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.options_dialog_layout, null);
        final RadioGroup rgOptions = (RadioGroup) view.findViewById(R.id.rgOptions);
        final RadioButton rbLowToHigh = (RadioButton) view.findViewById(R.id.rbLowToHigh);
        final RadioButton rbHighToLow = (RadioButton) view.findViewById(R.id.rbHighToLow);
        final RadioButton rbRating = (RadioButton) view.findViewById(R.id.rbRating);

        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        final AlertDialog optionsDialog = builder.show();

        if (!TextUtils.isEmpty(Public_Variables.optionName)){
            switch (Public_Variables.optionName){
                case Constants.LOW_TO_HIGH:
                    rbLowToHigh.setChecked(true);
                    break;
                case Constants.HIGH_TO_LOW:
                    rbHighToLow.setChecked(true);
                    break;
                case Constants.RATING:
                    rbRating.setChecked(true);
                    break;
            }
        }

        rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                switch (checkedId){
                    case R.id.rbLowToHigh:
                        allMobiles = sortData(allMobiles,Constants.LOW_TO_HIGH);
                        sortDataFavorites(Constants.LOW_TO_HIGH);
                        Public_Variables.optionName = Constants.LOW_TO_HIGH;
                        break;
                    case R.id.rbHighToLow:
                        allMobiles = sortData(allMobiles,Constants.HIGH_TO_LOW);
                        sortDataFavorites(Constants.HIGH_TO_LOW);
                        Public_Variables.optionName = Constants.HIGH_TO_LOW;
                        break;
                    case R.id.rbRating:
                        allMobiles = sortData(allMobiles,Constants.RATING);
                        sortDataFavorites(Constants.RATING);
                        Public_Variables.optionName = Constants.RATING;
                        break;
                }
                refreshTabAfterOptionIsSelected();
                optionsDialog.dismiss();
            }
        });
    }

    @Override
    public List<MobileListsModel> sortData(List<MobileListsModel> allMobiles, final String options) {
        if (allMobiles != null && allMobiles.size() > 0){
            Collections.sort(allMobiles, new Comparator<MobileListsModel>() {
                @Override
                public int compare(MobileListsModel list1, MobileListsModel list2) {
                    switch (options){
                        case Constants.LOW_TO_HIGH:
                            return Double.compare(list1.getMobilePrice(),list2.getMobilePrice());
                        case Constants.HIGH_TO_LOW:
                            return Double.compare(list2.getMobilePrice(),list1.getMobilePrice());
                        case Constants.RATING:
                            return Double.compare(list2.getMobileRating(),list1.getMobileRating());
                        default:
                            return Double.compare(list1.getMobileId(),list2.getMobileId());
                    }
                }
            });
        }
        return allMobiles;
    }

    @Override
    public void sortDataFavorites(final String options) {
        List<MobileListsModel> allFavorites = sortData(Sessions.readFavoriteLists(), options);
        Sessions.saveFavoriteLists(allFavorites);
    }

    @Override
    public void refreshTabAfterOptionIsSelected() {
        if ((getCurrentFragment() instanceof MobileListFragment))
            showMobileLists(allMobiles);
        else if ((getCurrentFragment() instanceof FavoriteListFragment))
            showFavoriteLists();
    }
}
