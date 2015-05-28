package nl.changer.polypicker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class IntentBuilder {

    public static final String M_TAB_BACKGROUND_COLOR = "mTabBackgroundColor";
    public static final String M_TAB_SELECTION_INDICATOR_COLOR = "mTabSelectionIndicatorColor";
    public static final String M_SELECTION_LIMIT = "mSelectionLimit";
    public static final String M_CAMERA_BUTTON_COLOR = "mCameraButtonColor";
    public static final String M_OPTIONS = "mOptions";
    private Config mConfig;

    public IntentBuilder() {
        mConfig = new Config();
    }

    public IntentBuilder setTabBackgroundColor(int mTabBackgroundColor) {
        this.mConfig.mTabBackgroundColor = mTabBackgroundColor;
        return this;
    }

    public IntentBuilder setTabSelectionIndicatorColor(int mTabSelectionIndicatorColor) {
        this.mConfig.mTabSelectionIndicatorColor = mTabSelectionIndicatorColor;
        return this;
    }

    public IntentBuilder setSelectionLimit(int mSelectionLimit) {
        this.mConfig.mSelectionLimit = mSelectionLimit;
        return this;
    }

    public IntentBuilder setCameraButtonColor(int mCameraButtonColor) {
        this.mConfig.mCameraButtonColor = mCameraButtonColor;
        return this;
    }

    public IntentBuilder setOptions(Option... options) {
        this.mConfig.mOptions = options;
        return this;
    }

    public Intent createIntent(Context context) {
        Intent intent = new Intent(context, ImagePickerActivity.class);
        intent.putExtra(M_TAB_BACKGROUND_COLOR, mConfig.mTabBackgroundColor);
        intent.putExtra(M_TAB_SELECTION_INDICATOR_COLOR, mConfig.mTabSelectionIndicatorColor);
        intent.putExtra(M_SELECTION_LIMIT, mConfig.mSelectionLimit);
        intent.putExtra(M_CAMERA_BUTTON_COLOR, mConfig.mCameraButtonColor);
        int[] options = new int[mConfig.mOptions.length];
        for (int i = 0; i < mConfig.mOptions.length; i++) {
            options[i] = mConfig.mOptions[i].ordinal();
        }
        intent.putExtra(M_OPTIONS, options);
        return intent;
    }

    public enum Option {
        CAMERA("Take a photo"), GALLERY("Gallery");
        String name;

        Option(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }


    }

    public static class Config {
        private int mTabBackgroundColor = R.color.white;
        private int mTabSelectionIndicatorColor = R.color.orange;
        private int mSelectionLimit = Integer.MAX_VALUE;
        private int mCameraButtonColor = R.color.orange;
        private Option[] mOptions = Option.values();

        public static Config restore(Bundle bundle) {
            Config config = new Config();
            config.mTabBackgroundColor = bundle.getInt(M_TAB_BACKGROUND_COLOR);
            config.mTabSelectionIndicatorColor = bundle.getInt(M_TAB_SELECTION_INDICATOR_COLOR, 0);
            config.mSelectionLimit = bundle.getInt(M_SELECTION_LIMIT, 0);
            config.mCameraButtonColor = bundle.getInt(M_CAMERA_BUTTON_COLOR, 0);
            int[] intArray = bundle.getIntArray(M_OPTIONS);
            Option[] options = new Option[intArray.length];
            for (int i = 0, intArrayLength = intArray.length; i < intArrayLength; i++) {
                options[i] = Option.values()[intArray[i]];
            }
            config.mOptions = options;
            return config;
        }

        public int getTabBackgroundColor() {
            return mTabBackgroundColor;
        }

        public int getTabSelectionIndicatorColor() {
            return mTabSelectionIndicatorColor;
        }

        public int getSelectionLimit() {
            return mSelectionLimit;
        }

        public int getCameraButtonColor() {
            return mCameraButtonColor;
        }

        public Option[] getOptions() {
            return mOptions;
        }
    }

}