package com.mascotas.Vistas.Veterinaria;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.mascotas.Aplication.Constantes;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

import org.json.JSONException;
import org.json.JSONObject;

public class VeterinariaDetalleActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvtitulo, tvdescripcion,tvCorreo,tvTelefono;
    Button llamada;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinaria_detalle);
        extras = getIntent().getExtras();
        new ApiRest().cargarVeterinariaDetalle(this,extras.getString("idVeterinario") );
    }

    public void listaLlena(JSONObject data) {
        imageView = findViewById(R.id.imagenVeterinaria);
        tvtitulo= findViewById(R.id.titulo);
        tvdescripcion= findViewById(R.id.descripcion);
        tvCorreo= findViewById(R.id.correo);
        tvTelefono= findViewById(R.id.telefonos);
        llamada= findViewById(R.id.llamar);

        try {
            tvtitulo.setText(data.getString("titulo_veterinario"));
            tvdescripcion.setText(data.getString("descripcion_veterinario"));
            Glide.with(this)
                    .load(Constantes.URL_DIR_VETERINARIO+data.getString("imagen_veterinario"))
                    .asBitmap()
                    .placeholder(null)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            tvCorreo.setText(data.getString("correo_veterinario"));
            tvTelefono.setText(data.getString("telefono_veterinario"));
            llamada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:"+tvTelefono.getText().toString()));
                    startActivity(i);
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void backView(View view) {
        finish();
    }
}