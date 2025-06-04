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

public class AnimalesActivity extends AppCompatActivity {

    private LinearLayout letrasContainer;
    private int filaCount = 0;

    private final String[] animales = {
            "raton", "rana", "pajaro", "leon", "lobo",
            "oso", "gato", "buho", "burro", "caballo"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.animales);

        letrasContainer = findViewById(R.id.letrasContainer);

        for (String animal : animales) {
            agregarAnimal(animal);
        }
    }

    private void agregarAnimal(String animal) {
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setPadding(16, 16, 16, 16);

        if (filaCount % 2 == 0) {
            fila.setGravity(Gravity.START);
        } else {
            fila.setGravity(Gravity.END);
        }

        filaCount++;

        TextView textoAnimal = new TextView(this);
        textoAnimal.setText(capitalizar(animal));
        textoAnimal.setTextSize(24);
        textoAnimal.setTextColor(getResources().getColor(android.R.color.black));
        textoAnimal.setPadding(0, 0, 16, 0);

        String nombreRecurso = "letra_" + animal;
        int resId = getResources().getIdentifier(nombreRecurso, "drawable", getPackageName());

        ImageView imagenAnimal = new ImageView(this);
        imagenAnimal.setImageResource(resId);
        imagenAnimal.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imagenAnimal.setContentDescription("Animal " + animal);

        fila.addView(textoAnimal);
        fila.addView(imagenAnimal);
        letrasContainer.addView(fila);
    }

    private String capitalizar(String texto) {
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    public void Retroceder(View view) {
        Intent intent = new Intent(this, CategoriasActivity.class);
        startActivity(intent);
        finish();
    }
}
