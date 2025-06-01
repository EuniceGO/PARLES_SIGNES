package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InicioActivity extends AppCompatActivity {

    Button btnNiveles,btnCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.inicio);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        btnNiveles = findViewById(R.id.btn_ver_niveles);
        btnCategorias = findViewById(R.id.btn_ver_categoria);

        // Click para ver niveles
        btnNiveles.setOnClickListener(view -> {
            Intent intent = new Intent(InicioActivity.this, NivelesActivity.class);
            startActivity(intent);
        });

        // Click para ver categorías
        btnCategorias.setOnClickListener(view -> {
            Intent intent = new Intent(InicioActivity.this, CategoriasActivity.class);
            startActivity(intent);
        });
    }


    public void vistaTraductor(MenuItem item) {
        Intent intent = new Intent(this, TraduccionActivity.class);
        startActivity(intent);
    }


}
