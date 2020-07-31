package com.mascotas.Servicios;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.mascotas.Aplication.Constantes;
import com.mascotas.Complementos.VolleyRequestPostMultipart;
import com.mascotas.Complementos.VolleySingleton;
import com.mascotas.Modelo.AdoptaVidaModel;
import com.mascotas.Modelo.CiudadModel;
import com.mascotas.Modelo.FundacionesModel;
import com.mascotas.Modelo.MascotasEncuentraModel;
import com.mascotas.Modelo.ServiciosModel;
import com.mascotas.Modelo.VeterinariaModel;
import com.mascotas.Modelo.VeterinarioLocalizacionModel;
import com.mascotas.Vistas.AdoptaVida.AdoptaVidaDetalleActivity;
import com.mascotas.Vistas.AdoptaVida.AdoptaVidaFragment;
import com.mascotas.Vistas.Ciudad.CiudadFragment;
import com.mascotas.Vistas.Fundaciones.FundacionesDetalleActivity;
import com.mascotas.Vistas.Fundaciones.FundacionesFragment;
import com.mascotas.Vistas.Mascota.MascotaEncuentraDetalleActivity;
import com.mascotas.Vistas.Mascota.MascotaReportaActivity;
import com.mascotas.Vistas.Mascota.MascotasEncuentraFragment;
import com.mascotas.Vistas.Veterinaria.VeterinariaMapaFragment;
import com.mascotas.Vistas.Servicio.ServicioDetalleActivity;
import com.mascotas.Vistas.Servicio.ServiciosFragment;
import com.mascotas.Vistas.Veterinaria.VeterinariaDetalleActivity;
import com.mascotas.Vistas.Veterinaria.VeterinariaFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class ApiRest {
    private Gson gson = new Gson();

    public void cargarServicios(final ServiciosFragment activity, final String ciudad) {
        Log.wtf("::::::::DATA", "Ciudad: "+ciudad);
        final ProgressDialog progresBar = new ProgressDialog(activity.getContext());
        progresBar.setMessage("Cargando servicios...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        Log.i("===== URL", Constantes.HOST + Constantes.URL_GET_SERVICIOS+"?opcionCiudad="+ciudad);
        VolleySingleton.getInstance(activity.getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_GET_SERVICIOS+"?opcionCiudad="+ciudad,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        final JSONArray contenido2 = response.getJSONArray("data");
                                        ServiciosModel[] items = gson.fromJson(contenido2.toString(), ServiciosModel[].class);
                                        if (items.length > 0) {
                                            activity.listaLlena(items);
                                        } else {
                                            Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarServiciosDetalle(final ServicioDetalleActivity activity, final String ciudad) {
        Log.wtf("::::::::DATA", "idServicio: "+ciudad);
        final ProgressDialog progresBar = new ProgressDialog(activity);
        progresBar.setMessage("Cargando detalle...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        Log.i("===== URL", Constantes.HOST + Constantes.URL_DETALLE_SERVICIO+"?servicios="+ciudad);
        VolleySingleton.getInstance(activity).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_DETALLE_SERVICIO+"?servicios="+ciudad,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        activity.listaLlena(response.getJSONObject("data"));
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity, response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarCiudades(final CiudadFragment activity) {
        Log.wtf("::::::::DATA", "Ciudad: ");
        final ProgressDialog progresBar = new ProgressDialog(activity.getContext());
        progresBar.setMessage("Cargando ciudades...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        Log.i("===== URL", Constantes.HOST + Constantes.URL_GET_CIUDADES);
        VolleySingleton.getInstance(activity.getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_GET_CIUDADES,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        final JSONArray contenido2 = response.getJSONArray("data");
                                        CiudadModel[] items = gson.fromJson(contenido2.toString(), CiudadModel[].class);
                                        Log.wtf("TAM", items.length+"");
                                        if (items.length > 0) {
                                            activity.listaLlena(items);
                                        } else {
                                            Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarVeterinaria(final VeterinariaFragment activity, String opcionCiudad) {
        Log.wtf("::::::::DATA", "VETERINARAIA: "+Constantes.HOST + Constantes.URL_GET_VETERINARIAS+"?opcionCiudad="+opcionCiudad);
        final ProgressDialog progresBar = new ProgressDialog(activity.getContext());
        progresBar.setMessage("Cargando veterinarias...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        VolleySingleton.getInstance(activity.getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_GET_VETERINARIAS+"?opcionCiudad="+opcionCiudad,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        final JSONArray contenido2 = response.getJSONArray("data");
                                        VeterinariaModel[] items = gson.fromJson(contenido2.toString(), VeterinariaModel[].class);
                                        if (items.length > 0) {
                                            activity.listaLlena(items);
                                        } else {
                                            Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarVeterinariaDetalle(final VeterinariaDetalleActivity activity, String idVeterinario) {
        Log.wtf("::::::::DATA", "idVeterinario: "+idVeterinario);
        final ProgressDialog progresBar = new ProgressDialog(activity);
        progresBar.setMessage("Cargando detalle...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        Log.i("===== URL", Constantes.HOST + Constantes.URL_DETALLE_VETERINARIO+"?veterinario="+idVeterinario);
        VolleySingleton.getInstance(activity).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_DETALLE_VETERINARIO+"?veterinario="+idVeterinario,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        activity.listaLlena(response.getJSONObject("data"));
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity, response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarLocalizacion(final VeterinariaMapaFragment activity) {
        Log.wtf("::::::::DATA", "VETERINARAIA: "+Constantes.HOST + Constantes.URL_GET_LOCALIZACION);
        final ProgressDialog progresBar = new ProgressDialog(activity.getContext());
        progresBar.setMessage("Cargando veterinarias...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        VolleySingleton.getInstance(activity.getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_GET_LOCALIZACION,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        final JSONArray contenido2 = response.getJSONArray("data");
                                        VeterinarioLocalizacionModel[] items = gson.fromJson(contenido2.toString(), VeterinarioLocalizacionModel[].class);
                                        if (items.length > 0) {
                                            activity.listaLlena(items);
                                        } else {
                                            Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarEncuentraMascota(final MascotasEncuentraFragment activity, String opcionCiudad) {
        Log.wtf("::::::::DATA", "VETERINARAIA: "+Constantes.HOST + Constantes.URL_GET_ENCUENTRA_MASCOTAS);
        final ProgressDialog progresBar = new ProgressDialog(activity.getContext());
        progresBar.setMessage("Cargando mascotas...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        VolleySingleton.getInstance(activity.getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_GET_ENCUENTRA_MASCOTAS+"?opcionCiudad="+opcionCiudad,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        final JSONArray contenido2 = response.getJSONArray("data");
                                        MascotasEncuentraModel[] items = gson.fromJson(contenido2.toString(), MascotasEncuentraModel[].class);
                                        if (items.length > 0) {
                                            activity.listaLlena(items);
                                        } else {
                                            Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void reportaMascota(final MascotaReportaActivity activity, final String titulo, final String descripcion, final String idciudad,
                               final String correo, final String telefono, final byte[] imagen) {
        final ProgressDialog progresBar = new ProgressDialog(activity);
        progresBar.setMessage("Reportando mascota...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        VolleyRequestPostMultipart volleyRequestPostMultipart = new VolleyRequestPostMultipart(
                Request.Method.POST,
                Constantes.HOST+Constantes.URL_SET_REPORTA_MASCOTA,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        progresBar.dismiss();
                        try {
                            JSONObject result = new JSONObject(new String(response.data));
                            activity.repuestaOperacion(result);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progresBar.dismiss();
                        Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("titulo", titulo);
                params.put("descripcion", descripcion);
                params.put("idciudad", idciudad);
                params.put("correo", correo);
                params.put("telefono", telefono);
                return params;
            }

            @Override
            protected Map<String, DataPart> getByteData() {
                Map<String, DataPart> params = new HashMap<>();
                long imagename = System.currentTimeMillis();
                params.put("imagen", new DataPart(imagename + ".jpg", imagen));
                return params;
            }
        };
        Volley.newRequestQueue(activity).add(volleyRequestPostMultipart);
    }

    public void cargarMascotaEncuentraDetalle(final MascotaEncuentraDetalleActivity activity, String Mascotadetalle) {
        Log.wtf("::::::::DATA", "idVeterinario: "+Mascotadetalle);
        final ProgressDialog progresBar = new ProgressDialog(activity);
        progresBar.setMessage("Cargando detalle...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        Log.i("===== URL", Constantes.HOST + Constantes.URL_DETALLE_REPORTA_MASCOTA+"?encuentraMascota="+Mascotadetalle);
        VolleySingleton.getInstance(activity).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_DETALLE_REPORTA_MASCOTA+"?encuentraMascota="+Mascotadetalle,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        activity.listaLlena(response.getJSONObject("data"));
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity, response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarAdoptaVida(final AdoptaVidaFragment activity, String opcionCiudad) {
        Log.wtf("::::::::DATA", "VETERINARAIA: "+Constantes.HOST + Constantes.URL_GET_ADOPTA_VIDA);
        final ProgressDialog progresBar = new ProgressDialog(activity.getContext());
        progresBar.setMessage("Cargando mascotas...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        VolleySingleton.getInstance(activity.getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_GET_ADOPTA_VIDA+"?opcionCiudad="+opcionCiudad,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        final JSONArray contenido2 = response.getJSONArray("data");
                                        AdoptaVidaModel[] items = gson.fromJson(contenido2.toString(), AdoptaVidaModel[].class);
                                        if (items.length > 0) {
                                            activity.listaLlena(items);
                                        } else {
                                            Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarAdoptaVidaDetalle(final AdoptaVidaDetalleActivity activity, String adoptaVida) {
        Log.wtf("::::::::DATA", "idVeterinario: "+adoptaVida);
        final ProgressDialog progresBar = new ProgressDialog(activity);
        progresBar.setMessage("Cargando detalle...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        Log.i("===== URL", Constantes.HOST + Constantes.URL_DETALLE_ADOPTA_VIDA+"?adoptaVida="+adoptaVida);
        VolleySingleton.getInstance(activity).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_DETALLE_ADOPTA_VIDA+"?adoptaVida="+adoptaVida,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        activity.listaLlena(response.getJSONObject("data"));
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity, response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarFundaciones(final FundacionesFragment activity, String opcionCiudad) {
        Log.wtf("::::::::DATA", "VETERINARAIA: "+Constantes.HOST + Constantes.URL_GET_FUNDACIONES);
        final ProgressDialog progresBar = new ProgressDialog(activity.getContext());
        progresBar.setMessage("Cargando mascotas...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        VolleySingleton.getInstance(activity.getActivity()).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_GET_FUNDACIONES+"?opcionCiudad="+opcionCiudad,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        final JSONArray contenido2 = response.getJSONArray("data");
                                        FundacionesModel[] items = gson.fromJson(contenido2.toString(), FundacionesModel[].class);
                                        if (items.length > 0) {
                                            activity.listaLlena(items);
                                        } else {
                                            Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                        }
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity.getContext(), response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity.getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity.getContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }

    public void cargarFundacionDetalle(final FundacionesDetalleActivity activity, String fundaciones) {
        Log.wtf("::::::::DATA", "idVeterinario: "+fundaciones);
        final ProgressDialog progresBar = new ProgressDialog(activity);
        progresBar.setMessage("Cargando detalle...");
        progresBar.setCancelable(true);
        progresBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progresBar.show();

        Log.i("===== URL", Constantes.HOST + Constantes.URL_DETALLE_FUNDACIONES+"?fundaciones="+fundaciones);
        VolleySingleton.getInstance(activity).addToRequestQueue(
                new JsonObjectRequest(
                        Request.Method.GET,
                        Constantes.HOST + Constantes.URL_DETALLE_FUNDACIONES+"?fundaciones="+fundaciones,
                        (String) null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                progresBar.dismiss();
                                try {
                                    Log.wtf("DATA", response.getString("message"));
                                    if (response.getBoolean("status")) {
                                        activity.listaLlena(response.getJSONObject("data"));
                                    } else {//usuario incorrecto
                                        Toast.makeText(activity, response.getString("message"), Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    Log.e("ERROR DE PROCESAMIENTO", e.getMessage());
                                    Toast.makeText(activity, e.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                progresBar.dismiss();
                                Log.e("ERROR DE RED", error.getMessage());
                                Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                )
        );
    }
}
