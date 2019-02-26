package project.nathapong.scbchallengeapp.MobileLists.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Constants;
import project.nathapong.scbchallengeapp.Utilities.Public_Variables;
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
        return new MobileListHolder(view, adapterListener, allMobiles, context);
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
        mobileListsHolder.tvName.setText(allMobiles.get(mobileListsHolder.getAdapterPosition()).getMobileName());
        mobileListsHolder.tvDescription.setText(allMobiles.get(mobileListsHolder.getAdapterPosition()).getMobileDescription());
        mobileListsHolder.tvPrice.setText("" + context.getText(R.string.price) + allMobiles.get(mobileListsHolder.getAdapterPosition()).getMobilePrice());
        mobileListsHolder.tvRating.setText("" + context.getText(R.string.rating) + allMobiles.get(mobileListsHolder.getAdapterPosition()).getMobileRating());
        mobileListsHolder.ivFavorite.setImageResource(allMobiles.get(mobileListsHolder.getAdapterPosition()).isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_no_favorite);
        Glide.with(context).load(allMobiles.get(mobileListsHolder.getAdapterPosition()).getMobileImageUrl()).into(mobileListsHolder.ivImage);
    }

    public void addFavorites(int position){
        List<MobileListsModel> allFavorites = Sessions.readFavoriteLists();
        allFavorites.add(allMobiles.get(position));
        allFavorites.get(allFavorites.size()-1).setFavorite(true);

        //Sort by options
        if (TextUtils.isEmpty(Public_Variables.optionName)) {
            Collections.sort(allFavorites, new Comparator<MobileListsModel>() {
                @Override
                public int compare(MobileListsModel list1, MobileListsModel list2) {
                    return Integer.compare(list1.getMobileId(),list2.getMobileId());
                }
            });
        }else {
            Collections.sort(allFavorites, new Comparator<MobileListsModel>() {
                @Override
                public int compare(MobileListsModel list1, MobileListsModel list2) {
                    if (Public_Variables.optionName.equals(Constants.LOW_TO_HIGH)){
                        return Double.compare(list1.getMobilePrice(),list2.getMobilePrice());
                    }else if (Public_Variables.optionName.equals(Constants.HIGH_TO_LOW)){
                        return Double.compare(list2.getMobilePrice(),list1.getMobilePrice());
                    }else {
                        return Double.compare(list2.getMobileRating(),list1.getMobileRating());
                    }
                }
            });
            Sessions.saveFavoriteLists(allFavorites);
        }
        allMobiles.get(position).setFavorite(true);
        notifyItemChanged(position);
    }

    public void removeFavorites(int position){
        List<MobileListsModel> allFavorites = Sessions.readFavoriteLists();
        for (int i = 0; i < allFavorites.size(); i++){
            if (allFavorites.get(i).getMobileId() == allMobiles.get(position).getMobileId()){
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
            if (allFavorites.get(i).getMobileId() == allMobiles.get(position).getMobileId()){
                allFavorites.remove(i);
                break;
            }
        }
        Sessions.saveFavoriteLists(allFavorites);
        allMobiles.remove(position);
        notifyItemRemoved(position);
    }
}