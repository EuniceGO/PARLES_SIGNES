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

public class MesesActivity extends AppCompatActivity {

    private LinearLayout letrasContainer;
    private int filaCount = 0;

    // Meses con y sin tilde (mostrar / recurso)
    private final String[][] meses = {
            {"enero", "Enero"},
            {"febrero", "Febrero"},
            {"marzo", "Marzo"},
            {"abril", "Abril"},
            {"mayo", "Mayo"},
            {"junio", "Junio"},
            {"julio", "Julio"},
            {"agosto", "Agosto"},
            {"septiembre", "Septiembre"},
            {"octubre", "Octubre"},
            {"noviembre", "Noviembre"},
            {"diciembre", "Diciembre"}
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.meses);

        letrasContainer = findViewById(R.id.letrasContainer);

        for (String[] mes : meses) {
            agregarMes(mes[0], mes[1]);
        }
    }

    private void agregarMes(String mesCodigo, String mesMostrar) {
        LinearLayout fila = new LinearLayout(this);
        fila.setOrientation(LinearLayout.HORIZONTAL);
        fila.setPadding(16, 16, 16, 16);

        if (filaCount % 2 == 0) {
            fila.setGravity(Gravity.START);
        } else {
            fila.setGravity(Gravity.END);
        }

        filaCount++;

        TextView textoMes = new TextView(this);
        textoMes.setText(mesMostrar);
        textoMes.setTextSize(24);
        textoMes.setTextColor(getResources().getColor(android.R.color.black));
        textoMes.setPadding(0, 0, 16, 0);

        String nombreRecurso = "letra_" + mesCodigo;
        int resId = getResources().getIdentifier(nombreRecurso, "drawable", getPackageName());

        ImageView imagenMes = new ImageView(this);
        imagenMes.setImageResource(resId);
        imagenMes.setLayoutParams(new LinearLayout.LayoutParams(300, 300));
        imagenMes.setContentDescription("Mes " + mesCodigo);

        fila.addView(textoMes);
        fila.addView(imagenMes);
        letrasContainer.addView(fila);
    }

    public void Retroceder(View view) {
        Intent intent = new Intent(this, CategoriasActivity.class);
        startActivity(intent);
        finish();
    }
}
