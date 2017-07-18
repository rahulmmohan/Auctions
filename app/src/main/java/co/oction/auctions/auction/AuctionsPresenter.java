package co.oction.auctions.auction;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import co.oction.auctions.data.ApiClient;
import co.oction.auctions.data.ApiInterface;
import co.oction.auctions.data.model.AuctionResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Rahul on 17/07/17.
 */

public class AuctionsPresenter implements AuctionsContract.Presenter {

    private final AuctionsContract.View mAuctionsView;
    private ApiInterface apiService;

    public AuctionsPresenter(@NonNull AuctionsContract.View view) {

        this.mAuctionsView = view;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public void loadAuctions(AuctionsType auctionsType) {

        Call<List<AuctionResponse>> call = apiService.getAuctionsList();
        call.enqueue(new Callback<List<AuctionResponse>>() {
            @Override
            public void onResponse(Call<List<AuctionResponse>> call, Response<List<AuctionResponse>> response) {
                mAuctionsView.showAuctions(response.body());
            }

            @Override
            public void onFailure(Call<List<AuctionResponse>> call, Throwable t) {

            }
        });

    }
}
