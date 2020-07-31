package com.mascotas.Vistas.Mascota;

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

import com.mascotas.Adaptadores.MascotasEncuentraAdapter;
import com.mascotas.Modelo.MascotasEncuentraModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

import static android.app.Activity.RESULT_OK;

public class MascotasEncuentraFragment extends Fragment {

    private Context context;
    private String opcionCiudad="1";
    private ListView listView;


    //INTENTOS DE CARGA
    private int CONT_LOAD = 0;

    public MascotasEncuentraFragment(String opcionCiudad) {
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
        return inflater.inflate(R.layout.fragment_busca_mascota, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();

        listView=getView().findViewById(R.id.listaMascotas);
        Button btnAgregar=getView().findViewById(R.id.btnAgregar);
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), MascotaReportaActivity.class);
                i.putExtra("idCiudad", opcionCiudad);
                startActivityForResult(i, 100);
            }
        });
        new ApiRest().cargarEncuentraMascota(MascotasEncuentraFragment.this, opcionCiudad);
    }

    public void listaLlena(final MascotasEncuentraModel[] items) {
        listView.setAdapter(new MascotasEncuentraAdapter(getContext(), items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MascotasEncuentraModel item = items[position];
                Intent i = new Intent(getContext(), MascotaEncuentraDetalleActivity.class);
                i.putExtra("encuentraMascota", item.getId_encuentra_mascota());
                startActivity(i);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==100){
            if (resultCode==RESULT_OK){
                new ApiRest().cargarEncuentraMascota(MascotasEncuentraFragment.this, opcionCiudad);
            }
        }
    }
}
