package accounts.accounts;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import java.util.*;

public  class SaveAndLoadManager {

    // This function is used to save account data to SharedPreferences
    public ArrayList<String> saveToSharedPreferences(Context mContext, ArrayList<String> accounts) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        SharedPreferences.Editor edit = preferences.edit();
        edit.putInt("Status_size", accounts.size());

        for(int i=0;i<accounts.size();i++)
        {
            edit.remove("Status_" + i);
            edit.putString("Status_" + i, accounts.get(i));
        }
        edit.commit();
        return  accounts;
    }

    // This function is used to load account data from SharedPreferences
    public ArrayList<String> loadFromSharedPreferences(Context mContext)
    {
        ArrayList<String> accounts = new ArrayList<String>();
        SharedPreferences mSharedPreference1 = PreferenceManager.getDefaultSharedPreferences(mContext);
        accounts.clear();
        int size = mSharedPreference1.getInt("Status_size", 0);

        for(int i=0;i<size;i++)
        {
            accounts.add(mSharedPreference1.getString("Status_" + i, null));
        }
        return  accounts;
    }
}
