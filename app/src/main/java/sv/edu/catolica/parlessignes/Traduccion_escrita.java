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

public class Traduccion_escrita extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_traduccion_escrita);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;


        });

    }
    public void vistaTraductorV(MenuItem item) {
        startActivity(new Intent(this, TraduccionActivity.class));
        finish();
    }

    public void vistaTraductor_Escrito(MenuItem item) {
        startActivity(new Intent(this, Traduccion_escrita.class));
        finish();
    }

    public void vistaInicioV(MenuItem item) {
        startActivity(new Intent(this, InicioActivity.class));
        finish();
    }
}