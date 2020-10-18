package com.example.firebaseset;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.analytics.FirebaseAnalytics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import static com.google.firebase.analytics.FirebaseAnalytics.UserProperty.ALLOW_AD_PERSONALIZATION_SIGNALS;

public class Privacy extends AppCompatActivity {
    Switch s1,s2;
    private FirebaseAnalytics fb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        s1 =(Switch)findViewById(R.id.switch2);
        s2 =(Switch)findViewById(R.id.switch3);
        fb = FirebaseAnalytics.getInstance(this);

        s1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b == true){
                    fb.setAnalyticsCollectionEnabled(false);
                    Toast.makeText(getBaseContext(),"analytics not allowed",Toast.LENGTH_LONG).show();
                }

                else
                {
                    fb.setAnalyticsCollectionEnabled(true);
                    Toast.makeText(getBaseContext(),"analytics allowed",Toast.LENGTH_LONG).show();
                }
            }
        });

        s2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b==true){


                    fb.setUserProperty( ALLOW_AD_PERSONALIZATION_SIGNALS, "false" );
                    fb.setAnalyticsCollectionEnabled(false);
                    Toast.makeText(getBaseContext(),"ad not allowed",Toast.LENGTH_LONG).show();
                }
                else
                {
                    fb.setUserProperty( ALLOW_AD_PERSONALIZATION_SIGNALS, "true" );
                    fb.setAnalyticsCollectionEnabled(true);
                    Toast.makeText(getBaseContext(),"ad allowed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}