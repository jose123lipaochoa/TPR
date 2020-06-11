package com.example.tpr.ui.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tpr.Adaptador_Ruta;
import com.example.tpr.Mapa;
import com.example.tpr.Preferencia;
import com.example.tpr.R;
import com.google.android.gms.common.internal.FallbackServiceBroker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private DatabaseReference Preferidas;
    ListView list_preferidas;
    ArrayList<Preferencia> arrayList=new ArrayList<>();
    Button pasar;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        pasar=(Button)root.findViewById(R.id.button5);
        list_preferidas=(ListView)root.findViewById(R.id.lista_preferidas);
        Preferidas= FirebaseDatabase.getInstance().getReference("Preferencia");
        Preferidas.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        String id=ds.child("id").getValue().toString();
                        String origen=ds.child("origen").getValue().toString();
                        String destino=ds.child("destino").getValue().toString();
                        Preferencia prefer=new Preferencia(id,origen,destino);
                        arrayList.add(prefer);
                    }
                    list_preferidas.setAdapter((ListAdapter) new Adaptador_Ruta(root.getContext(),arrayList));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        pasar.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                startActivity(new Intent(getActivity(), Mapa.class));
            }
        });
        return root;
    }
}
