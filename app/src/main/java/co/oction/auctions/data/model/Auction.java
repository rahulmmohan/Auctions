package co.oction.auctions.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul on 17/07/17.
 */

public class Auction {
    @SerializedName("user_name")
    @Expose
    public Object userName;
    @SerializedName("name")
    @Expose
    public Object name;
    @SerializedName("profileImage")
    @Expose
    public Object profileImage;
    @SerializedName("userId")
    @Expose
    public Object userId;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("productPrice")
    @Expose
    public String productPrice;
    @SerializedName("product_id")
    @Expose
    public String productId;
    @SerializedName("productCurrency")
    @Expose
    public String productCurrency;
    @SerializedName("auctionId")
    @Expose
    public String auctionId;
    @SerializedName("isHidden")
    @Expose
    public String isHidden;
    @SerializedName("startingPrice")
    @Expose
    public String startingPrice;
    @SerializedName("currency")
    @Expose
    public String currency;
    @SerializedName("price")
    @Expose
    public String price;
    @SerializedName("unique_id")
    @Expose
    public String uniqueId;
    @SerializedName("start_time")
    @Expose
    public String startTime;
    @SerializedName("start_time_unix")
    @Expose
    public String startTimeUnix;
    @SerializedName("end_time")
    @Expose
    public String endTime;
    @SerializedName("end_time_unix")
    @Expose
    public String endTimeUnix;
    @SerializedName("minimumPrice")
    @Expose
    public String minimumPrice;
    @SerializedName("bidCount")
    @Expose
    public Integer bidCount;
    @SerializedName("auctionsHeldCount")
    @Expose
    public String auctionsHeldCount;
    @SerializedName("bidderCount")
    @Expose
    public Integer bidderCount;
}
