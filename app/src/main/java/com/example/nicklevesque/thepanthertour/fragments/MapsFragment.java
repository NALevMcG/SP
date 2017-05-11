package com.example.nicklevesque.thepanthertour.fragments;

//Imports
import android.Manifest;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nicklevesque.thepanthertour.DataDirectionsParser;
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
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.example.nicklevesque.thepanthertour.R.id.dining_halls;
import static com.example.nicklevesque.thepanthertour.R.id.imageView3;
import static com.example.nicklevesque.thepanthertour.R.id.toolbar;
import static com.example.nicklevesque.thepanthertour.R.id.tv_snippet;
import static com.example.nicklevesque.thepanthertour.R.id.tv_title;

/**
 * Created by nicklevesque 3/14/17
 */

public class MapsFragment extends Fragment implements OnMapReadyCallback,
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        LocationListener, GoogleMap.OnInfoWindowClickListener {

    //global variables
    //Map object, we use later in this class to populate a map
    GoogleMap mMap;
    //GoogleApiClient object  used to integrate google play services, to successfully use my map.
    GoogleApiClient mGoogleApiClient;
    //Location object used to keep track of your location
    Location mLastLocation;
    //LocationRequest object used to to request the location of the user
    LocationRequest mLocationRequest;

    List<Marker> resHalls = new ArrayList<Marker>();
    List<Marker> dining = new ArrayList<Marker>();
    List<Marker> academicBuildings = new ArrayList<Marker>();

    double currentLatitude;
    double currentLongitude;

    private TextView Distance;
    private TextView Duration;

    private Button button;
    private Button button2;

    Polyline polyline;



    SupportMapFragment mapFragment;

    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

    /* Called to do initial creation of the fragment. */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapFragment = SupportMapFragment.newInstance();
        mapFragment.getMapAsync(this);
        setHasOptionsMenu(true);


    }

    //creates and returns the view hierarchy associated with the fragment. returns the rootView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_maps, container, false);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Map");

        Distance = (TextView) rootView.findViewById(R.id.distance);
        Duration = (TextView) rootView.findViewById(R.id.duration);

        button = (Button) rootView.findViewById(R.id.routeButton);
        button.setVisibility(View.INVISIBLE);
        button2 = (Button) rootView.findViewById(R.id.clearButton);
        button2.setVisibility(View.INVISIBLE);


        // Inflate the layout for this fragment
        return rootView;

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

    /*
    Method that clears previous and creates new option menu
     */
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.settings_layout, menu);

        super.onCreateOptionsMenu(menu, inflater);

    }

    /*
    method that handles my options menu functionality
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }

        /*
        switch statement used determine which button was clicked and to handle it accordingly
         */
        switch (id) {
            case R.id.academic_halls:
                if (item.isChecked()) {
                    for (int i = 0; i < academicBuildings.size() + 3; i++) {
                        academicBuildings.get(i).setVisible(true);
                    }
                    Toast.makeText(getActivity(), "Academic buildings visable", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    for (int i = 0; i < academicBuildings.size(); i++) {
                        academicBuildings.get(i).setVisible(false);
                    }
                    Toast.makeText(getActivity(), "Academic buildings non visible", Toast.LENGTH_SHORT).show();
                    break;
                }

            case R.id.res_halls:
                if (item.isChecked()) {
                    for (int i = 0; i < resHalls.size(); i++) {
                        resHalls.get(i).setVisible(true);
                    }
                    Toast.makeText(getActivity(), "Residential buildings visible", Toast.LENGTH_SHORT).show();
                    break;

                } else {
                    for (int i = 0; i < resHalls.size(); i++) {
                        resHalls.get(i).setVisible(false);
                    }
                    Toast.makeText(getActivity(), "Residential buildings non visible", Toast.LENGTH_SHORT).show();
                    break;
                }
            case R.id.dining_halls:
                if (item.isChecked()) {
                    for (int i = 0; i < dining.size(); i++) {
                        dining.get(i).setVisible(true);
                    }
                    Toast.makeText(getActivity(), "Dining buildings visible", Toast.LENGTH_SHORT).show();
                    break;
                } else {
                    for (int i = 0; i < dining.size(); i++) {
                        dining.get(i).setVisible(false);
                    }
                    Toast.makeText(getActivity(), "Dining buildings non visible", Toast.LENGTH_SHORT).show();
                    break;
                }


            case R.id.rounds_hall:
                for (int i = 0; i < academicBuildings.size() + 3; i++) {
                    if(academicBuildings.get(i).getTitle().equals("Rounds")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(academicBuildings.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.memorial_hall:
                for (int i = 0; i < academicBuildings.size() + 3; i++) {
                    if(academicBuildings.get(i).getTitle().equals("Memorial")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(academicBuildings.get(i).getPosition()));
                        return true;
                    }
                }
            case R.id.d_m:
                for (int i = 0; i < academicBuildings.size() + 3; i++) {
                    if(academicBuildings.get(i).getTitle().equals("Draper & Maynard")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(academicBuildings.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.hyde_hall:
                for (int i = 0; i < academicBuildings.size() + 3; i++) {
                    if(academicBuildings.get(i).getTitle().equals("Hyde")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(academicBuildings.get(i).getPosition()));
                        return true;
                    }
                }
            case R.id.boyd_hall:
                for (int i = 0; i < academicBuildings.size() + 3; i++) {
                    if(academicBuildings.get(i).getTitle().equals("Boyd")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(academicBuildings.get(i).getPosition()));
                        return true;
                    }
                }


            case R.id.student_aps:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Student Apartments")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.non_trad_aps:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Non-Traditional Student Apartments")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.smith:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Smith")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.grafton:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Grafton")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.belknap:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Belknap")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.mary_lyon:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Mary Lyon")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.blair:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Blair")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.pemi:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Pemi")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }
                break;

            case R.id.langdonwoods:
                for (int i = 0; i < resHalls.size()+1; i++) {
                    if(resHalls.get(i).getTitle().equals("Langdon Woods")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(resHalls.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.commons_cafe:
                for (int i = 0; i < dining.size()+1; i++) {
                    if(dining.get(i).getTitle().equals("Commons Cafe")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(dining.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.prospect_dininghall:
                for (int i = 0; i < dining.size()+1; i++) {
                    if(dining.get(i).getTitle().equals("Prospect")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(dining.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.woods_cafe:
                for (int i = 0; i < dining.size()+1; i++) {
                    if(dining.get(i).getTitle().equals("Woods Cafe")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(dining.get(i).getPosition()));
                        return true;
                    }
                }

            case R.id.union_grille:
                for (int i = 0; i < dining.size()+1; i++) {
                    if(dining.get(i).getTitle().equals("Union Grill")){
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(dining.get(i).getPosition()));
                        return true;
                    }
                }



        }
        return false;

    }
    /*After calling connect(), this method will be invoked asynchronously when the connect request has successfully completed.
    Here we request the location of the user, set how fast the location updates, and how accurate we want the location to be.
    Lastly, we check if Location permissions have been granted(I know this is a bit redundant in many methods but android forces you to do it now)
     */

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(17));

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        //check to make sure we have permission to get user location
        if (ContextCompat.checkSelfPermission(getActivity(),
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {

            //if permission granted check to see if we have a location, if not request one, else assign our variables to our long lat
            if (location == null) {
                LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
            } else {
                //If everything went fine lets get latitude and longitude
                currentLatitude = location.getLatitude();
                currentLongitude = location.getLongitude();
                Log.e("latitude", "" + currentLatitude);
                Log.e("longitude", "" + currentLongitude);

            }
        }
    }

    private String getUrl(LatLng origin, LatLng dest) {

        // Origin of route
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        // Destination of route
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        // Sensor enabled
        String sensor = "sensor=false";

        // travel mode
        String mode = "mode=walking";

        // Building the parameters to the web service
        String parameters = str_origin + "&" + str_dest + "&" + sensor + "&" + mode;

        // Output format
        String output = "json";

        // Building the url to the web service
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    /*
     * A method to download json data from url
     */
    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            // Creating an http connection to communicate with url
            urlConnection = (HttpURLConnection) url.openConnection();

            // Connecting to url
            urlConnection.connect();

            // Reading data from url
            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();
            Log.d("downloadUrl", data.toString());
            br.close();

        } catch (Exception e) {
            Log.d("Exception", e.toString());
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    /**
     * inner class used to fetch data from the url passed to it
     */
    private class FetchUrl extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            // For storing data from web service
            String data = "";

            try {
                // Fetching the data from web service
                data = downloadUrl(url[0]);
                Log.d("Background Task data", data.toString());
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            MapsFragment.ParserTask parserTask = new MapsFragment.ParserTask();

            // Invokes the thread for parsing the JSON data
            parserTask.execute(result);

        }
    }

    /*
     * A class to parse the Google Places in JSON format
     */
    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        // Parsing the data in non-ui thread
        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                Log.d("ParserTask",jsonData[0].toString());
                DataDirectionsParser parser = new DataDirectionsParser();
                Log.d("ParserTask", parser.toString());

                // Starts parsing data
                routes = parser.parse(jObject);
                Log.d("ParserTask","Executing routes");
                Log.d("ParserTask",routes.toString());

            } catch (Exception e) {
                Log.d("ParserTask",e.toString());
                e.printStackTrace();
            }
            return routes;
        }

        // Executes in UI thread, after the parsing process
        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points;
            String distance = "Blah";
            String duration = "Blah";

            PolylineOptions lineOptions = null;

            // Traversing through all the routes
            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<>();
                lineOptions = new PolylineOptions();

                // Fetching i-th route
                List<HashMap<String, String>> path = result.get(i);

                // Fetching all the points in i-th route
                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);

                    if (j == 0) {    // Get distance from the list
                        distance = point.get("distance");
                        continue;
                    } else if (j == 1) { // Get duration from the list
                        duration = point.get("duration");
                        continue;
                    }

                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                // Adding all the points in the route to LineOptions
                lineOptions.addAll(points);
                lineOptions.width(10);
                lineOptions.color(Color.RED);

                Log.d("onPostExecute","onPostExecute lineoptions decoded");

            }
            Distance.setText("Distance: " + distance);
            Duration.setText("Duration: " + duration);


            // Drawing polyline in the Google Map for the i-th route

            if(lineOptions != null) {
                polyline = mMap.addPolyline(lineOptions);
            }
            else {
                Log.d("onPostExecute","without Polylines drawn");
            }
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

        mMap.getUiSettings().setMapToolbarEnabled(false);


        mMap.setOnInfoWindowClickListener(this);

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

        mMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {
                View v = getActivity().getLayoutInflater().inflate(R.layout.info_window, null);
                TextView title = (TextView)v.findViewById(tv_title);
                TextView snippet = (TextView)v.findViewById(tv_snippet);
                ImageView image = (ImageView)v.findViewById(imageView3);

                if(marker.getSnippet().equals("Residential Hall"))
                    image.setImageResource(R.mipmap.house);
                else if(marker.getSnippet().equals("Academic Hall"))
                    image.setImageResource(R.mipmap.academic_picture);
                else
                    image.setImageResource(R.mipmap.restaurant_image);


                title.setText(marker.getTitle());
                snippet.setText(marker.getSnippet());

                return v;
            }
        });


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
        Marker prospect = mMap.addMarker(new MarkerOptions().position(new LatLng(43.760684, -71.689338)).title("Prospect").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));
        Marker unionGrill = mMap.addMarker(new MarkerOptions().position(new LatLng(43.760181, -71.690109)).title("Union Grill").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));
        Marker commonsCafe = mMap.addMarker(new MarkerOptions().position(new LatLng(43.757634, -71.691078)).title("Commons Cafe").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));
        Marker woodsCafe = mMap.addMarker(new MarkerOptions().position(new LatLng(43.764638, -71.688980)).title("Woods Cafe").snippet("Dining Area").icon(BitmapDescriptorFactory.fromBitmap(smalldiningMarker)));

        dining.add(prospect);
        dining.add(unionGrill);
        dining.add(commonsCafe);
        dining.add(woodsCafe);


        //List of all Academic markers on campus
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


    public void onInfoWindowClick(final Marker marker) {
        button.setVisibility(View.VISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.VISIBLE);

                for (int i = 0; i < academicBuildings.size(); i++) {
                    academicBuildings.get(i).setVisible(false);
                }

                for (int i = 0; i < resHalls.size(); i++) {
                    resHalls.get(i).setVisible(false);
                }

                for (int i = 0; i < dining.size(); i++) {
                    dining.get(i).setVisible(false);
                }

                marker.setVisible(true);

                LatLng latlng = marker.getPosition();
                double latitude = latlng.latitude;
                double longitude = latlng.longitude;

                LatLng origin = new LatLng(currentLatitude, currentLongitude);
                LatLng dest = new LatLng(latitude,longitude);

                // Getting URL to the Google Directions API
                String url = getUrl(origin, dest);
                FetchUrl FetchUrl = new FetchUrl();

                // Start downloading json data from Google Directions API
                FetchUrl.execute(url);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button2.setVisibility(View.INVISIBLE);
                polyline.remove();
                for (int i = 0; i < academicBuildings.size(); i++) {
                    academicBuildings.get(i).setVisible(true);
                }

                for (int i = 0; i < resHalls.size(); i++) {
                    resHalls.get(i).setVisible(true);
                }

                for (int i = 0; i < dining.size(); i++) {
                    dining.get(i).setVisible(true);
                }

            }
        });

    }

}
