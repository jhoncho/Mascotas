package com.mascotas.Adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
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
import com.mascotas.Modelo.VeterinariaModel;
import com.mascotas.R;
import com.mascotas.Vistas.Servicio.ServicioDetalleActivity;

public class VeterinariosAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView tvtitulo, tvdescripcion,tvId;
    VeterinariaModel[] item;

    public VeterinariosAdapter(Context applicationContext, VeterinariaModel[] item)
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
        ViewGroup viewGroup=(ViewGroup)layoutInflater.inflate(R.layout.fragment_veterinarias_item, null);
        imageView = viewGroup.findViewById(R.id.imagenVeterinarias);
        tvtitulo=(TextView) viewGroup.findViewById(R.id.titulo);
        tvdescripcion=(TextView) viewGroup.findViewById(R.id.descripcion);
        tvId=(TextView) viewGroup.findViewById(R.id.txtId);
        Glide.with(ctx)
                .load(Constantes.URL_DIR_VETERINARIO+item[position].getImagen_veterinario())
                .asBitmap()
                .placeholder(null)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        tvtitulo.setText(item[position].getTitulo_veterinario());
        tvdescripcion.setText(item[position].getDescripcion_veterinario());
        tvId.setText(item[position].getId_veterinario());
        return viewGroup;
    }
}
