package com.mascotas.Vistas.Veterinaria;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mascotas.Adaptadores.ServiciosAdapter;
import com.mascotas.Adaptadores.VeterinariosAdapter;
import com.mascotas.Modelo.CiudadModel;
import com.mascotas.Modelo.ServiciosModel;
import com.mascotas.Modelo.VeterinariaModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;
import com.mascotas.Vistas.Servicio.ServicioDetalleActivity;

public class VeterinariaFragment extends Fragment {

    private Context context;
    private String opcionCiudad="1";
    private ListView listView;


    //INTENTOS DE CARGA
    private int CONT_LOAD = 0;

    public VeterinariaFragment(String opcionCiudad) {
        this.opcionCiudad=opcionCiudad;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_veterinarias, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();

        listView=getView().findViewById(R.id.listaveterinarias);
        new ApiRest().cargarVeterinaria(VeterinariaFragment.this, opcionCiudad);
    }

    public void listaLlena(final VeterinariaModel[] items) {
        listView.setAdapter(new VeterinariosAdapter(getContext(), items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                VeterinariaModel item = items[position];
                Intent i = new Intent(getContext(), VeterinariaDetalleActivity.class);
                i.putExtra("idVeterinario", item.getId_veterinario());
                startActivity(i);
            }
        });
    }
}
