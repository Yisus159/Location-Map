package example.jesus.googlemapsclaveapi;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

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
        mMap = googleMap;
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        //tomo las coordenadas del usuario y hago zoom
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();

        Location location = locationManager.getLastKnownLocation(locationManager
                .getBestProvider(criteria, false));
        double userlatitude = location.getLatitude();
        double userlongitud = location.getLongitude();
        LatLng userlatLng = new LatLng(userlatitude,userlongitud);
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(userlatLng, 17);
        mMap.addMarker(new MarkerOptions()
                .title("Usted esta aquí")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                .position(userlatLng));
        mMap.animateCamera(cameraUpdate);


        //agrego marcador en la surcolombiana
        LatLng colombia = new LatLng(2.942027367315385,-75.29822364449501);
        mMap.addMarker(new MarkerOptions()
                .position(colombia)
                .title("Universidad Surcolombiana"));



        //agrego marcador shiroku
        LatLng shiroku = new LatLng(2.947936, -75.298055);
        mMap.addMarker(new MarkerOptions()
                .position(shiroku)
                .title("Shiroku-Maid Cafe")
                .snippet("Cafeteria - Ocio Dirección: cra 1° # 34 - 2"));


        //agrego marcador exito
        LatLng exito = new LatLng(2.949790, -75.288390);
        mMap.addMarker(new MarkerOptions()
                .position(exito)
                .title("Sampedro Plaza")
                .snippet("Centro Comercial Dirección: Cl. 39 #7-26"));


        //agrego marcador hotel dujos
        LatLng dujos = new LatLng(2.9418935,-75.2985061);
        mMap.addMarker(new MarkerOptions()
                .position(dujos)
                .title("Hoster'ia Los Dujos")
                .snippet("Hotel 3 estrellas Dirección: Av Pastrana 1-06 "));


        //agrego marcador Davivienda
        LatLng dav = new LatLng(2.9269572,-75.2877391);
        mMap.addMarker(new MarkerOptions()
                .position(dav)
                .title("Banco Davivienda 7a")
                .snippet("Banco - Economia Dirección: Cl. 7 #5-57 "));

        //agrego marcador Interalemana
        LatLng inter = new LatLng(2.9242733,-75.2889594);
        mMap.addMarker(new MarkerOptions()
                .position(inter)
                .title("INTERALEMANA")
                .snippet("Ferreteria  Dirección: Cl. 5 #4-54 "));


    }
}
