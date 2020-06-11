package com.example.tpr;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Point;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

public class Mapa extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private DatabaseReference Destino;
    private DatabaseReference Origen;
    private DatabaseReference Ruta;
    private DatabaseReference Puntos;
    private DatabaseReference Bus;
    Destino destino;
    Origen origen;
    public Button anadir;
    public Button buscar;
    private DatabaseReference Preferencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null)
            getSupportActionBar().hide();
        setContentView(R.layout.activity_mapa);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Destino = FirebaseDatabase.getInstance().getReference("Destino");
        Origen = FirebaseDatabase.getInstance().getReference("Origen");
        Ruta = FirebaseDatabase.getInstance().getReference("Ruta");
        Preferencia = FirebaseDatabase.getInstance().getReference("Preferencia");
        Puntos = FirebaseDatabase.getInstance().getReference("Puntos");
        Bus = FirebaseDatabase.getInstance().getReference("Bus");
        anadir = (Button) findViewById(R.id.Anadir);
        buscar = (Button) findViewById(R.id.Buscar);
        final SearchView search = (SearchView) findViewById(R.id.sv_location);
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = search.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(Mapa.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    if (!TextUtils.isEmpty(location)) {
                        String id = Origen.push().getKey();
                        Double latitud = address.getLatitude();
                        Double longitude = address.getLongitude();
                        origen = new Origen(id, "Casa", latitud, longitude);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        final SearchView search1 = (SearchView) findViewById(R.id.sv_destino);
        search1.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String location = search1.getQuery().toString();
                List<Address> addressList = null;
                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(Mapa.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title(location));
                    mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));
                    if (!TextUtils.isEmpty(location)) {
                        String id = Destino.push().getKey();
                        Double latitud = address.getLatitude();
                        Double longitude = address.getLongitude();
                        destino = new Destino(id, location, latitud, longitude);
                    }
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        buscar.setOnClickListener(new View.OnClickListener() {
            Integer cont=0;
            @Override
            public void onClick(View v) {
                Ruta.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if(dataSnapshot.exists()){
                            for(DataSnapshot ds:dataSnapshot.getChildren()){
                                String nombre=ds.child("destino").child("nombre").getValue().toString();
                                Double latitud_dest= Double.parseDouble(ds.child("destino").child("latitud").getValue().toString());
                                Double longitud_dest= Double.parseDouble(ds.child("destino").child("longitud").getValue().toString());
                                Double latitud_ori= Double.parseDouble(ds.child("origen").child("latitud").getValue().toString());
                                Double longitud_ori= Double.parseDouble(ds.child("origen").child("longitud").getValue().toString());
                                PolylineOptions camino=new PolylineOptions();
                                if(Double.compare(destino.getLatitud(),latitud_dest)==0 && Double.compare(destino.getLongitud(),longitud_dest)==0){
                                    if(Double.compare(origen.getLatitud(),latitud_ori)==0 && Double.compare(origen.getLongitud(),longitud_ori)==0){
                                        camino.add(new LatLng(origen.getLatitud(),origen.getLongitud()));
                                        for (int i=0;i<ds.child("puntos").getChildrenCount();i=i+1){
                                            Double punto_lat= Double.parseDouble(ds.child("puntos").child(Integer.toString(i)).child("latitud").getValue().toString());
                                            Double punto_long= Double.parseDouble(ds.child("puntos").child(Integer.toString(i)).child("longitud").getValue().toString());
                                            LatLng Latlng_nodo=new LatLng(punto_lat,punto_long);
                                            camino.add(Latlng_nodo);
                                            Toast.makeText(Mapa.this,ds.child("destino").child("latitud").getValue().toString(),Toast.LENGTH_SHORT).show();
                                        }
                                        camino.add(new LatLng(destino.getLatitud(),destino.getLongitud()));
                                        mMap.addPolyline(camino.width(10).color(getRandomColor()));
                                    }
                                }
                                else{
                                    Toast.makeText(Mapa.this,"Perdon aun no hemos llegado a ti",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });

        anadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id=Preferencia.push().getKey();
                Preferencia preferencia = new Preferencia(id,origen.nombre,destino.nombre);
                Preferencia.child(id).setValue(preferencia);
            }
        });
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Arequipa = new LatLng(-16.4040516, -71.5565211);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Arequipa, 18));
        mMap.setMyLocationEnabled(true);
    }
    public int getRandomColor(){
        Random rnd = new Random();
        return Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }
}