package com.mascotas.Vistas.AdoptaVida;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mascotas.Adaptadores.AdoptaVidaAdapter;
import com.mascotas.Adaptadores.MascotasEncuentraAdapter;
import com.mascotas.Modelo.AdoptaVidaModel;
import com.mascotas.Modelo.MascotasEncuentraModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;
import com.mascotas.Vistas.Mascota.MascotaEncuentraDetalleActivity;
import com.mascotas.Vistas.Mascota.MascotaReportaActivity;

import static android.app.Activity.RESULT_OK;

public class AdoptaVidaFragment extends Fragment {

    private Context context;
    private String opcionCiudad="1";
    private ListView listView;


    //INTENTOS DE CARGA
    private int CONT_LOAD = 0;

    public AdoptaVidaFragment(String opcionCiudad) {
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
        return inflater.inflate(R.layout.fragment_adopta_vida, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();

        listView=getView().findViewById(R.id.listaAdoptaVida);
        new ApiRest().cargarAdoptaVida(AdoptaVidaFragment.this, opcionCiudad);
    }

    public void listaLlena(final AdoptaVidaModel[] items) {
        listView.setAdapter(new AdoptaVidaAdapter(getContext(), items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AdoptaVidaModel item = items[position];
                Intent i = new Intent(getContext(), AdoptaVidaDetalleActivity.class);
                i.putExtra("adoptaVida", item.getId_adopta_vida());
                startActivity(i);
            }
        });
    }
}
