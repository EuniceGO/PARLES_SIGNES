package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NumerosActivity extends AppCompatActivity {

    private LinearLayout numerosContainer;
    private int filaCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.numeros);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        numerosContainer = findViewById(R.id.numerosContainer);

        for (int i = 0; i <= 9; i++) {
            agregarNumero(i);
        }
    }

    private void agregarNumero(int numero) {
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setPadding(16, 16, 16, 16);

        if (filaCount % 2 == 0) {
            fila.setGravity(Gravity.START);
        } else {
            fila.setGravity(Gravity.END);
        }

        filaCount++;

        TextView textoNumero = new TextView(this);
        textoNumero.setText(String.valueOf(numero));
        textoNumero.setTextSize(24);
        textoNumero.setTextColor(getResources().getColor(android.R.color.black));
        textoNumero.setPadding(0, 0, 16, 0);

        String nombreRecurso = "letra_" + numero;
        int resId = getResources().getIdentifier(nombreRecurso, "drawable", getPackageName());

        ImageView imagenNumero = new ImageView(this);
        imagenNumero.setImageResource(resId);
        imagenNumero.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imagenNumero.setContentDescription("Número " + numero);

        fila.addView(textoNumero);
        fila.addView(imagenNumero);
        numerosContainer.addView(fila);
    }

    public void Retroceder(View view) {
        Intent intent = new Intent(this, CategoriasActivity.class);
        startActivity(intent);
        finish();
    }
}
