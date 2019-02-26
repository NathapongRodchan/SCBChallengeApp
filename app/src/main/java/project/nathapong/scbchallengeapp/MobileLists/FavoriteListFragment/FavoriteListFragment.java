package project.nathapong.scbchallengeapp.MobileLists.FavoriteListFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import project.nathapong.scbchallengeapp.MobileLists.Adapter.AdapterListener;
import project.nathapong.scbchallengeapp.MobileLists.Adapter.MobileListAdapter;
import project.nathapong.scbchallengeapp.MobileLists.Adapter.SwipeToDeleteCallBack;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.R;

public class FavoriteListFragment extends Fragment implements FavoriteListInterface.ActionView {

    @BindView(R.id.rvFavoriteLists) RecyclerView rvFavoriteLists;
    Unbinder unbinder;
    private FavoriteListInterface.ActionPresenter actionPresenter;

    private MobileListAdapter mobileListAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    @Override
    public void initView() {
        actionPresenter = new FavoriteListPresenter(this);
    }

    @Override
    public void setFavoriteLists(List<MobileListsModel> allFavorites) {
        AdapterListener adapterListener = new AdapterListener() {
            @Override
            public void onFavoriteClick(int position) {
                mobileListAdapter.deleteFavorites(position);
            }
        };
        mobileListAdapter = new MobileListAdapter(allFavorites, adapterListener);
        rvFavoriteLists.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvFavoriteLists.setAdapter(mobileListAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDeleteCallBack(mobileListAdapter, getActivity()));
        itemTouchHelper.attachToRecyclerView(rvFavoriteLists);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
