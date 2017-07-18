package co.oction.auctions.auction;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import co.oction.auctions.R;
import co.oction.auctions.adapter.CurrentAuctionsAdapter;
import co.oction.auctions.adapter.UpcomingAuctionsAdapter;
import co.oction.auctions.data.model.AuctionResponse;

import java.util.ArrayList;
import java.util.List;


public class AuctionsFragment extends Fragment implements AuctionsContract.View {

    private static final String ARG_PARAM1 = "auctionsType";


    private AuctionsType auctionsType;
    private AuctionsPresenter auctionsPresenter;
    private List<AuctionResponse> mAuctionResponses = new ArrayList<>();
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private TextView mNoAuctionView;

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
            auctionsType = auctionsType.values()[getArguments().getInt(ARG_PARAM1)];
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_auctions, container, false);
        auctionsPresenter = new AuctionsPresenter(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.auctions);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setAdapter();
        mNoAuctionView = (TextView) view.findViewById(R.id.noAuction);
        // Set up progress indicator
        final SwipeRefreshLayout swipeRefreshLayout =
                (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                auctionsPresenter.loadAuctions();
            }
        });

        return view;
    }


    private void setAdapter() {
        if (auctionsType == AuctionsType.CURRENT_AUCTIONS) {
            adapter = new CurrentAuctionsAdapter(mAuctionResponses, getContext());
        } else {
            adapter = new UpcomingAuctionsAdapter(mAuctionResponses, getContext());
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mAuctionResponses.isEmpty()){
            auctionsPresenter.loadAuctions();
        }
    }

    @Override
    public void setLoadingIndicator(final boolean active) {

        final SwipeRefreshLayout srl =
                (SwipeRefreshLayout) getView().findViewById(R.id.refresh_layout);

        // Make sure setRefreshing() is called after the layout is done with everything else.
        srl.post(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(active);
            }
        });
    }
    @Override
    public void showNoAuctions() {
        recyclerView.setVisibility(View.GONE);
        mNoAuctionView.setVisibility(View.VISIBLE);
    }
    @Override
    public void showAuctions(List<AuctionResponse> auctionResponses) {
        mAuctionResponses.addAll(auctionResponses);
        adapter.notifyDataSetChanged();
        recyclerView.setVisibility(View.VISIBLE);
        mNoAuctionView.setVisibility(View.GONE);
    }
}
