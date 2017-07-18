package co.oction.auctions.adapter;

/**
 * Created by Rahul on 17/07/17.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.oction.auctions.R;
import co.oction.auctions.data.ApiClient;
import co.oction.auctions.data.model.Auction;
import co.oction.auctions.data.model.AuctionResponse;
import co.oction.auctions.data.model.Medium;


public class UpcomingAuctionsAdapter extends RecyclerView.Adapter<UpcomingAuctionsAdapter.AuctionViewHolder> {

    private List<AuctionResponse> auctions;
    private Context context;

    public UpcomingAuctionsAdapter(List<AuctionResponse> auctions, Context context) {
        this.auctions = auctions;
        this.context = context;
    }

    @Override
    public AuctionViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.upcoming_auction_item, parent, false);
        return new AuctionViewHolder(view);
    }


    @Override
    public void onBindViewHolder(AuctionViewHolder holder, final int position) {
        Auction auction = auctions.get(position).auction;
        Medium medium = auctions.get(position).media.get(0);

        holder.itemName.setText(auction.title);
        holder.retailPrice.setText(String.format(context.getString(R.string.retail_price),auction.productCurrency+" "+auction.productPrice));
        holder.startingDate.setText(auction.startTime);
        Glide.with(context).load(ApiClient.BASE_URL+medium.media).into(holder.itemImageView);

    }

    @Override
    public int getItemCount() {
        return auctions.size();
    }


    public static class AuctionViewHolder extends RecyclerView.ViewHolder {


        ImageView itemImageView;
        TextView itemName;
        TextView retailPrice;
        TextView startingDate;

        public AuctionViewHolder(View v) {
            super(v);
            itemImageView = ((ImageView) v.findViewById(R.id.item_image));
            itemName = ((TextView) v.findViewById(R.id.item_name));
            retailPrice = ((TextView) v.findViewById(R.id.retail_price));
            startingDate = ((TextView) v.findViewById(R.id.start_time));
        }
    }

}