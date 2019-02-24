package project.nathapong.scbchallengeapp.MobileList.MobileListFragment.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.nathapong.scbchallengeapp.MobileList.Model.MobileListsModel;
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
        this.context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.mobile_lists_adapter, viewGroup, false);
        return new MobileListsHolder(view, allMobiles, adapterListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MobileListsHolder mobileListsHolder = (MobileListsHolder)viewHolder;
        mobileListsHolder.tvName.setText(allMobiles.get(i).getName());
        mobileListsHolder.tvDescription.setText(allMobiles.get(i).getDescription());
        mobileListsHolder.tvPrice.setText("" + context.getText(R.string.price) + allMobiles.get(i).getPrice());
        mobileListsHolder.tvRating.setText("" + context.getText(R.string.rating) + allMobiles.get(i).getRating());
        mobileListsHolder.ivFavorite.setImageResource(allMobiles.get(i).isFavorite() ? R.drawable.ic_favorite : R.drawable.ic_no_favorite);
        Glide.with(context).load(allMobiles.get(i).getThumbImageURL()).into(mobileListsHolder.ivImage);
    }

    @Override
    public int getItemCount() {
        return allMobiles.size();
    }
}

class MobileListsHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ivImage) ImageView ivImage;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.ivFavorite) ImageView ivFavorite;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.tvRating) TextView tvRating;

    public MobileListsHolder(@NonNull View itemView, final List<MobileListsModel> allMobiles, final AdapterListener adapterListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (allMobiles.get(getAdapterPosition()).isFavorite()){
                    List<Integer> allFavoriteID = Sessions.readFavoriteLists();
                    for (int i = 0; i < allFavoriteID.size(); i++){
                        if (allFavoriteID.get(i) == allMobiles.get(getAdapterPosition()).getId()){
                            allFavoriteID.remove(i);
                            break;
                        }
                    }
                    Sessions.saveFavoriteLists(allFavoriteID);
                }else {
                    List<Integer> allFavoriteID = Sessions.readFavoriteLists();
                    allFavoriteID.add(allMobiles.get(getAdapterPosition()).getId());
                    Sessions.saveFavoriteLists(allFavoriteID);
                }

                adapterListener.onFavoriteClick(getAdapterPosition(), allMobiles.get(getAdapterPosition()).getId(), allMobiles.get(getAdapterPosition()).isFavorite());
            }
        });
    }
}
