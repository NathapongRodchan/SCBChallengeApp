package project.nathapong.scbchallengeapp.MobileLists.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Sessions;

public class MobileListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MobileListsModel> allMobiles;
    private AdapterListener adapterListener;
    private Context context;

    public MobileListAdapter(List<MobileListsModel> allMobiles, AdapterListener adapterListener) {
        this.allMobiles = allMobiles;
        this.adapterListener = adapterListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.mobile_lists_adapter, viewGroup, false);
        return new MobileListHolder(view, adapterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MobileListHolder mobileListsHolder = (MobileListHolder)viewHolder;
        bindView(mobileListsHolder);
    }

    @Override
    public int getItemCount() {
        return allMobiles.size();
    }

    private void bindView(MobileListHolder mobileListsHolder){
        mobileListsHolder.tvName.setText(allMobiles.get(mobileListsHolder.getAdapterPosition()).getName());
        mobileListsHolder.tvDescription.setText(allMobiles.get(mobileListsHolder.getAdapterPosition()).getDescription());
        mobileListsHolder.tvPrice.setText("" + context.getText(R.string.price) + allMobiles.get(mobileListsHolder.getAdapterPosition()).getPrice());
        mobileListsHolder.tvRating.setText("" + context.getText(R.string.rating) + allMobiles.get(mobileListsHolder.getAdapterPosition()).getRating());
        mobileListsHolder.ivFavorite.setImageResource(allMobiles.get(mobileListsHolder.getAdapterPosition()).isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_no_favorite);
        Glide.with(context).load(allMobiles.get(mobileListsHolder.getAdapterPosition()).getThumbImageURL()).into(mobileListsHolder.ivImage);
    }

    public void addFavorites(int position){
        List<MobileListsModel> allFavorites = Sessions.readFavoriteLists();
        allFavorites.add(allMobiles.get(position));
        allFavorites.get(allFavorites.size()-1).setFavorite(true);
        Collections.sort(allFavorites, new Comparator<MobileListsModel>() {
            @Override
            public int compare(MobileListsModel list1, MobileListsModel list2) {
                return Integer.compare(list1.getId(),list2.getId());
            }
        });
        Sessions.saveFavoriteLists(allFavorites);
        allMobiles.get(position).setFavorite(true);
        notifyItemChanged(position);
    }

    public void removeFavorites(int position){
        List<MobileListsModel> allFavorites = Sessions.readFavoriteLists();
        for (int i = 0; i < allFavorites.size(); i++){
            if (allFavorites.get(i).getId() == allMobiles.get(position).getId()){
                allFavorites.remove(i);
                break;
            }
        }
        Sessions.saveFavoriteLists(allFavorites);
        allMobiles.get(position).setFavorite(false);
        notifyItemChanged(position);
    }

    public void deleteFavorites(int position){
        List<MobileListsModel> allFavorites = Sessions.readFavoriteLists();
        for (int i = 0; i < allFavorites.size(); i++){
            if (allFavorites.get(i).getId() == allMobiles.get(position).getId()){
                allFavorites.remove(i);
                break;
            }
        }
        Sessions.saveFavoriteLists(allFavorites);
        allMobiles.remove(position);
        notifyItemRemoved(position);
    }
}