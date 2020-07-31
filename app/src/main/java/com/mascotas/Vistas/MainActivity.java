package com.mascotas.Vistas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.mascotas.R;
import com.mascotas.Vistas.Ciudad.CiudadFragment;
import com.mascotas.Vistas.Soporte.AyudaFragment;
import com.mascotas.Vistas.Servicio.ServiciosFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawableLayout;
    private int _CIUDAD_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        drawableLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawableLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawableLayout.addDrawerListener(toggle);
        toggle.syncState();

        //seccion de menu
        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        //cargar el primer fragmento
        getSupportActionBar().setTitle("Servicios");
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_content, new ServiciosFragment(String.valueOf(_CIUDAD_ID)))
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (drawableLayout.isDrawerOpen(GravityCompat.START)) {
            drawableLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.nav_inicio:
                fragment = new ServiciosFragment(String.valueOf(_CIUDAD_ID));
                getSupportActionBar().setTitle(menuItem.getTitle());
                break;
            case R.id.nav_veterinarias:
                fragment = new CiudadFragment(1);
                getSupportActionBar().setTitle(menuItem.getTitle());
                break;
            case R.id.nav_mapa:
                fragment = new CiudadFragment(2);
                getSupportActionBar().setTitle(menuItem.getTitle());
                break;
            case R.id.nav_adopta_vida:
                fragment = new CiudadFragment(3);
                getSupportActionBar().setTitle(menuItem.getTitle());
                break;
            case R.id.nav_encuentra_mascota:
                fragment = new CiudadFragment(4);
                getSupportActionBar().setTitle(menuItem.getTitle());
                break;
            case R.id.nav_fundaciones:
                fragment = new CiudadFragment(5);
                getSupportActionBar().setTitle(menuItem.getTitle());
                break;
            case R.id.nav_help:
                fragment = new AyudaFragment();
                getSupportActionBar().setTitle(menuItem.getTitle());
                break;
        }
        //hace el acambio de fragmento
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.home_content, fragment)
                .addToBackStack(null)
                .commit();
        //cierra el menu lateral
        drawableLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}