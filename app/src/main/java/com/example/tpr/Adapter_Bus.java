package com.example.tpr;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter_Bus extends BaseAdapter {
    Context c;
    ArrayList<Bus> buseslist;
    private static LayoutInflater inflater=null;

    public Adapter_Bus(Context c, ArrayList<Bus> buseslist) {
        this.c = c;
        this.buseslist = buseslist;
        inflater = (LayoutInflater)c.getSystemService(c.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return buseslist.size();
    }

    @Override
    public Object getItem(int position) {
        return buseslist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_bus, null);
        final TextView matricula=(TextView) vista.findViewById(R.id.textView2);
        final TextView empresa=(TextView) vista.findViewById(R.id.textView9);
        final ImageView imagen=(ImageView) vista.findViewById(R.id.imageView2);
        matricula.setText(buseslist.get(position).getEmpresa());
        empresa.setText(buseslist.get(position).getMatricula());
        imagen.setImageResource(buseslist.get(position).getImagen());
        return vista;
    }
}
