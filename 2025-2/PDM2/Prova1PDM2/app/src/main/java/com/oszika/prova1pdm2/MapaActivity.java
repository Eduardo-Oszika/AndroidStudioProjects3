package com.oszika.prova1pdm2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

public class MapaActivity extends AppCompatActivity {
    private MapView map;
    private TextView textViewCoordenadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mapa);

        Configuration.getInstance().setUserAgentValue(getPackageName());
        map = findViewById(R.id.mapa);
        textViewCoordenadas = findViewById(R.id.coordenadas);
        inicializarMapa();

    }
//    -10.1987632,-48.3129125

    private void inicializarMapa() {
        if (map != null) {
            map.setMultiTouchControls(true);
            GeoPoint palmas = new GeoPoint(-10.189292, -48.364082);

            map.getController().setZoom(15.0);
            map.getController().setCenter(palmas); // Coordenadas de Madrid

            Marker marker = new Marker(map);
            marker.setPosition(palmas);
            marker.setTitle("Praia Graciosa");
            marker.setIcon(ContextCompat.getDrawable(this, R.drawable.guarda));
            map.getOverlays().add(marker);
            marker.setDraggable(true);

            marker.setOnMarkerDragListener(new Marker.OnMarkerDragListener() {
                @Override
                public void onMarkerDragStart(Marker marker) {
                    // Ação ao iniciar o arrasto (opcional)
                }

                @Override
                public void onMarkerDrag(Marker marker) {
                    // Ação durante o arrasto (opcional)
                }

                @Override
                public void onMarkerDragEnd(Marker marker) {
                    GeoPoint position = marker.getPosition();
                    String coordenadas = "Lat: " + position.getLatitude() + "\nLong: " + position.getLongitude();
                    textViewCoordenadas.setText(coordenadas);
                }
            });
        }
    }
}