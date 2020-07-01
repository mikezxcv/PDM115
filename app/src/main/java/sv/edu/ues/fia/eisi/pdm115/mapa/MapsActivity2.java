package sv.edu.ues.fia.eisi.pdm115.mapa;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;

import sv.edu.ues.fia.eisi.pdm115.R;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerDragListener,View.OnClickListener, LocationListener {
    private View rootView;
    private MapView mapView;
    private GoogleMap gMap;
    private MarkerOptions marker;
    private MarkerOptions markerD;
    private MarkerOptions markerC;
    private MarkerOptions markerB;
    private MarkerOptions industrial;
    private Geocoder geocoder;
    private LocationManager locationManager;
    private List<Address> addresses;

    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap= googleMap;
        locationManager= (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
        //lat y long de edificios
        LatLng edificioD =new LatLng(13.720787,-89.200604);
        LatLng edificioC= new LatLng(13.720500,-89.200701);
        LatLng edificioB= new LatLng(13.720250,-89.200912);
        LatLng edificioIndustrial= new LatLng(13.721287,-89.200207);
        LatLng elSalvador= new LatLng(13.7177,  -89.2037);

        //Crear Marcadores
        markerD= new MarkerOptions();
        markerD.position(edificioD);
        markerD.title("Edificio D");
        markerD.snippet("UES edificio D, SALONES DE D-11 A D-43");

        markerC= new MarkerOptions();
        markerC.position(edificioC);
        markerC.title("Edificio C, SALONES C-11 A C-43");
        markerC.snippet("UES edificio C, SALONES C-11 A C-43");

        markerB= new MarkerOptions();
        markerB.position(edificioB);
        markerB.title("Edificio B");
        markerB.snippet("UES edificio B, SALONES DE B-11 A B-43");

        industrial= new MarkerOptions();
        industrial.position(edificioIndustrial);
        industrial.title("Edificio Sistemas e Industrial");
        industrial.snippet("Labs de Computacion,Ing. Industrial e Ing. Sistemas");
        //agregar Marcadroes a gMap

        gMap.getUiSettings().setZoomControlsEnabled(true);
        gMap.addMarker(markerD);
        gMap.addMarker(markerC);
        gMap.addMarker(markerB);
        gMap.addMarker(industrial);

        //permisos de localizacion
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(elSalvador,15));
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},2);
            return;
        }
        gMap.setMyLocationEnabled(true);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000,0,this);
    }


    @Override
    public void onMarkerDragStart(Marker marker) {

    }

    @Override
    public void onMarkerDrag(Marker marker) {

    }

    @Override
    public void onMarkerDragEnd(Marker marker) {
        double latitude= marker.getPosition().latitude;
        double longitud= marker.getPosition().longitude;

        try {
            addresses= geocoder.getFromLocation(latitude,longitud,1);
        }catch (IOException e){
            e.printStackTrace();
        }
        String addres= addresses.get(0).getAddressLine(0);
        String city= addresses.get(0).getLocality();
        String state= addresses.get(0).getAdminArea();
        String country= addresses.get(0).getCountryName();
        String postalCode= addresses.get(0).getPostalCode();
        marker.setSnippet("prueba");
        marker.showInfoWindow();
    }
    private void checkGpsIsEnabled(){
        try {
            int gpsSignal= Settings.Secure.getInt(getApplicationContext().getContentResolver(),Settings.Secure.LOCATION_MODE);
            if(gpsSignal==0){
                //el gps esta desactivado
                this.showInfoAlert();
            }
        }catch (Settings.SettingNotFoundException e){
            e.printStackTrace();
        }
    }
    private void showInfoAlert(){
        new AlertDialog.Builder(getApplicationContext())
                .setTitle("GPS signal")
                .setMessage("No tienes Activa La señal GPS , ¿quieres activarla ahora?")
                .setPositiveButton("ACTIVAR", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent= new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("CANCELAR",null)
                .show();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onLocationChanged(Location location) {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},2);
            return;
        }
        gMap.setMyLocationEnabled(true);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
