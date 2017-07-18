package co.oction.auctions.data;

import java.util.List;

import co.oction.auctions.data.model.AuctionResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Rahul on 17/07/17.
 */

public interface ApiInterface {

    @GET("api/v1/auctions")
    Call<List<AuctionResponse>> getAuctionsList();

}
