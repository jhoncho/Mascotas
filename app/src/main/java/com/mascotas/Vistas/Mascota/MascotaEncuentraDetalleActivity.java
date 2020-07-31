package com.mascotas.Vistas.Mascota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.snowdream.android.widget.SmartImageView;
import com.mascotas.Aplication.Constantes;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

import org.json.JSONException;
import org.json.JSONObject;

public class MascotaEncuentraDetalleActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvtitulo, tvdescripcion,tvCorreo,tvTelefono;
    Button llamada;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_encuentra_detalle);
        extras = getIntent().getExtras();
        new ApiRest().cargarMascotaEncuentraDetalle(this,extras.getString("encuentraMascota") );
    }

    public void backView(View view) {
        finish();
    }

    public void listaLlena(JSONObject data) {
        imageView = findViewById(R.id.imagenEncuentraMascota);
        tvtitulo= findViewById(R.id.titulo);
        tvdescripcion= findViewById(R.id.descripcion);
        tvCorreo= findViewById(R.id.correo);
        tvTelefono= findViewById(R.id.telefonos);
        llamada= findViewById(R.id.llamar);

        try {
            tvtitulo.setText(data.getString("titulo_encuentra_mascota"));
            tvdescripcion.setText(data.getString("descripcion_encuentra_mascota"));
            Glide.with(this)
                    .load(Constantes.URL_DIR_ENCUENTRA_MASCOTA+data.getString("imagen_encuentra_mascota"))
                    .asBitmap()
                    .placeholder(null)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            tvCorreo.setText(data.getString("correo_encuentra_mascota"));
            tvTelefono.setText(data.getString("telefono_encuentra_mascota"));
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
}