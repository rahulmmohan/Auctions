package co.oction.auctions.auction;

/**
 * Created by Rahul on 17/07/17.
 */

import java.util.List;

import co.oction.auctions.data.model.AuctionResponse;

/**
 * This specifies the contract between the view and the presenter.
 */
public interface AuctionsContract {

    interface View{

        void showAuctions(List<AuctionResponse> auctionResponses);
    }

    interface Presenter{
        void loadAuctions(AuctionsType auctionsType);
    }
}
