package sv.edu.catolica.parlessignes;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AbecedarioActivity extends AppCompatActivity {

    private LinearLayout letrasContainer;
    private int filaCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.abecedario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        letrasContainer = findViewById(R.id.letrasContainer);

        for (char letra = 'a'; letra <= 'z'; letra++) {
            agregarLetra(letra);

            if (letra == 'n') {
                agregarLetra('ñ');
            }
        }

    }
    private void agregarLetra(char letra) {
        // contenedor horizontal
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setPadding(16, 16, 16, 16);

        if (filaCount % 2 == 0) {
            fila.setGravity(Gravity.START);
        } else {
            fila.setGravity(Gravity.END);
        }

        filaCount++;

        TextView textoLetra = new TextView(this);
        textoLetra.setText(String.valueOf(Character.toUpperCase(letra)));
        textoLetra.setTextSize(24);
        textoLetra.setTextColor(getResources().getColor(android.R.color.black));
        textoLetra.setPadding(0, 0, 16, 0);

        // Obtener imagen según letra
        String nombreRecurso = (letra == 'ñ') ? "letra_ene" : "letra_" + letra;
        int resId = getResources().getIdentifier(nombreRecurso, "drawable", getPackageName());

        ImageView imagenLetra = new ImageView(this);
        imagenLetra.setImageResource(resId);
        imagenLetra.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imagenLetra.setContentDescription("Letra " + Character.toUpperCase(letra));

        fila.addView(textoLetra);
        fila.addView(imagenLetra);
        letrasContainer.addView(fila);
    }

    public void Retroceder(View view) {
        Intent intent = new Intent(this, MaterialApoyoActivity.class);
        startActivity(intent);
        finish();
    }
}

