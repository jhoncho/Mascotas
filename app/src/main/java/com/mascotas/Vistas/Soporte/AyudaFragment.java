package com.mascotas.Vistas.Soporte;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
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
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View whatsapp=inflater.inflate(R.layout.fragment_ayuda, container, false);

        //codigo para contactarse por whatsapp
        Button whatsaap = (Button) whatsapp.findViewById(R.id.btn_whatsapp);

        whatsaap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://api.whatsapp.com/send?phone="+"59169977949";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        return whatsapp;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();



    }
}
