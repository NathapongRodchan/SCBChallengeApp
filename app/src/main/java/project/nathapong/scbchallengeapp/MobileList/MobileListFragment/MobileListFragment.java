package project.nathapong.scbchallengeapp.MobileList.MobileListFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.nathapong.scbchallengeapp.MobileList.MobileListFragment.Adapter.AdapterListener;
import project.nathapong.scbchallengeapp.MobileList.MobileListFragment.Adapter.MobileListAdapter;
import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Sessions;

public class MobileListFragment extends Fragment implements MobileListInterface.ActionView {

    @BindView(R.id.rvMobileLists) RecyclerView rvMobileLists;
    Unbinder unbinder;
    private MobileListInterface.ActionPresenter actionPresenter;
    private List<MobileListsModel> allMobiles;
    private MobileListAdapter mobileListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mobile_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        actionPresenter = new MobileListPresenter(this, getActivity());
        allMobiles = getArguments().getParcelableArrayList("ALL_MOBILES");
        actionPresenter.checkFavorites(allMobiles);
        setMobileLists();
    }

    @Override
    public void setMobileLists() {
        AdapterListener adapterListener = new AdapterListener() {
            @Override
            public void onFavoriteClick(int position, int id, boolean isFavorite) {
                if (!isFavorite){
                    allMobiles.get(position).setFavorite(true);
                    mobileListAdapter.notifyDataSetChanged();
                }else {
                    allMobiles.get(position).setFavorite(false);
                    mobileListAdapter.notifyDataSetChanged();
                }
            }
        };
        mobileListAdapter = new MobileListAdapter(allMobiles, adapterListener);
        rvMobileLists.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvMobileLists.setAdapter(mobileListAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
