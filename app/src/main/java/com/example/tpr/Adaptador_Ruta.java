package com.example.tpr;

import android.content.Context;
import android.content.Intent;
import android.os.IInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Adaptador_Ruta extends BaseAdapter {
    Context c;
    private DatabaseReference Preferidas;
    ArrayList<Preferencia> preferencias;
    private static LayoutInflater inflater=null;
    public Adaptador_Ruta(Context c, ArrayList<Preferencia> preferencias) {
        this.c = c;
        this.preferencias = preferencias;
        inflater = (LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
        Preferidas= FirebaseDatabase.getInstance().getReference("Preferencia");
    }

    @Override
    public int getCount() {
        return preferencias.size();
    }

    @Override
    public Object getItem(int position) {
        return preferencias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_ruta, null);
        final TextView origen=(TextView) vista.findViewById(R.id.rutadestori);
        final TextView destino=(TextView) vista.findViewById(R.id.textView8);
        origen.setText(preferencias.get(position).origen);
        destino.setText(preferencias.get(position).destino);
        String id=preferencias.get(position).id;
        Button btn=(Button) vista.findViewById(R.id.buses);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(c, ListBus.class);
                intent.putExtra("destino",destino.getText());
                intent.putExtra("origen",origen.getText());
                c.startActivity(intent);
            }
        });
        Button delete=(Button) vista.findViewById(R.id.button4);
        delete.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Preferidas.child(preferencias.get(position).getId()).removeValue();
            }
        });
        return vista;
    }
}
