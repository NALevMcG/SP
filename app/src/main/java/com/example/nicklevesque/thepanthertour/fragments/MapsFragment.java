package com.example.nicklevesque.thepanthertour.fragments;

//Imports
import android.Manifest;
import android.app.Fragment;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicklevesque.thepanthertour.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

import static com.example.nicklevesque.thepanthertour.R.id.toolbar;

/**
 * Created by nicklevesque 3/14/17
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener {

    //global variables
    //Map object, we use later in this class to populate a map
    GoogleMap mMap;
    //GoogleApiClient object  used to integrate google play services, to successfully use my map.
    GoogleApiClient mGoogleApiClient;
    //Location object used to keep track of your location
    Location mLastLocation;
    //LocationRequest object used to to request the location of the user
    LocationRequest mLocationRequest;

    SupportMapFragment mapFragment;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    /* Called to do initial creation of the fragment. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);


    }

    //creates and returns the view hierarchy associated with the fragment. returns the rootView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_maps, container, false);

    }

    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        MapFragment fragment = (MapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        fragment.getMapAsync(this);

    }

    /* Method used to build our Google client and connect it */
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }


    /* Method used to check if the user has granted permission for the app to use their location*/

    public boolean checkLocationPermission(){
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Asking user if explanation is needed
            if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

                //Prompt the user once explanation has been shown
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    /* method called after user grants location permissions,
    in this case, we set our location to enabled if the user agrees */
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted. Do the
                    // contacts-related task you need to do.
                    if (ContextCompat.checkSelfPermission(getActivity(),
                            Manifest.permission.ACCESS_FINE_LOCATION)
                            == PackageManager.PERMISSION_GRANTED) {

                        if (mGoogleApiClient == null) {
                            buildGoogleApiClient();
                        }
                        mMap.setMyLocationEnabled(true);
                    }

                } else {

                    // Permission denied, Disable the functionality that depends on this permission.
                    Toast.makeText(getActivity(), "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }

            // other 'case' lines to check for other permissions this app might request.
            // You can add here other case statements according to your requirement.
        }
    }

    /*After calling connect(), this method will be invoked asynchronously when the connect request has successfully completed.
    Here we request the location of the user, set how fast the location updates, and how accurate we want the location to be.
    Lastly, we check if Location permissions have been granted(I know this is a bit redundant in many methods but android forces you to do it now)
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    /*void method that is called when location is changed. When it is changed,
    We move the camera to the marker */
    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;

        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        //stop location updates
        if (mGoogleApiClient != null) {
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, this);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    /* Method used to create your map when it is ready, here we add markers, set our map type, etc.. */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        //Checks current sdk on device to see if location permission is even needed (not needed in lower sdk versions)
        // if it is, check if location permissions have been granted, if not run checkLocationPermission();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                //Location Permission already granted
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            } else {
                //Request Location Permission
                checkLocationPermission();
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }


        //Scale images used for the map
        //restaurant image
        BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.mipmap.restaurant_image);
        Bitmap a = bitmapdraw.getBitmap();
        Bitmap smalldiningMarker = Bitmap.createScaledBitmap(a, 50, 50, false);

        //housing image
        BitmapDrawable bitmap2draw = (BitmapDrawable)getResources().getDrawable(R.mipmap.house);
        Bitmap b = bitmap2draw.getBitmap();
        Bitmap smallreshallMarker = Bitmap.createScaledBitmap(b, 50, 50, false);

        //academic image
        BitmapDrawable bitmap3draw = (BitmapDrawable)getResources().getDrawable(R.mipmap.academic_picture);
        Bitmap c = bitmap3draw.getBitmap();
        Bitmap smallacademicMarker = Bitmap.createScaledBitmap(c, 50, 50, false);



        //List of all residential hall markers markers
        List<Marker> resHalls = new ArrayList<Marker>();
        Marker smith = mMap.addMarker(new MarkerOptions().position(new LatLng(43.760895, -71.689809)).title("Smith").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker grafton = mMap.addMarker(new MarkerOptions().position(new LatLng(43.760945, -71.688570)).title("Grafton").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker langdonWoods = mMap.addMarker(new MarkerOptions().position(new LatLng(43.765137, -71.689239)).title("Langdon Woods").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker pemi = mMap.addMarker(new MarkerOptions().position(new LatLng(43.757005, -71.691628)).title("Pemi").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker belKnap = mMap.addMarker(new MarkerOptions().position(new LatLng(43.757762, -71.692124)).title("Belknap").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker maryLyon = mMap.addMarker(new MarkerOptions().position(new LatLng(43.758774, -71.690345)).title("Mary Lyon").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker blair = mMap.addMarker(new MarkerOptions().position(new LatLng(43.758649, -71.691145)).title("Blair").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker stdAps = mMap.addMarker(new MarkerOptions().position(new LatLng(43.763599, -71.688877)).title("Student Apartments").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));
        Marker nontradstdAps = mMap.addMarker(new MarkerOptions().position(new LatLng(43.762337, -71.688838)).title("Non-Traditional Student Apartments").snippet("Residential Hall").icon(BitmapDescriptorFactory.fromBitmap(smallreshallMarker)));


        resHalls.add(smith);
        resHalls.add(grafton);
        resHalls.add(langdonWoods);
        resHalls.add(pemi);
        resHalls.add(belKnap);
        resHalls.add(maryLyon);
        resHalls.add(blair);
        resHalls.add(smith);
        resHalls.add(stdAps);
        resHalls.add(nontradstdAps);


        //List of all dining Markers on campus
        List<Marker> dining = new ArrayList<Marker>();
        Marker prospect = mMap.addMarker(new MarkerOptions().position(new LatLng(43.760684, -71.689338)).title("Prospect").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));
        Marker unionGrill = mMap.addMarker(new MarkerOptions().position(new LatLng(43.760181, -71.690109)).title("Union Grill").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));
        Marker commonsCafe = mMap.addMarker(new MarkerOptions().position(new LatLng(43.757634, -71.691078)).title("Commons Cafe").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));
        Marker woodsCafe = mMap.addMarker(new MarkerOptions().position(new LatLng(43.764638, -71.688980)).title("Woods Cafe").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));

        dining.add(prospect);
        dining.add(unionGrill);
        dining.add(commonsCafe);
        dining.add(woodsCafe);


        //List of all Academic markers on campus
        List<Marker> academicBuildings = new ArrayList<Marker>();
        Marker rounds = mMap.addMarker(new MarkerOptions().position(new LatLng(43.759533, -71.688379)).title("Rounds").snippet("Academic Hall").icon(BitmapDescriptorFactory.fromBitmap(smallacademicMarker)));
        Marker Hyde = mMap.addMarker(new MarkerOptions().position(new LatLng(43.761506, -71.690463)).title("Hyde").snippet("Academic Hall").icon(BitmapDescriptorFactory.fromBitmap(smallacademicMarker)));
        Marker memorial = mMap.addMarker(new MarkerOptions().position(new LatLng(43.759440, -71.689639)).title("Memorial").snippet("Academic Hall").icon(BitmapDescriptorFactory.fromBitmap(smallacademicMarker)));
        Marker boyd = mMap.addMarker(new MarkerOptions().position(new LatLng(43.756938, -71.689997)).title("Boyd").snippet("Academic Hall").icon(BitmapDescriptorFactory.fromBitmap(smallacademicMarker)));
        Marker dandm = mMap.addMarker(new MarkerOptions().position(new LatLng(43.760564, -71.687599)).title("Draper & Maynard").snippet("Academic Hall").icon(BitmapDescriptorFactory.fromBitmap(smallacademicMarker)));

        academicBuildings.add(rounds);
        academicBuildings.add(Hyde);
        academicBuildings.add(memorial);
        academicBuildings.add(boyd);
        academicBuildings.add(dandm);


    }

}
