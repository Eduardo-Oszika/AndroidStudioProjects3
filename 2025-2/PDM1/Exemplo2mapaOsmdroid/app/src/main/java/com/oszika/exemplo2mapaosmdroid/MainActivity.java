package com.oszika.exemplo2mapaosmdroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MainActivity extends AppCompatActivity implements ActivityCompat.OnRequestPermissionsResultCallback{
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView map;
    private Marker marker;
    private TextView textView;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Configuration.getInstance().setUserAgentValue(getPackageName());
        map = findViewById(R.id.mapView);
        textView = findViewById(R.id.textViewCoordenadas);

        solicitarPermissao(new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
        });
    }

    private void solicitarPermissao(String[] permissions) {

        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) !=
                    android.content.pm.PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,permissions, REQUEST_PERMISSIONS_REQUEST_CODE);
                return;
            }
        }
        iniciarLocalizacao();
    }

    private void iniciarLocalizacao() {
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        if (location != null) {
            inicializaMapa(location);
        }
    }

    private void inicializaMapa(Location location) {
        if (map !=null) {
            map.setMultiTouchControls(true);

            GeoPoint ponto = new GeoPoint(location.getLatitude(), location.getLongitude());

            map.getController().setZoom(15.0);
            map.getController().setCenter(ponto);

            marker = new Marker(map);
            marker.setPosition(ponto);
            marker.setTitle("Minha Localização");
            map.getOverlays().add(marker);
            textView.setText("Coordenadas: \nLatitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude());

            String coordenadas = "Latitude: " + location.getLatitude() + "\nLongitude: " + location.getLongitude();
            textView.setText(coordenadas);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (REQUEST_PERMISSIONS_REQUEST_CODE == requestCode) {
            boolean permetido = true;
            for (int result : grantResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    permetido = false;
                    break;
                }
            }
            if (permetido) {
                iniciarLocalizacao();
            } else {
                Snackbar.make(map,"Permissões negadas. Não é possível obter a localização.", Snackbar.LENGTH_INDEFINITE).show();
            }
        }
    }
}