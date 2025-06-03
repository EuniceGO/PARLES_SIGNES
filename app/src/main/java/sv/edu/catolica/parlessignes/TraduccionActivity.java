package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class TraduccionActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.traductor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationT);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_inicio) {
                startActivity(new Intent(this, InicioActivity.class));
                finish();
                return true;

            } else if (id == R.id.nav_voz) {
                startActivity(new Intent(this, TraduccionActivity.class));
                finish();
                return true;
            } else if (id == R.id.nav_escrito) {
                startActivity(new Intent(this, Traduccion_escrita.class));
                finish();
                return true;
            }

            return false;
        });

    }

    public void vistaTraductorV(MenuItem item) {
        startActivity(new Intent(this, TraduccionActivity.class));
    }

    public void vistaTraductor_Escrito(MenuItem item) {
        startActivity(new Intent(this, Traduccion_escrita.class));
    }

    public void vistaInicioV(MenuItem item) {
        startActivity(new Intent(this, InicioActivity.class));
    }

    public void speak(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Empieza a hablar");

        try {
            startActivityForResult(intent, 100);
        } catch (Exception e) {
            textView.setText(R.string.Error_traductor);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                textView.setText(result.get(0));
            }
        }
    }
}
