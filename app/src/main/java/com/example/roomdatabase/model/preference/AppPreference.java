package com.example.roomdatabase.model.preference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.roomdatabase.R;

public class AppPreference implements PreferenceHelper {
    private final SharedPreferences mPrefs;
    private Context mContext;


    public AppPreference(Context context) {
        mContext=context;
        this.mPrefs = context.getSharedPreferences("mySharedPreference",Context.MODE_PRIVATE);
    }

    @Override
    public int getUserId() {
        return mPrefs.getInt(mContext.getString(R.string.shared_preference_user_id_key),-1);
    }

    @Override
    public void setUserId(int userId) {

        mPrefs.edit().putInt(mContext.getString(R.string.shared_preference_user_id_key),userId).apply();
    }



}
