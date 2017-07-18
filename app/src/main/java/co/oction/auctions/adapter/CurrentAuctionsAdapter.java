package co.oction.auctions.adapter;

/**
 * Created by Rahul on 17/07/17.
 */

import android.content.Context;
import android.os.CountDownTimer;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import co.oction.auctions.R;
import co.oction.auctions.data.ApiClient;
import co.oction.auctions.data.model.Auction;
import co.oction.auctions.data.model.AuctionResponse;
import co.oction.auctions.data.model.Medium;


public class CurrentAuctionsAdapter extends RecyclerView.Adapter<CurrentAuctionsAdapter.AuctionViewHolder> {

    private final List<AuctionResponse> auctions;
    private final Context context;

    public CurrentAuctionsAdapter(List<AuctionResponse> auctions, Context context) {
        this.auctions = auctions;
        this.context = context;
    }

    @Override
    public AuctionViewHolder onCreateViewHolder(ViewGroup parent,
                                                int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_auction_item, parent, false);
        return new AuctionViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final AuctionViewHolder holder, final int position) {
        final Auction auction = auctions.get(position).auction;
        Medium medium = auctions.get(position).media.get(0);

        holder.itemName.setText(auction.title);
        holder.retailPrice.setText(String.format(context.getString(R.string.retail_price), auction.productCurrency + " " + auction.productPrice));
        holder.startingPrice.setText(auction.currency + " " + auction.startingPrice);
        Glide.with(context).load(ApiClient.BASE_URL + medium.media).into(holder.itemImageView);
        if (holder.Count != null) {
            holder.Count.cancel();
        }
        holder.Count = new CountDownTimer(10000, 150) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) ((millisUntilFinished / 1000));
                holder.progressBar.setProgress(((int) millisUntilFinished));
                holder.remainingTime.setText(seconds + "." + millisUntilFinished % 1000 + "s");

            }

            public void onFinish() {
                float v = Float.parseFloat(auction.startingPrice) + Float.parseFloat(auction.price);
                auction.startingPrice = v + "";
                holder.startingPrice.setText(auction.currency + " " + String.format("%.2f", v));
                start();
            }
        };
        holder.Count.start();
    }


    @Override
    public int getItemCount() {
        return auctions.size();
    }


    public static class AuctionViewHolder extends RecyclerView.ViewHolder {
        final ImageView itemImageView;
        final TextView itemName;
        final TextView remainingTime;
        final TextView retailPrice;
        final TextView startingPrice;
        final ProgressBar progressBar;
        CountDownTimer Count;

        public AuctionViewHolder(View v) {
            super(v);
            itemImageView = ((ImageView) v.findViewById(R.id.item_image));
            itemName = ((TextView) v.findViewById(R.id.item_name));
            remainingTime = ((TextView) v.findViewById(R.id.time));
            retailPrice = ((TextView) v.findViewById(R.id.retail_price));
            startingPrice = ((TextView) v.findViewById(R.id.starting_price));
            progressBar = ((ProgressBar) v.findViewById(R.id.progressBar));
            Count = null;
        }
    }

}