package teamminus1.udirtyrat.controllers;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import teamminus1.udirtyrat.R;
import teamminus1.udirtyrat.models.Model;
import teamminus1.udirtyrat.models.RatSighting;

import java.util.List;

/**
 * Activity controller for viewing the map of rat sightings
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final double LATITUDE = -34;
    private static final double LONGITUDE = 151;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        // Add a marker in Sydney and move the camera

        LatLng sydney = new LatLng(LATITUDE, LONGITUDE);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        List<RatSighting> list_to_map = Model.Instance().getFilteredSightings();
        for(RatSighting ea : list_to_map) {
            LatLng rat_position = new LatLng(ea.getLatitude(), ea.getLongitude());
            String sighting_description = ea.toString();
            googleMap.addMarker(new MarkerOptions().position(rat_position).
                    title(sighting_description));
        }
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
