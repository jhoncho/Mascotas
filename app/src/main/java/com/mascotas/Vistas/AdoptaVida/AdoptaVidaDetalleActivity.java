package com.mascotas.Vistas.AdoptaVida;

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
import com.mascotas.Aplication.Constantes;
import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

import org.json.JSONException;
import org.json.JSONObject;

public class AdoptaVidaDetalleActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvtitulo, tvdescripcion,tvCorreo,tvTelefono;
    Button llamada;
    private Bundle extras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adopta_vida_detalle);
        extras = getIntent().getExtras();
        new ApiRest().cargarAdoptaVidaDetalle(this,extras.getString("adoptaVida") );
    }

    public void listaLlena(JSONObject data) {
        imageView = findViewById(R.id.imagenAdoptaVida);
        tvtitulo= findViewById(R.id.titulo);
        tvdescripcion= findViewById(R.id.descripcion);
        tvCorreo= findViewById(R.id.correo);
        tvTelefono= findViewById(R.id.telefonos);
        llamada= findViewById(R.id.llamar);

        try {
            tvtitulo.setText(data.getString("titulo_adopta_vida"));
            tvdescripcion.setText(data.getString("descripcion_adopta_vida"));
            Glide.with(this)
                    .load(Constantes.URL_DIR_ADOPTA_VIDA+data.getString("imagen_adopta_vida"))
                    .asBitmap()
                    .placeholder(null)
                    .skipMemoryCache(false)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(imageView);
            tvCorreo.setText(data.getString("correo_adopta_vida"));
            tvTelefono.setText(data.getString("telefono_adopta_vida"));
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