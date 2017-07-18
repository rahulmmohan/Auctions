package co.oction.auctions.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Rahul on 17/07/17.
 */

public class AuctionResponse {

    @SerializedName("auction")
    @Expose
    public Auction auction;

    @SerializedName("media")
    @Expose
    public final List<Medium> media = null;
}
