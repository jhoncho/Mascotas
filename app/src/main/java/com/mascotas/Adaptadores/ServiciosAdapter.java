package com.mascotas.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.snowdream.android.widget.SmartImageView;
import com.mascotas.Aplication.Constantes;
import com.mascotas.Modelo.ServiciosModel;
import com.mascotas.R;
import com.mascotas.Vistas.Servicio.ServicioDetalleActivity;

public class ServiciosAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView tvtitulo, tvdescripcion,tvId;
    ServiciosModel[] item;

    public ServiciosAdapter(Context applicationContext, ServiciosModel[] item)
    {
        try {
            this.ctx=applicationContext;
            this.item=item;
            layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return item.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewGroup viewGroup=(ViewGroup)layoutInflater.inflate(R.layout.activity_principal_item, null);
        imageView = viewGroup.findViewById(R.id.imagenServicio);
        tvtitulo=(TextView) viewGroup.findViewById(R.id.titulo);
        tvdescripcion=(TextView) viewGroup.findViewById(R.id.descripcion);
        tvId=(TextView) viewGroup.findViewById(R.id.txtId);
        Glide.with(ctx)
                .load(Constantes.URL_DIR_SERVICIO+item[position].getImagen_servicio())
                .asBitmap()
                .placeholder(null)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        tvtitulo.setText(item[position].getTitulo_servicio());
        tvdescripcion.setText(item[position].getDescripcion_servicio());
        tvId.setText(item[position].getId_servicio());

        viewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ctx, ServicioDetalleActivity.class);
                i.putExtra("idServicio", item[position].getId_servicio());
                ctx.startActivity(i);
            }
        });
        return viewGroup;
    }
}
