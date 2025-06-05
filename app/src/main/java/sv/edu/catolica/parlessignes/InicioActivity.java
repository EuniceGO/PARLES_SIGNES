package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class InicioActivity extends AppCompatActivity {

    Button btnNiveles,btnCategorias,btnNosotros;
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
        btnNosotros= findViewById(R.id.btnNosotros);

        findViewById(R.id.btn_verMaterial).setOnClickListener(v -> {
            Intent intent = new Intent(this, MaterialApoyoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_ver_niveles).setOnClickListener(v -> {
            Intent intent = new Intent(this, NivelesActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btn_ver_categoria).setOnClickListener(v -> {
            Intent intent = new Intent(this, CategoriasActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.btnNosotros).setOnClickListener(v -> {
            Intent intent = new Intent(this, SobreNosotrosActivity.class);
            startActivity(intent);
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_home) {
                startActivity(new Intent(this, InicioActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_library) {
                startActivity(new Intent(this, MaterialApoyoActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_voice) {
                startActivity(new Intent(this, TraduccionActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_user) {
                startActivity(new Intent(this, NivelesActivity.class));
                finish();
                return true;
            }

            return false;
        });
    }


    public void vistaTraductor(MenuItem item) {
        startActivity(new Intent(this, TraduccionActivity.class));
        finish();
    }


    public void vistaMaterial(MenuItem item) {
        startActivity(new Intent(this, MaterialApoyoActivity.class));
        finish();
    }

    public void vistaUsuario(MenuItem item) {
        startActivity(new Intent(this, PerfilActivity.class));
        finish();
    }

    public void vistaInicio(MenuItem item) {
        startActivity(new Intent(this, InicioActivity.class));
        finish();
    }
    public void vistaNosotros(MenuItem item) {
        startActivity(new Intent(this, SobreNosotrosActivity.class));
        finish();

    }

    public void ver_lecciones(View view) {
        startActivity(new Intent(this, CategoriasActivity.class));
        finish();
    }
}
