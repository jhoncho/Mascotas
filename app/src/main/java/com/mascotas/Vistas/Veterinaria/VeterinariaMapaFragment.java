package com.mascotas.Vistas.Veterinaria;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mascotas.Modelo.VeterinarioLocalizacionModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

public class VeterinariaMapaFragment extends  Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng ubicacionOrigen=null;
    private String opcionCiudad="1";


    public VeterinariaMapaFragment(String opcionCiudad) {
        //la ciudad no se esta utilizando ojo!!!
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
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map2);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        ubicacionOrigen = new LatLng(-17.0568696, -64.9912286);
        new ApiRest().cargarLocalizacion(VeterinariaMapaFragment.this);
    }

    public void listaLlena(final VeterinarioLocalizacionModel[] items) {
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionOrigen, 5), 5000, null);
        for (int i = 0; i < items.length; i++) {
            mMap.addMarker(new MarkerOptions()
                    .snippet(items[i].getTitulo_veterinario())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marcador))
                    .position(new LatLng(Double.parseDouble(items[i].getLatitud()), Double.parseDouble(items[i].getLongitud())))
                    .title(items[i].getTitulo_veterinario()));
            final int finalI = i;
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    Intent i = new Intent(getContext(), VeterinariaDetalleActivity.class);
                    i.putExtra("idVeterinario", items[finalI].getId_veterinario());
                    startActivity(i);
                    return false;
                }
            });
        }
    }
}