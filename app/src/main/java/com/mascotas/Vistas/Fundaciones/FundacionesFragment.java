package com.mascotas.Vistas.Fundaciones;

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

import com.mascotas.Adaptadores.AdoptaVidaAdapter;
import com.mascotas.Adaptadores.FundacionesAdapter;
import com.mascotas.Modelo.AdoptaVidaModel;
import com.mascotas.Modelo.FundacionesModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;
import com.mascotas.Vistas.AdoptaVida.AdoptaVidaDetalleActivity;

public class FundacionesFragment extends Fragment {

    private Context context;
    private String opcionCiudad="1";
    private ListView listView;


    //INTENTOS DE CARGA
    private int CONT_LOAD = 0;

    public FundacionesFragment(String opcionCiudad) {
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
        return inflater.inflate(R.layout.fragment_fundaciones, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();

        listView=getView().findViewById(R.id.listafundaciones);
        new ApiRest().cargarFundaciones(FundacionesFragment.this, opcionCiudad);
    }

    public void listaLlena(final FundacionesModel[] items) {
        listView.setAdapter(new FundacionesAdapter(getContext(), items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FundacionesModel item = items[position];
                Intent i = new Intent(getContext(), FundacionesDetalleActivity.class);
                i.putExtra("fundaciones", item.getId_fundacion());
                startActivity(i);
            }
        });
    }
}
