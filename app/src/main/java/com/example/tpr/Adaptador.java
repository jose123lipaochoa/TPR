package com.example.tpr;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class Adaptador extends BaseAdapter {
    private static LayoutInflater inflater=null;
    Context contexto;
    String[][]datos;
    int[]datosImg;
    public Adaptador(Context contexto, String[][] datos, int[] datosImg) {
        this.contexto = contexto;
        this.datos = datos;
        this.datosImg = datosImg;
        inflater = (LayoutInflater)contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);    }

    @Override
    public int getCount() {
        return datosImg.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View vista = inflater.inflate(R.layout.elemento_lista, null);


        TextView nombre = (TextView) vista.findViewById(R.id.nombre);
        TextView direccion = (TextView) vista.findViewById(R.id.direccion);
        TextView placa = (TextView) vista.findViewById(R.id.placa);
        ImageView imagen = (ImageView) vista.findViewById(R.id.imageView3);

        nombre.setText(datos[position][0]);
        direccion.setText(datos[position][1]);
        placa.setText(datos[position][2]);
        imagen.setImageResource(datosImg[position]);

        imagen.setTag(position);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImagen = new Intent(contexto, VisorImagen.class);
                visorImagen.putExtra("IMG", datosImg[(Integer)v.getTag()]);
                contexto.startActivity(visorImagen);
            }
        });
        return vista;
    }
}
