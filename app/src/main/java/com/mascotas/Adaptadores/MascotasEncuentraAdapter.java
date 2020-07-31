package com.mascotas.Adaptadores;

import android.content.Context;
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
import com.mascotas.Modelo.MascotasEncuentraModel;
import com.mascotas.R;

public class MascotasEncuentraAdapter extends BaseAdapter
{
    Context ctx;
    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView tvtitulo, tvdescripcion,tvId;
    MascotasEncuentraModel[] item;

    public MascotasEncuentraAdapter(Context applicationContext, MascotasEncuentraModel[] item)
    {
        this.ctx=applicationContext;
        this.item=item;
        layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        ViewGroup viewGroup=(ViewGroup)layoutInflater.inflate(R.layout.fragment_encuentra_macota_item, null);
        imageView = viewGroup.findViewById(R.id.imagenEncuentraMascota);
        tvtitulo=(TextView) viewGroup.findViewById(R.id.titulo);
        tvdescripcion=(TextView) viewGroup.findViewById(R.id.descripcion);
        tvId=(TextView) viewGroup.findViewById(R.id.txtId);
        Glide.with(ctx)
                .load(Constantes.URL_DIR_ENCUENTRA_MASCOTA+item[position].getImagen_encuentra_mascota())
                .asBitmap()
                .placeholder(null)
                .skipMemoryCache(false)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);
        tvtitulo.setText(item[position].getTitulo_encuentra_mascota());
        tvdescripcion.setText(item[position].getDescripcion_encuentra_mascota());
        tvId.setText(item[position].getId_encuentra_mascota());
        return viewGroup;
    }
}
