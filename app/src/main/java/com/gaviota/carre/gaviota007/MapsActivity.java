package com.example.carre.gaviota007;

import android.Manifest;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker marcador;
    private Marker punto;
    double lat = 0.0;
    double lng = 0.0;
    private String tipo="";
    LocationManager locationManager;
    Dialog customDialog = null;
    ImageButton imagen;
    EditText et_titulo,et_fecha,et_hora,et_descripcion;
    Context contexto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        contexto=this;
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        miUbicacion();
        miEvento();

    }

    private void miEvento() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                if (latLng != null){
                    punto = mMap.addMarker(new MarkerOptions().position(latLng)
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                    sacarAlertDialog(latLng.toString());
                }
            }

        });
    }

    private void sacarAlertDialog(final String latLng) {


        // con este tema personalizado evitamos los bordes por defecto
        customDialog = new Dialog(this,R.style.Theme_Dialog_Translucent);
        //deshabilitamos el título por defecto
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //obligamos al usuario a pulsar los botones para cerrarlo
        customDialog.setCancelable(false);
        //establecemos el contenido de nuestro dialog
        customDialog.setContentView(R.layout.alert);

        //seteamos el registro del evento
        imagen = customDialog.findViewById(R.id.btn_imagen);
        et_descripcion=customDialog.findViewById(R.id.et_descripcion);
        et_fecha=customDialog.findViewById(R.id.et_fecha);
        et_hora=customDialog.findViewById(R.id.et_hora);
        et_titulo=customDialog.findViewById(R.id.et_titulo);
        et_fecha.setFocusable(false);
        et_hora.setFocusable(false);
        et_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int dia = c.get(Calendar.DAY_OF_MONTH);
                int mes = c.get(Calendar.MONTH);
                int ano = c.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(contexto, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_fecha.setText(dayOfMonth + "/" + (month+1) + "/" + year);
                    }
                }, dia, mes, ano);
                datePickerDialog.show();
            }
        });
        et_hora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hora = c.get(Calendar.HOUR_OF_DAY);
                int minuto = c.get(Calendar.MINUTE);



                TimePickerDialog timePickerDialog=new TimePickerDialog(contexto, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker time, int hourOfDay, int minute) {

                        et_hora.setText(hourOfDay+":"+minute);
                    }
                },hora,minuto,true);


                timePickerDialog.show();
            }
        });



        View.OnClickListener entrar = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(v);
                openContextMenu(v);
                unregisterForContextMenu(v);
            }
        };
        imagen.setOnClickListener(entrar);

        final Intent I = new Intent(this,Principal.class);

        ((Button) customDialog.findViewById(R.id.btn_aceptar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                //si aceptamos, introduciremos los datos en firebase
                String titulo=et_titulo.getText().toString();
                String fecha=et_fecha.getText().toString();
                String descripcion=et_descripcion.getText().toString();
                String creador="jorge";
                String localizacion=punto.getPosition().toString().replace("lat/lng: (","").replace(")","");
                Log.v("mensaje",localizacion+" "+tipo);
                // tipo --> lo sacamos como string con palabras clave en el onContextItemSelected;
                int participantes=0;

                //verificamos que el usuario seleccione un tipo
                if (tipo.equals("")){
                    return;
                }
                else{
                    FirebaseApp.initializeApp(contexto);

                    
                    //creamos el objeto evento
                    Evento e=new Evento(localizacion,fecha,titulo,descripcion,creador,participantes,tipo);
                    Punto p=new Punto(creador,localizacion);
                    DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference("eventos");
                    DatabaseReference bbdd2 = FirebaseDatabase.getInstance().getReference("puntos");
                    DatabaseReference bbdd3 = FirebaseDatabase.getInstance().getReference("usuarios").child("jorge").child("eventos_creados");

                    //generamos una clave para ese evento pero la guardamos para la tabla usuario(evento creados)
                    String clave=bbdd.push().getKey();

                    //insertamos el evento
                    bbdd.child(clave).setValue(e);
                    bbdd2.child(clave).setValue(p);
                    bbdd3.child(clave).setValue(true);


                    customDialog.dismiss();
                    startActivity(I);
                    finish();
                }





            }
        });

        ((Button) customDialog.findViewById(R.id.btn_cancelar)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view)
            {
                punto.remove();
                customDialog.dismiss();

            }
        });
        customDialog.show();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.example_menu, menu);
        MenuItem.OnMenuItemClickListener oyente=new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Log.v("clicado ",""+item.getItemId());
                onContextItemSelected(item);
                return false;
            }
        };
            for (int i=0; i<menu.size(); i++)
            {
                menu.getItem(i).setOnMenuItemClickListener(oyente);
            }

    }


    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.option_1:
                tipo="limpiar";
                imagen.setImageResource(R.drawable.papelera);
                return true;
            case R.id.option_2:
                tipo="chorizos";
                imagen.setImageResource(R.drawable.ladron);
                return true;
            case R.id.option_3:
                tipo="medusas";
                imagen.setImageResource(R.drawable.medusa);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void agregegarMarcador(double lat, double lng) {
        LatLng coordenadas = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(coordenadas, 16);
        if (marcador != null) {
            marcador.remove();
            marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi ubicacion")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            mMap.animateCamera(miUbicacion);
        } else {
            marcador = mMap.addMarker(new MarkerOptions().position(coordenadas).title("Mi ubicacion")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
            mMap.animateCamera(miUbicacion);
        }
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            //location.getLatitude()
            //location.getLongitude()
            lat = location.getLatitude();//40.4165000;
            lng =  location.getLongitude();//-3.7025600;
            agregegarMarcador(lat,lng);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            if (location!=null) {
                actualizarUbicacion(location);
                Log.v("ubicacion", location.getLatitude()+", "+location.getLongitude());
                locationManager.removeUpdates(this);
            }
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        /*Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if(location!=null) {
            actualizarUbicacion(location);
            Log.v("ubicacion", location.getLatitude()+", "+location.getLongitude());
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 0, locationListener);
        }*/
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, locationListener);
    }

    private void insertarEventos(){

    }
}
