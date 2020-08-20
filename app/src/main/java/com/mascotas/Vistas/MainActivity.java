package com.mascotas.Vistas;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.mascotas.R;
import com.mascotas.Vistas.Ciudad.CiudadFragment;
import com.mascotas.Vistas.Soporte.AyudaFragment;
import com.mascotas.Vistas.Servicio.ServiciosFragment;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

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

        //PARA QUE LOS INCONOS DEL MENU ESTEN A COLORES
        navigationView.setItemIconTintList(null);
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
                if (validaPermisoGps(MainActivity.this)) {
                    fragment = new CiudadFragment(2);
                    getSupportActionBar().setTitle(menuItem.getTitle());
                }
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
        if(fragment!=null) {
            //hace el acambio de fragmento
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.home_content, fragment)
                    .addToBackStack(null)
                    .commit();
        }
        //cierra el menu lateral
        drawableLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100://GPS
                Log.wtf("grantResults: ", grantResults.length+" - "+grantResults[0]+" - "+grantResults[1]);
                // ya tiene permisos?
                if (grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Fragment fragment = new CiudadFragment(2);
                    getSupportActionBar().setTitle("Mapa");
                    //hace el acambio de fragmento
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.home_content, fragment)
                            .addToBackStack(null)
                            .commit();
                    //cierra el menu lateral
                    drawableLayout.closeDrawer(GravityCompat.START);
                } else {
                    darPermisosManual(this);
                }
                break;
        }
    }

    //    PERMISOS
    public boolean validaPermisoGps(final Activity context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        //YA TIENE LOS PERMISOS?
        if ((context.checkSelfPermission(ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED)
                && (context.checkSelfPermission(ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED)) {
            return true;
        }
        //SI NO TIENE PIDE PERMISOS
        if ((context.shouldShowRequestPermissionRationale(ACCESS_FINE_LOCATION))
                || (context.shouldShowRequestPermissionRationale(ACCESS_COARSE_LOCATION))) {

            android.app.AlertDialog.Builder dialo = new android.app.AlertDialog.Builder(context);
            dialo.setCancelable(false);
            dialo.setTitle("Permisos desactivados");
            dialo.setMessage("Debe aceptar los permisos para el correto funcionamiento de la aplicacion");

            dialo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    context.requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 100);
                }
            });

            dialo.show();
        } else {
            final android.app.AlertDialog.Builder dialo = new android.app.AlertDialog.Builder(context);
            dialo.setCancelable(false);
            dialo.setTitle("Bienvenido a Maskotas ");
            dialo.setMessage(context.getString(R.string.app_name) + " necesita un permiso para que la aplicacion funcione correctamente.");

            dialo.setPositiveButton("Dar permiso", new DialogInterface.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    context.requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 100);
                    dialogInterface.dismiss();
                }
            });
            dialo.show();
            ;
        }
        return false;
    }

    public void darPermisosManual(final Context context) {
        final android.app.AlertDialog.Builder dialo = new android.app.AlertDialog.Builder(context);
        dialo.setTitle("Permisos desactivados");
        dialo.setMessage("Desea configurar los permisos de forma manual?");

        dialo.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent();
                intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                Uri uri = Uri.fromParts("package", context.getPackageName(), null);
                intent.setData(uri);
                context.startActivity(intent);
                dialogInterface.dismiss();
            }
        });
        dialo.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Permisos no aceptados", Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        dialo.show();
    }
}