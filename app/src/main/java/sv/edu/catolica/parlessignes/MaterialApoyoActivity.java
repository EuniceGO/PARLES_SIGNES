package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MaterialApoyoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_material_apoyo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        findViewById(R.id.areaDoc).setOnClickListener(v -> {
            Intent intent = new Intent(this, NivelesActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.areaVideo).setOnClickListener(v -> {
            Intent intent = new Intent(this, VideoActivity.class);
            startActivity(intent);
        });

        findViewById(R.id.areaImg).setOnClickListener(v -> {
            Intent intent = new Intent(this, AbecedarioActivity.class);
            startActivity(intent);
        });

    }
}
