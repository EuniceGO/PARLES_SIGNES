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

public class MesesActivity extends AppCompatActivity {

    private LinearLayout letrasContainer;
    private int filaCount = 0;
    private final List<String[]> listaMeses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.meses);

        letrasContainer = findViewById(R.id.letrasContainer);
        listaMeses.add(new String[]{"enero", getString(R.string.enero)});
        listaMeses.add(new String[]{"febrero", getString(R.string.febrero)});
        listaMeses.add(new String[]{"marzo", getString(R.string.marzo)});
        listaMeses.add(new String[]{"abril", getString(R.string.abril)});
        listaMeses.add(new String[]{"mayo", getString(R.string.mayo)});
        listaMeses.add(new String[]{"junio", getString(R.string.junio)});
        listaMeses.add(new String[]{"julio", getString(R.string.julio)});
        listaMeses.add(new String[]{"agosto", getString(R.string.agosto)});
        listaMeses.add(new String[]{"septiembre", getString(R.string.septiembre)});
        listaMeses.add(new String[]{"octubre", getString(R.string.octubre)});
        listaMeses.add(new String[]{"noviembre", getString(R.string.noviembre)});
        listaMeses.add(new String[]{"diciembre", getString(R.string.diciembre)});

        for (String[] mes : listaMeses) {
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
