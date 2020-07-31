package com.mascotas.Vistas.Soporte;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mascotas.Adaptadores.CiudadesAdapter;
import com.mascotas.Modelo.CiudadModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;
import com.mascotas.Vistas.AdoptaVida.AdoptaVidaFragment;
import com.mascotas.Vistas.Fundaciones.FundacionesFragment;
import com.mascotas.Vistas.Mascota.MascotasEncuentraFragment;
import com.mascotas.Vistas.Veterinaria.VeterinariaFragment;
import com.mascotas.Vistas.Veterinaria.VeterinariaMapaFragment;

public class AyudaFragment extends Fragment {

    private Context context;

    public AyudaFragment() {
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
        return inflater.inflate(R.layout.fragment_ayuda, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();
    }
}
