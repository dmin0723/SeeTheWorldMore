package android.dengmin.seetheworldmore.ui;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

/**
 * Created by dmin on 2016/5/12.
 */
public class SettingFragment extends PreferenceFragment implements Preference.OnPreferenceClickListener{

    //定义一些常量
    public static final String ORIGINAL_SPLASH = "original_splash";


    private Preference splash;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        splash = findPreference(ORIGINAL_SPLASH);

        splash.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                secretStepOne();
                return true;
            }
        });
    }

    private void secretStepOne() {
    }


    @Override
    public boolean onPreferenceClick(Preference preference) {
        return false;
    }
}
