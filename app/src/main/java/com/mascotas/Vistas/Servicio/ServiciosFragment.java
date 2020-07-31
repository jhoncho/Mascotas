package com.mascotas.Vistas.Servicio;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mascotas.Adaptadores.ServiciosAdapter;
import com.mascotas.Modelo.ServiciosModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

public class ServiciosFragment extends Fragment {

    private Context context;
    private String opcionCiudad="1";
    private ListView listView;


    //INTENTOS DE CARGA
    private int CONT_LOAD = 0;

    public ServiciosFragment(String opcionCiudad) {
        this.opcionCiudad=opcionCiudad;
        // Required empty public constructor
//        new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date())
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_servicios, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();

        listView=getView().findViewById(R.id.listaServicios);
        new ApiRest().cargarServicios(ServiciosFragment.this, opcionCiudad);
    }

    public void listaLlena(ServiciosModel[] items) {
        listView.setAdapter(new ServiciosAdapter(getContext(), items));
    }
}
