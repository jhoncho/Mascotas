package com.mascotas.Vistas.Servicio;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ServicioDetalleActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvtitulo, tvdescripcion, tvCorreo, tvTelefono;
    Button llamada;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_detalle);
        extras = getIntent().getExtras();
        new ApiRest().cargarServiciosDetalle(this,extras.getString("idServicio") );
    }

    public void listaLlena(JSONObject data) {
        imageView = findViewById(R.id.imagenServicio);
        tvtitulo= findViewById(R.id.titulo);
        tvdescripcion= findViewById(R.id.descripcion);
        tvCorreo= findViewById(R.id.correo);
        tvTelefono= findViewById(R.id.telefonos);
        llamada= findViewById(R.id.llamar);

        try {
            tvtitulo.setText(data.getString("titulo_servicio"));
            tvdescripcion.setText(data.getString("descripcion_servicio"));
            tvTelefono.setText(data.getString("telefono"));
            tvCorreo.setText(data.getString("correo"));
            Glide.with(this)
                    .load(Constantes.URL_DIR_SERVICIO+data.getString("imagen_servicio"))
                    .asBitmap()
                    .placeholder(null)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            llamada.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+tvTelefono.getText().toString()));
                    startActivity(intent);
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