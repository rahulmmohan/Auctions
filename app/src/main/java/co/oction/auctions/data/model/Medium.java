package co.oction.auctions.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Rahul on 17/07/17.
 */

public class Medium {

    @SerializedName("id")
    @Expose
    public String id;
    @SerializedName("media")
    @Expose
    public String media;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("product_id")
    @Expose
    public String productId;
}
