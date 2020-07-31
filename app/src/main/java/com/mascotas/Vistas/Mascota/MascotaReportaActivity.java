package com.mascotas.Vistas.Mascota;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mascotas.R;
import com.mascotas.Servicios.ApiRest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MascotaReportaActivity extends AppCompatActivity {

    EditText titulo, descripcion, correo, telefono;
    Button cargarImagen, registrar;
    String datos;
    ImageView contenedorImagen;
    String ruta;
    File newFile;
    String opcionCiudad;
    private String APP_DIRECTORY = "myPictureApp";
    private String MEDIA_DIRECTORY = APP_DIRECTORY + "media";
    private String TEMPORAL_PICTURE_NAME = "temporal.jpg";
    private final int PHOTO_CODE = 100;
    private final int SELECT_PICTURE = 200;
    private Bundle extras;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporta_mascota);
        extras = getIntent().getExtras();
        opcionCiudad = extras.getString("idCiudad");
        contenedorImagen = (ImageView) findViewById(R.id.imagenCargar);
        titulo = (EditText) findViewById(R.id.txtTitulo);
        descripcion = (EditText) findViewById(R.id.txtDescripcion);
        correo = (EditText) findViewById(R.id.txtCorreo);
        telefono = (EditText) findViewById(R.id.txtTelefono);

        cargarImagen = (Button) findViewById(R.id.btnImagenes);
        cargarImagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    final CharSequence[] opciones = {"Tomar foto", "Elegir de galeria", "Cancelar"};
                    final AlertDialog.Builder dialogoImagen = new AlertDialog.Builder(MascotaReportaActivity.this);
                    dialogoImagen.setItems(opciones, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent;
                            switch (opciones[i].toString()) {
                                case "Tomar foto":
//                                    openCamera();
                                    intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, PHOTO_CODE);
                                    dialogInterface.dismiss();
                                    break;
                                case "Elegir de galeria":
                                    Intent intent1 = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    intent1.setType("image/*");
                                    startActivityForResult(intent1.createChooser(intent1, "Selecciona Una Imagen"), SELECT_PICTURE);
                                    dialogInterface.dismiss();
                                    break;
                                default:
                                    dialogInterface.dismiss();
                                    break;
                            }
                        }
                    });
                    dialogoImagen.show();
                } catch (Exception e) {
                    Toast.makeText(MascotaReportaActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

        Button btnReportar = findViewById(R.id.btnReportar);
        btnReportar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ApiRest().reportaMascota(MascotaReportaActivity.this,
                        titulo.getText().toString(),
                        descripcion.getText().toString(),
                        opcionCiudad,
                        correo.getText().toString(),
                        telefono.getText().toString(),
                        getFileDataFromDrawable(bitmap, 100)
                        );
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //SI ES GALERIA
            try {
                switch (requestCode) {
                    case SELECT_PICTURE://GALERIA
                        Uri filepath = data.getData();
//                            bitmap = Metodos.reduceFoto(ClienteAgregarActivity.this, filepath.toString(), 600, 600);
                        bitmap = compressBitmap(reduceFoto(MascotaReportaActivity.this, filepath.toString(), 480, 480), 50);
                        contenedorImagen.setImageBitmap(bitmap);
                        break;
                    case PHOTO_CODE://CAMARA
                        Bundle bundle = data.getExtras();
//                            bitmap = Metodos.reduceFotoBitmap((Bitmap) bundle.getParcelable("data"), 600);
                        bitmap = compressBitmap(reduceFotoBitmap((Bitmap) bundle.getParcelable("data"), 480), 50);
                        contenedorImagen.setImageBitmap(bitmap);
                        break;
                }
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error al cargar la foto", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Bitmap compressBitmap(Bitmap src, int quality) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        src.compress(Bitmap.CompressFormat.JPEG, quality, os);
        byte[] array = os.toByteArray();
        return BitmapFactory.decodeByteArray(array, 0, array.length);
    }

    public Bitmap reduceFoto(Context context, String filepath, int maxAncho, int maxAlto) {
        try {
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(filepath)), null, options);
            options.inSampleSize = (int) Math.max(Math.ceil(options.outWidth / maxAncho),
                    Math.ceil(options.outHeight / maxAlto));
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(context.getContentResolver().openInputStream(Uri.parse(filepath)), null, options);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Bitmap reduceFotoBitmap(Bitmap imagenOriginal, int maxAncho) {
        float proporcion = maxAncho / (float) imagenOriginal.getWidth();
        return Bitmap.createScaledBitmap(imagenOriginal, maxAncho, imagenOriginal.getHeight() * (int) proporcion, false);
    }

    public byte[] getFileDataFromDrawable(Bitmap bitmap, int calidad) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, calidad, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }

    public void repuestaOperacion(JSONObject result) {
        try {
            Log.wtf("USUSARIO ID", result.getString("message"));
            if(result.getBoolean("status")){
                Toast.makeText(this, "Registro exioso", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent();
                intent.putExtra("result",  result.getString("message"));
                setResult(Activity.RESULT_OK, intent);
                finish();
            }else{
                Toast.makeText(getApplicationContext(), result.getString("message"), Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void back(View view) {
        finish();
    }
}