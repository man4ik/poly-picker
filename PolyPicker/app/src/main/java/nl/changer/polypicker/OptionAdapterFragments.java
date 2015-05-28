package nl.changer.polypicker;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentStatePagerAdapter;

/**
 * Created by jay on 21/4/15.
 */
public class OptionAdapterFragments extends FragmentStatePagerAdapter {

    private IntentBuilder.Option[] options = IntentBuilder.Option.values();
    /**
     * Number of tabs to be show. Change this value when a tab is added/removed
     */
    private Intent activityIntent;

    public OptionAdapterFragments(FragmentManager fm, Intent activityIntent) {
        super(fm);
        this.activityIntent = activityIntent;
    }

    @Override
    public Fragment getItem(int position) {
        switch (options[position]) {
            case GALLERY:
                return new GalleryFragment();
            case CAMERA:
                CwacCameraFragment profileInfoFragment = new CwacCameraFragment();
                Bundle args = new Bundle();
                args.putBundle(CwacCameraFragment.BUNDLE_CONFIG, activityIntent.getExtras());
                profileInfoFragment.setArguments(args);
                return profileInfoFragment;
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