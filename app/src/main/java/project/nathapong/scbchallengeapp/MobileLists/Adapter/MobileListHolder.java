package project.nathapong.scbchallengeapp.MobileLists.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.nathapong.scbchallengeapp.MobileLists.Model.MobileListsModel;
import project.nathapong.scbchallengeapp.R;
import project.nathapong.scbchallengeapp.Utilities.Sessions;

public class MobileListHolder extends RecyclerView.ViewHolder{

    @BindView(R.id.ivImage) ImageView ivImage;
    @BindView(R.id.tvName) TextView tvName;
    @BindView(R.id.ivFavorite) ImageView ivFavorite;
    @BindView(R.id.tvDescription) TextView tvDescription;
    @BindView(R.id.tvPrice) TextView tvPrice;
    @BindView(R.id.tvRating) TextView tvRating;

    public MobileListHolder(@NonNull View itemView, final AdapterListener adapterListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        ivFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapterListener.onFavoriteClick(getAdapterPosition());
            }
        });
    }
}
