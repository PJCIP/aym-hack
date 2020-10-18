package com.example.facebook;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;


public class MainActivity extends AppCompatActivity {
    private CallbackManager mcall;
    private FirebaseAuth mFirebase;
    private FirebaseAuth.AuthStateListener authStateListener;
    private TextView name;
    private ImageView photo;
    private LoginButton login;
    Button privacy;
    private AccessTokenTracker accessTokenTracker;
    private static final  String TAG = "FacebookAuthentication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        privacy = (Button)findViewById(R.id.privacy);
        privacy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoprivacy();
            }
        });
        mFirebase = FirebaseAuth.getInstance();
        FacebookSdk.sdkInitialize(getApplicationContext());
        name = findViewById(R.id.name);
        login = findViewById(R.id.login_button);
        login.setReadPermissions("email","public_profile");
        photo = findViewById(R.id.profileImage);
        mcall = CallbackManager.Factory.create();
        login.registerCallback(mcall, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(TAG,"onSuccess"+loginResult);
                handleFacebooktoken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(TAG,"onCancel");

            }

            @Override
            public void onError(FacebookException error) {
                Log.d(TAG,"onERROR"+error);

            }
        });
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user!=null)
                {
                    updateuserid(user);
                }
                else
                {
                    updateuserid(null);
                }
            }
        };

        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                if(currentAccessToken==null)
                {
                    mFirebase.signOut();
                }
            }
        };

    }
    private void handleFacebooktoken(AccessToken token)
    {
        Log.d(TAG,"handleFacebookToken"+token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mFirebase.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Log.d(TAG,"sign in successful");
                    FirebaseUser user = mFirebase.getCurrentUser();
                    updateuserid(user);
                }
                else
                {
                    Log.d(TAG,"sign in fAILED"+task.getException());
                    Toast.makeText(MainActivity.this,"Authontication failed",Toast.LENGTH_LONG).show();
                    updateuserid(null);
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        mcall.onActivityResult(requestCode,resultCode,data);
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void updateuserid(FirebaseUser user)
    {
        if (user !=null){
            name.setText(user.getDisplayName());
            if(user.getPhotoUrl() !=null){
                String photourl = user.getPhotoUrl().toString();
                photourl = photourl+"?type=large";
                Picasso.get().load(photourl).into(photo);
            }
        }
        else
        {
            name.setText("");
            photo.setImageResource(android.R.color.transparent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebase.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null)
        {
            mFirebase.removeAuthStateListener(authStateListener);
        }
    }

    private void gotoprivacy(){
        Intent intent=new Intent(this,privacy.class);
        startActivity(intent);
    }
}