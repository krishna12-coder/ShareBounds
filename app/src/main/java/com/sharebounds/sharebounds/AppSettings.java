package com.sharebounds.sharebounds;

import android.content.Context;
import android.content.SharedPreferences;
//import android.support.v7.preference.PreferenceManager;
import androidx.preference.PreferenceManager;


public final class AppSettings {

    public enum LaunchDialog {Tutorial, Rate, None}

    private static AppSettings sInstance;
//    private SharedPreferences mSharedPreferences;
    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    private static final String DESIGN_LIST_KEY = "design_colors_list";
    private static final String KEYBOARD_LIST = "keyboard_globe_list";
    private static final String APP_PRO_VERSION = "pro_sharebounds_msg";
    private static final String FLASH_MODE = "flash_mode";
    private static final String IS_FULL_SCREEN = "is_full_screen";
    private static final String KEYBOARD_SOUND = "keyboard_sound";
    private static final String KEYBOARD_VIBRATION = "keyboard_vibration";
    private static final String TERMS_ACCEPTED = "terms_accepted";

    private static final int SHOW_RATE_COUNT = 15;
    private static final String RATE_COUNT = "rate_count";

    private AppSettings(Context context) {
        this.sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    // ONLY PASS APPLICATION CONTEXT
    public static AppSettings getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new AppSettings(context);
        }
        return sInstance;
    }

    public boolean getIsFullScreen() {
        return sharedPreferences.getBoolean(IS_FULL_SCREEN, false);
    }

    public void setIsFullScreen(boolean bool) {
        sharedPreferences.edit().putBoolean(IS_FULL_SCREEN, bool).apply();
    }

    public int getFlashMode() {
        return sharedPreferences.getInt(FLASH_MODE, 0);
    }

    public void setFlashMode(int i) {
        sharedPreferences.edit().putInt(FLASH_MODE, i).apply();
    }

    public int getTheme() {
        return Integer.parseInt(sharedPreferences.getString(DESIGN_LIST_KEY, "0"));
    }

    public int getGlobeFunction() {
        String value = sharedPreferences.getString(KEYBOARD_LIST, "0");
        return Integer.parseInt(value);
    }

    public boolean getKeyboardSound() {
        return sharedPreferences.getBoolean(KEYBOARD_SOUND, true);
    }

    public boolean getKeyboardVibration() {
        return sharedPreferences.getBoolean(KEYBOARD_VIBRATION, true);
    }

    public boolean getAppPro() {
        // also check store cache, w/o in app: add text
        return sharedPreferences.getBoolean(APP_PRO_VERSION, false);
    }

    public LaunchDialog getLaunchDialog() {
        int count = sharedPreferences.getInt(RATE_COUNT, 0);
        if (count == -1 || count > SHOW_RATE_COUNT) return LaunchDialog.None;

        newRateDialogCount(count+1);
        if (count == 0) return LaunchDialog.Tutorial;
        if (count == SHOW_RATE_COUNT) return LaunchDialog.Rate;
        return LaunchDialog.None;
    }

    public void newRateDialogCount(int newCount) {
        sharedPreferences.edit().putInt(RATE_COUNT, newCount).apply();
    }

    public boolean getTermsAccepted() {
        return sharedPreferences.getBoolean(TERMS_ACCEPTED, false);
    }

    public void setTermsAccepted(boolean bool) {
        sharedPreferences.edit().putBoolean(TERMS_ACCEPTED, bool).apply();
    }
}
