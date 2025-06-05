package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AnimalesActivity extends AppCompatActivity {

    private LinearLayout letrasContainer;
    private int filaCount = 0;
    private List<String[]> animales = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.animales);

        letrasContainer = findViewById(R.id.letrasContainer);

        animales.add(new String[]{"raton", getString(R.string.rat_n)});
        animales.add(new String[]{"rana", getString(R.string.rana)});
        animales.add(new String[]{"pajaro", getString(R.string.p_jaro)});
        animales.add(new String[]{"leon", getString(R.string.le_n)});
        animales.add(new String[]{"lobo", getString(R.string.lobo)});
        animales.add(new String[]{"oso", getString(R.string.oso)});
        animales.add(new String[]{"gato", getString(R.string.gato_te)});
        animales.add(new String[]{"buho", getString(R.string.b_ho_)});
        animales.add(new String[]{"burro", getString(R.string.burro_t)});
        animales.add(new String[]{"caballo", getString(R.string.caballo_t)});
        for (String[] animal : animales) {
            agregarAnimal(animal[0], animal[1]);
        }

    }

    private void agregarAnimal(String animalCodigo, String animalMostrar) {
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
        textoAnimal.setText(animalMostrar); // Muestra con tilde
        textoAnimal.setTextSize(24);
        textoAnimal.setTextColor(getResources().getColor(android.R.color.black));
        textoAnimal.setPadding(0, 0, 16, 0);

        String nombreRecurso = "letra_" + animalCodigo; // Usa sin tilde para obtener el drawable
        int resId = getResources().getIdentifier(nombreRecurso, "drawable", getPackageName());

        ImageView imagenAnimal = new ImageView(this);
        imagenAnimal.setImageResource(resId);
        imagenAnimal.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imagenAnimal.setContentDescription("Animal " + animalCodigo);

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
