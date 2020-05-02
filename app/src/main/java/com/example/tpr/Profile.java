package com.example.tpr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView id;
    Button button2;
    ImageView profile_image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        button2=findViewById(R.id.button2);
        profile_image=findViewById(R.id.profile_image);
        name=findViewById(R.id.name);
        id=findViewById(R.id.id);
        email=findViewById(R.id.email);
        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(this);
        if(signInAccount!=null){
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            id.setText(signInAccount.getId());
            Picasso.get().load(signInAccount.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile_image);
        }
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
