package co.oction.auctions.auction;

import android.support.annotation.NonNull;

import java.util.ArrayList;
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
    private final ApiInterface apiService;

    public AuctionsPresenter(@NonNull AuctionsContract.View view) {

        this.mAuctionsView = view;
        apiService = ApiClient.getClient().create(ApiInterface.class);
    }


    @Override
    public void loadAuctions(final AuctionsType auctionsType) {
        mAuctionsView.setLoadingIndicator(true);

        Call<List<AuctionResponse>> call = apiService.getAuctionsList();
        call.enqueue(new Callback<List<AuctionResponse>>() {
            @Override
            public void onResponse(Call<List<AuctionResponse>> call, Response<List<AuctionResponse>> response) {
                if (response.body() != null && !response.body().isEmpty()) {
                    List<AuctionResponse> auctionResponses = filterAuctions(response.body(), auctionsType);
                    if (auctionResponses.isEmpty()) {
                        mAuctionsView.showNoAuctions();
                    } else {
                        mAuctionsView.showAuctions(auctionResponses);
                    }
                } else {
                    mAuctionsView.showNoAuctions();
                }
                mAuctionsView.setLoadingIndicator(false);
            }

            @Override
            public void onFailure(Call<List<AuctionResponse>> call, Throwable t) {
                mAuctionsView.showNoAuctions();
                mAuctionsView.setLoadingIndicator(false);
            }
        });

    }

    /**
     * filter auctions based on the current or upcoming
     *
     * @param list
     * @param auctionsType
     * @return
     */
    private List<AuctionResponse> filterAuctions(List<AuctionResponse> list, AuctionsType auctionsType) {
        List<AuctionResponse> auctionResponses = new ArrayList<>();
        for (AuctionResponse auctionResponse : list) {
            if (auctionsType == AuctionsType.CURRENT_AUCTIONS &&
                    Long.parseLong(auctionResponse.auction.endTimeUnix) < System.currentTimeMillis()) {
                auctionResponses.add(auctionResponse);
            } else if (auctionsType == AuctionsType.UPCOMING_AUCTIONS &&
                    Long.parseLong(auctionResponse.auction.startTimeUnix) > System.currentTimeMillis()) {
                auctionResponses.add(auctionResponse);
            }
        }
        return auctionResponses;
    }
}
