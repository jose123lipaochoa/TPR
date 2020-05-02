package com.example.tpr.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tpr.MainActivity;
import com.example.tpr.Navegador;
import com.example.tpr.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.squareup.picasso.Picasso;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView name = root.findViewById(R.id.name);
        final TextView id = root.findViewById(R.id.id);
        final TextView email = root.findViewById(R.id.email);
        final Button button2=root.findViewById(R.id.button2);
        final ImageView profile_image=root.findViewById(R.id.profile_image);
        GoogleSignInAccount signInAccount= GoogleSignIn.getLastSignedInAccount(root.getContext());
        if(signInAccount!=null){
            name.setText(signInAccount.getDisplayName());
            email.setText(signInAccount.getEmail());
            Picasso.get().load(signInAccount.getPhotoUrl()).placeholder(R.mipmap.ic_launcher).into(profile_image);
        }
        button2.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();
                Intent intent= new Intent(root.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }
}
