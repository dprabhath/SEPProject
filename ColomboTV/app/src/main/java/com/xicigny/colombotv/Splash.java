package com.xicigny.colombotv;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Dileepa on 4/13/2016.
 */
public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        final ImageView iv = (ImageView) findViewById(R.id.image_logo);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_in);

        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //   iv.startAnimation(an);
                alert();

                //Intent intent = new Intent(Splash.this, SignInActivity.class);
                //intent.putExtra("activity","splash");
                //startActivity(intent);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }

    public boolean checkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfoMobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo activeNetInfoWifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isConnectedData = activeNetInfoMobile != null && activeNetInfoMobile.isConnectedOrConnecting();
        boolean isConnectedWifi = activeNetInfoWifi != null && activeNetInfoWifi.isConnectedOrConnecting();
        if (isConnectedData || isConnectedWifi) {
            return true;
        }
        else
            return false;
    }

    public boolean alert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("This app needs an internet connection.")
                .setPositiveButton("retry", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        if(checkConnection())
                        {
                            Intent intent = new Intent(Splash.this, SignInActivity.class);
                            intent.putExtra("activity","splash");
                            startActivity(intent);
                            finish();
                        }else{
                            alert();
                        }

                    }
                });
        builder.create().show();
        return true;
    }
}
