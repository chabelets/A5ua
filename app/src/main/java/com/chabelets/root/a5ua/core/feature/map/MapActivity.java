package com.chabelets.root.a5ua.core.feature.map;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.chabelets.root.a5ua.R;
import com.chabelets.root.a5ua.core.feature.next.PlaceFragment;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback,
        PlaceSelectionListener, GoogleMap.OnMarkerClickListener {

    public static final String PLACE_NAME = "PLACE_NAME";
    private GoogleMap map;
    private String placeName;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initView();
    }

    @Override
    public void onPlaceSelected(Place place) {
        LatLng latLng = place.getLatLng();
        setMarker(latLng);
        placeName = place.getName().toString();
        sharedPreferences.edit().putString("Lat",String.valueOf(latLng.latitude)).apply();
        sharedPreferences.edit().putString("Lng",String.valueOf(latLng.longitude)).apply();
    }

    @Override
    public void onError(Status status) {
        Log.d("Maps", "An error occurred: " + status);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setOnMarkerClickListener(this);
        sharedPreferences = this.getSharedPreferences("LatLng",MODE_PRIVATE);
        if((sharedPreferences.contains("Lat")) && (sharedPreferences.contains("Lng"))) {
            String lat = sharedPreferences.getString("Lat", "");
            String lng = sharedPreferences.getString("Lng", "");
            LatLng l = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
            setMarker(l);
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Bundle bundle = new Bundle();
        bundle.putString(PLACE_NAME, placeName);
        PlaceFragment placeFragment = new PlaceFragment();
        placeFragment.setArguments(bundle);
        placeFragment.setCancelable(false);
        placeFragment.show(getSupportFragmentManager(), "placeDialog");
        return false;
    }

    private void setMarker(LatLng latLng) {
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 18));
        MarkerOptions markerOptions;
        LatLng myCoordinate = new LatLng(latLng.latitude, latLng.longitude);
        markerOptions = new MarkerOptions()
                .position(myCoordinate);
        map.addMarker(markerOptions);
    }

    private void initView() {
        PlaceAutocompleteFragment placeAutoComplete = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete);
        placeAutoComplete.setOnPlaceSelectedListener(this);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(MapActivity.this);
        }
    }
}
