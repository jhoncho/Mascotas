package com.mascotas.Vistas.Veterinaria;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mascotas.Modelo.VeterinarioLocalizacionModel;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

public class VeterinariaMapaFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String opcionCiudad = "1";
    double latUser =0;
    double lngUser = 0;
    AlertDialog alert = null;
    private Marker marcador;


    public VeterinariaMapaFragment(String opcionCiudad) {
        //la ciudad no se esta utilizando ojo!!!
        this.opcionCiudad = opcionCiudad;
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
//        ubicacionOrigen = new LatLng(-17.0568696, -64.9912286);
        new ApiRest().cargarLocalizacion(VeterinariaMapaFragment.this);
    }

    public void listaLlena(final VeterinarioLocalizacionModel[] items) {
//        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(ubicacionOrigen, 5), 5000, null);

        for (int i = 0; i < items.length; i++) {

            MarkerOptions marketItem=new MarkerOptions()
                    .snippet(items[i].getTitulo_veterinario())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marcador))
                    .position(new LatLng(Double.parseDouble(items[i].getLatitud()), Double.parseDouble(items[i].getLongitud())))
                    .title(items[i].getTitulo_veterinario());
            Marker markerMapa =mMap.addMarker(marketItem);
            markerMapa.setTag(i);
            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    int position=(int)marker.getTag();
                    if(position>=0){
                        Intent i = new Intent(getContext(), VeterinariaDetalleActivity.class);
                        i.putExtra("idVeterinario", items[position].getId_veterinario());
                        startActivity(i);
                    }
                    return false;
                }
            });
        }

        if (mMap != null) {
            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {
                @Override
                public void onMyLocationChange(Location location) {
                    Log.wtf("GPS", latUser+":"+lngUser);
                    if (latUser!=0){
                        Log.wtf("GPS", " no actualizar");
                    }else {
                        Log.wtf("GPS", "si actualiza");
                        latUser=location.getLatitude();
                        lngUser=location.getLongitude();
                        Marker markerMapa= mMap.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())).title("Mi posicion Actual"));
                        CameraPosition cameraPosition = CameraPosition.builder()
                                .target(new LatLng(location.getLatitude(), location.getLongitude()))
                                .zoom(15)
                                .build();
                        markerMapa.setTag(-1);
                        mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    }
                }
            });
        }
    }
}