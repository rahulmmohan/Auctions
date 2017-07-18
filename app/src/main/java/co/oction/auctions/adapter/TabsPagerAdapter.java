package co.oction.auctions.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import co.oction.auctions.auction.AuctionsFragment;
import co.oction.auctions.auction.AuctionsType;

/**
 * Created by Rahul on 17/07/17.
 */

public class TabsPagerAdapter extends FragmentPagerAdapter {

    // Tab titles
    private String[] tabs = { "CURRENT", "UPCOMING" };

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return AuctionsFragment.newInstance(AuctionsType.CURRENT_AUCTIONS);
            case 1:
                return AuctionsFragment.newInstance(AuctionsType.UPCOMING_AUCTIONS);
        }

        return null;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return tabs[position];
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }
}