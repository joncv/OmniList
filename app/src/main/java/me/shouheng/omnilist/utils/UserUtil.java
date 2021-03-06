package me.shouheng.omnilist.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import me.shouheng.omnilist.model.tools.SignUser;


/**
 * Created by wangshouheng on 2017/3/13. */
public final class UserUtil {

    private static SignUser signUser;

    private static UserUtil sInstance;

    private static SharedPreferences mPreferences;

    private static final String USER_ID_KEPT = "User_Id_Kept";
    private static final String LOGINED_USER = "Logined_User";


    private UserUtil(final Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static UserUtil getInstance(final Context context) {
        if (sInstance == null) {
            synchronized (UserUtil.class) {
                if (sInstance == null){
                    sInstance = new UserUtil(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    public void setOnAccountKeeperChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPreferences.registerOnSharedPreferenceChangeListener(listener);
    }


    public long getUserIdKept() {
        return mPreferences.getLong(USER_ID_KEPT, 0L);
    }

    public void setUserIdKept(long userId) {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(USER_ID_KEPT, 0);
        editor.apply();
    }

    public static void logoff() {
        final SharedPreferences.Editor editor = mPreferences.edit();
        editor.remove(USER_ID_KEPT);
        editor.apply();
        signUser = null;
    }
}
