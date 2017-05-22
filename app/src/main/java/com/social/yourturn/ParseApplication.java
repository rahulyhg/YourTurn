package com.social.yourturn;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.social.yourturn.utils.ParseConstant;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by ousmane on 4/18/17.
 */

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        // Add your initialization code here
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.app_id))
                .server(getString(R.string.server))
                .clientKey(null)
                .clientBuilder(builder)
                .build());

        // Need to register GCM token
        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("device_id", getCurrentPhoneNumber());
        installation.saveInBackground();
        ParsePush.subscribeInBackground("pushChannel");
    }

    private String getCurrentPhoneNumber(){
        SharedPreferences sharePref = getSharedPreferences(getString(R.string.user_credentials), Context.MODE_PRIVATE);
        return sharePref.getString(ParseConstant.USERNAME_COLUMN, "");
    }
}