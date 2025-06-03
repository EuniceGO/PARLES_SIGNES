package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CategoriasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.categorias);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void vistaTraductor(MenuItem item) {
        startActivity(new Intent(this, TraduccionActivity.class));
        finish();
    }


    public void vistaMaterial(MenuItem item) {
        startActivity(new Intent(this, PerfilActivity.class));
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
}