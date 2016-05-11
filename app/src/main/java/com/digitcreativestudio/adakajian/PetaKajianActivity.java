package com.digitcreativestudio.adakajian;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.digitcreativestudio.adakajian.utility.AlertDialogManager;
import com.digitcreativestudio.adakajian.utility.MyLocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class PetaKajianActivity extends AppCompatActivity implements GoogleMap.OnMarkerClickListener,GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;

    protected LocationManager locationManager;

    boolean isNetworkEnabled;

    Location location;

    MyLocationListener mylistener;

    String radiusStr,longitudeStr, latitudeStr;

    AlertDialogManager alert = new AlertDialogManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peta_kajian);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Peta Kajian Terdekat");

        setUpMapIfNeeded();
        setUpLocation();
        cekKoneksi();

        mMap.getUiSettings().setMapToolbarEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        mMap.setMyLocationEnabled(true);
        mMap.setOnMarkerClickListener(this);
        mMap.setOnInfoWindowClickListener(this);


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
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                //setUpMap();
            }
        }
    }

    private void setUpLocation(){
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        mylistener = new MyLocationListener(PetaKajianActivity.this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 0, mylistener);
        mylistener.onLocationChanged(location);

    }

    private void cekKoneksi(){
        if(isNetworkEnabled==true){
            if((String.valueOf(mylistener.getLatitude())!=null) || (String.valueOf(mylistener.getLongitude())!=null)){
                createCircle("20000",mylistener.getLatitude(),mylistener.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mylistener.getLatitude(),mylistener.getLongitude()), 10));
                createMarker("Kajian 1","Alamat 1",mylistener.getLatitude(),mylistener.getLongitude());


            }else{
                alert.showAlertDialog(PetaKajianActivity.this, "Gagal", "Tidak dapat menemukan lokasi !", false);
            }
        }else{
            showSettingsAlert();
        }
    }


    private void showSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(
                PetaKajianActivity.this);
        alertDialog.setTitle("Location setting");

        alertDialog
                .setMessage("Lokasi belum aktif, silahkan aktifkan terlebih dulu.");

        alertDialog.setPositiveButton("Setting",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        PetaKajianActivity.this.startActivity(intent);
                    }
                });

        alertDialog.setNegativeButton("Batal",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();
    }

    private void createCircle(String str,double lat, double log){
        CircleOptions circleOptions = new CircleOptions().center(new LatLng(lat,log)).radius(Double.valueOf(str)).fillColor(0x44ff0000).strokeColor(0xffff0000).strokeWidth(8);
        mMap.addCircle(circleOptions);
    }
    private void createMarker(String name,String alamat, double lat, double log){
        mMap.addMarker(new MarkerOptions()
                .position(new LatLng(lat,log))
                .snippet(alamat).title(name)).showInfoWindow();
    }


    @Override
    public boolean onMarkerClick(final Marker marker) {
        marker.showInfoWindow();
        return false;
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Toast.makeText(PetaKajianActivity.this, "Clicked", Toast.LENGTH_SHORT).show();

    }
}
