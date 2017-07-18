package co.oction.auctions.auction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import co.oction.auctions.R;
import co.oction.auctions.adapter.CurrentAuctionsAdapter;
import co.oction.auctions.adapter.UpcomingAuctionsAdapter;
import co.oction.auctions.data.model.AuctionResponse;

import java.util.ArrayList;
import java.util.List;


public class AuctionsFragment extends Fragment implements AuctionsContract.View {

    private static final String ARG_PARAM1 = "auctionsType";


    private AuctionsType auctionsType;
    private AuctionsContract.Presenter mPresenter;
    private AuctionsPresenter auctionsPresenter;
    private List<AuctionResponse> mAuctionResponses =new ArrayList<>();
    private CurrentAuctionsAdapter currentAuctionsAdapter;
    private UpcomingAuctionsAdapter upcomingAuctionsAdapter;
    public AuctionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param auctionsType auctionsType
     * @return A new instance of fragment AuctionsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuctionsFragment newInstance(AuctionsType auctionsType) {
        AuctionsFragment fragment = new AuctionsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, auctionsType.ordinal());
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            auctionsType = auctionsType.values()[ getArguments().getInt(ARG_PARAM1)];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auctions, container, false);
        auctionsPresenter = new AuctionsPresenter(this);
        final RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.auctions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(auctionsType == AuctionsType.CURRENT_AUCTIONS) {
            currentAuctionsAdapter = new CurrentAuctionsAdapter(mAuctionResponses, getContext());
        }
        upcomingAuctionsAdapter = new UpcomingAuctionsAdapter(mAuctionResponses,getContext());
        recyclerView.setAdapter(upcomingAuctionsAdapter);
        auctionsPresenter.loadAuctions(AuctionsType.CURRENT_AUCTIONS);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void showAuctions(List<AuctionResponse> auctionResponses) {
        mAuctionResponses.addAll(auctionResponses);
        upcomingAuctionsAdapter.notifyDataSetChanged();
    }
}
