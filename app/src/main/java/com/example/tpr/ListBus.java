package com.example.tpr;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListBus extends AppCompatActivity {
    private DatabaseReference Bus;
    ListView Buses;
    ArrayList<Bus> arrayList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bus);
        final String nombre_dest=getIntent().getStringExtra("destino");
        final String nombre_ori=getIntent().getStringExtra("origen");
        Buses=(ListView)findViewById(R.id.listbusese);
        Bus= FirebaseDatabase.getInstance().getReference("Bus");
        Bus.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    for(DataSnapshot ds:dataSnapshot.getChildren()){
                        String destino=ds.child("ruta").child("destino").child("nombre").getValue().toString();
                        String origen=ds.child("ruta").child("origen").child("nombre").getValue().toString();
                        if(destino.equals(nombre_dest) && origen.equals(nombre_ori)){
                            String matricula=ds.child("matricula").getValue().toString();
                            Integer imagen= Integer.parseInt(ds.child("imagen").getValue().toString());
                            String nombre=ds.child("empresa").getValue().toString();
                            Bus busobject=new Bus(matricula,null,imagen,nombre);
                            arrayList.add(busobject);
                        }
                    }
                    Buses.setAdapter((ListAdapter) new Adapter_Bus(ListBus.this,arrayList));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
