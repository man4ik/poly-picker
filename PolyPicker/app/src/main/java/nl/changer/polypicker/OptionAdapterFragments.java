package nl.changer.polypicker;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by jay on 21/4/15.
 */
public class OptionAdapterFragments extends FragmentPagerAdapter {

    private IntentBuilder.Option[] options = IntentBuilder.Option.values();

    /**
     * Number of tabs to be show. Change this value when a tab is added/removed
     */

    public OptionAdapterFragments(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (options[position]) {
            case GALLERY:
                return new GalleryFragment();
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return options[position].getName();
    }

    @Override
    public int getCount() {
        return options.length;
    }

    /**
     * On a call to NotifyDataSetChanged it checks if the item position is POSITION_UNCHANGED
     * or POSITION_NONE if we return NONE here based on some criteria it reloads only that specific
     * fragment
     */
    @Override
    public int getItemPosition(Object object) {
        return POSITION_UNCHANGED;
    }

    public void setOptions(IntentBuilder.Option[] options) {
        this.options = options;
    }
}