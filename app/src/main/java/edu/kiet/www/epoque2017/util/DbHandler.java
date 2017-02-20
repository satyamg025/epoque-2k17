package edu.kiet.www.epoque2017.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;

import edu.kiet.www.epoque2017.Splash.SplashActivity;
import edu.kiet.www.epoque2017.app.Config;
import edu.kiet.www.epoque2017.ui.coloredSnackBar;

/**
 * Created by satyam on 2/10/17.
 */
public class DbHandler {
    public static void putInt(Context context, String Key, int value)
    {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences(Config.DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(Key,value);
        editor.commit();
    }
    public static void putString(Context context,String Key,String value)
    {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences(Config.DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(Key,value);
        editor.commit();
    }
    public static void putBoolean(Context context,String Key,Boolean value)
    {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences(Config.DB_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(Key,value);
        editor.commit();
    }
    public static int getInt(Context context,String Key,int Alternate)
    {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences(Config.DB_NAME, Context.MODE_PRIVATE);
        return prefs.getInt(Key, Alternate);
    }
    public static String getString(Context context,String Key,String Alternate)
    {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences(Config.DB_NAME, Context.MODE_PRIVATE);
        return prefs.getString(Key, Alternate);
    }
    public static Boolean getBoolean(Context context,String Key,Boolean Alternate)
    {
        SharedPreferences prefs;
        prefs = context.getSharedPreferences(Config.DB_NAME, Context.MODE_PRIVATE);
        return prefs.getBoolean(Key, Alternate);
    }
    public static void clearDb(Context context)
    {
        SharedPreferences preferences = context.getSharedPreferences(Config.DB_NAME, 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
    public static void setSession(Context context,String bearer,String type)
    {
        //FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
        DbHandler.putString(context, "type", type);
        DbHandler.putString(context,"bearer",bearer);
        DbHandler.putBoolean(context,"isLoggedIn",true);
    }

    public static void unsetSession(Context context,String type)
    {
       // FirebaseMessaging.getInstance().unsubscribeFromTopic(Config.TOPIC_GLOBAL);
        DbHandler.clearDb(context);
        DbHandler.putBoolean(context, "isLoggedIn", false);
        Bundle b = new Bundle();
        b.putBoolean(type, true);
        Intent i = new Intent(context, SplashActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.putExtras(b);
        context.startActivity(i);
        ((Activity) context).finishAffinity();
    }
}
