package com.example.tpr.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.tpr.Adaptador;
import com.example.tpr.R;

public class SlideshowFragment extends Fragment {
    ListView buses;
    String[][]datos={
            {"Bus1","UCSM","A6X-123"},
            {"Bus2","CRISTO REY","A1A-970"},
            {"Bus3","PAUCARPATA","A1A-950"}
    };
    int[] datosImg = {R.drawable.busrojo, R.drawable.busazul, R.drawable.busceleste};
    private SlideshowViewModel slideshowViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        buses=(ListView)root.findViewById(R.id.ListView1);
        buses.setAdapter(new Adaptador(root.getContext(),datos,datosImg));
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}
