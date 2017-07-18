package co.oction.auctions.auction;

/**
 * Created by Rahul on 17/07/17.
 */

import java.util.List;

import co.oction.auctions.data.model.AuctionResponse;

/**
 * This specifies the contract between the view and the presenter.
 */
interface AuctionsContract {

    interface View{
        void setLoadingIndicator(boolean active);

        void showNoAuctions();

        void showAuctions(List<AuctionResponse> auctionResponses);
    }

    interface Presenter{
        void loadAuctions(AuctionsType auctionsType);
    }
}
