package id.kelompok1.bookspaces;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
	SharedPreferences sharedPreferences;
	SharedPreferences.Editor editor;
	String SHARED_PREF_NAME = "session";
	String SESSION_KEY = "session_user";

	public SessionManager(Context context) {
		sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
		editor = sharedPreferences.edit();
	}

	public void saveSession(Integer id){
		editor.putInt(SESSION_KEY, id).commit();
	}

	public Integer getSession(){
		return sharedPreferences.getInt(SESSION_KEY, -1);
	}

	public void removeSesion(){
		editor.putInt(SESSION_KEY, -1).commit();
	}
}
