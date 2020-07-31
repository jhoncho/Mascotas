package com.mascotas.Vistas.Ciudad;

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

public class CiudadFragment extends Fragment {

    private Context context;
    private ListView listView;
    private int destino;

    public CiudadFragment(int destino) {
        this.destino=destino;
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
        return inflater.inflate(R.layout.fragment_ciudades, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.context = this.getContext();

        listView=getView().findViewById(R.id.listaCiudades);
        new ApiRest().cargarCiudades(CiudadFragment.this);
    }

    public void listaLlena(final CiudadModel[] items) {
        listView.setAdapter(new CiudadesAdapter(getContext(), items));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                CiudadModel item= (CiudadModel) parent.getItemAtPosition(position);
                CiudadModel item=items[position];
                        switch (destino){
                    case 1://veterinaraia
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_content, new VeterinariaFragment(item.getId_ciudad()))
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 2://Mapa
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_content, new VeterinariaMapaFragment(item.getId_ciudad()))
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 3://Adoptavida
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_content, new MascotasEncuentraFragment(item.getId_ciudad()))
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 4://BuscaMascota
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_content, new AdoptaVidaFragment(item.getId_ciudad()))
                                .addToBackStack(null)
                                .commit();
                        break;
                    case 5://fundaciones
                        getFragmentManager().beginTransaction()
                                .replace(R.id.home_content, new FundacionesFragment(item.getId_ciudad()))
                                .addToBackStack(null)
                                .commit();
                        break;
                }
            }
        });
    }
}
