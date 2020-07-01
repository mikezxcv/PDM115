package sv.edu.ues.fia.eisi.pdm115.mapa.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import sv.edu.ues.fia.eisi.pdm115.R;

import static android.media.CamcorderProfile.get;


public class MapFragment extends Fragment implements OnMapReadyCallback , GoogleMap.OnMarkerDragListener, View.OnClickListener {
    private View rootView;
    private MapView mapView;
    private GoogleMap gMap;

    private Geocoder geocoder;
    private List<Address> addresses;
    private MarkerOptions marker;
    private FloatingActionButton fab;
    public MapFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView= inflater.inflate(R.layout.fragment_map, container, false);
        fab= (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mapView=(MapView) rootView.findViewById(R.id.mapFragment);
        if(mapView!=null){
            mapView.onCreate(null);
            mapView.onResume();
            mapView.getMapAsync( this);

        }


    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap= googleMap;
        LatLng place= new LatLng(15.284185,12.027068);
        CameraUpdate zoom= CameraUpdateFactory.zoomTo(15);

        marker= new MarkerOptions();
        marker.position(place);
        marker.title("Mi marcador");
        marker.draggable(true);
        marker.snippet("Caja donde se modificaran los datos");
        marker.icon(BitmapDescriptorFactory.fromResource(android.R.drawable.star_on));
        gMap.addMarker(marker);
        //gMap.addMarker(new MarkerOptions().position(place).title("Marker In Sevilla").draggable(true));
        gMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        gMap.animateCamera(zoom);
        gMap.setOnMarkerDragListener(this);
        geocoder= new Geocoder(getContext(), Locale.getDefault());
    }

    private void checkGpsIsEnabled(){
        try {
            int gpsSignal= Settings.Secure.getInt(getActivity().getContentResolver(),Settings.Secure.LOCATION_MODE);
            if(gpsSignal==0){
                //el gps esta desactivado
                this.showInfoAlert();
            }
        }catch (Settings.SettingNotFoundException e){
            e.printStackTrace();
        }
    }

    private void showInfoAlert(){
        new AlertDialog.Builder(getContext())
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
        this.checkGpsIsEnabled();
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
        marker.setSnippet(addres);
        marker.showInfoWindow();
    }
}
