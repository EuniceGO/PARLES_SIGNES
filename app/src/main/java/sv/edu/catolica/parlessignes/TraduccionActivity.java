package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
                String textoReconocido = result.get(0);
                textView.setText(textoReconocido);
                mostrarSenias(textoReconocido);
            }
        }

    }
    private void mostrarSenias(String texto) {
        LinearLayout contenedorVertical = findViewById(R.id.contenedorFilas);
        contenedorVertical.removeAllViews();

        texto = texto.toLowerCase();
        texto = normalizarTildes(texto);

        int maxAnchoFila = getResources().getDisplayMetrics().widthPixels;
        int sizeImagen = (int) (80 * getResources().getDisplayMetrics().density); // tamaño imagen en px
        int margenEntreImagenes = (int) (8 * getResources().getDisplayMetrics().density);

        LinearLayout filaActual = new LinearLayout(this);
        filaActual.setOrientation(LinearLayout.HORIZONTAL);
        filaActual.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        filaActual.setPadding(0, 0, 0, margenEntreImagenes);
        contenedorVertical.addView(filaActual);

        int anchoOcupado = 0;
        for (char c : texto.toCharArray()) {
            if (Character.isLetter(c) || c == 'ñ') {
                String nombreImagen = "letra_" + (c == 'ñ' ? "ene" : c);
                int id = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());

                if (id != 0) {
                    ImageView imagen = new ImageView(this);
                    imagen.setImageResource(id);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeImagen, sizeImagen);
                    params.setMargins(margenEntreImagenes / 2, 0, margenEntreImagenes / 2, 0);
                    imagen.setLayoutParams(params);

                    if (anchoOcupado + sizeImagen + margenEntreImagenes > maxAnchoFila) {
                        filaActual = new LinearLayout(this);
                        filaActual.setOrientation(LinearLayout.HORIZONTAL);
                        filaActual.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        filaActual.setPadding(0, 0, 0, margenEntreImagenes);
                        contenedorVertical.addView(filaActual);
                        anchoOcupado = 0;
                    }

                    filaActual.addView(imagen);
                    anchoOcupado += sizeImagen + margenEntreImagenes;
                }
            } else if (Character.isDigit(c)) {
                String nombreImagen = "letra_" + c;
                int id = getResources().getIdentifier(nombreImagen, "drawable", getPackageName());

                if (id != 0) {
                    ImageView imagen = new ImageView(this);
                    imagen.setImageResource(id);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(sizeImagen, sizeImagen);
                    params.setMargins(margenEntreImagenes / 2, 0, margenEntreImagenes / 2, 0);
                    imagen.setLayoutParams(params);

                    if (anchoOcupado + sizeImagen + margenEntreImagenes > maxAnchoFila) {
                        filaActual = new LinearLayout(this);
                        filaActual.setOrientation(LinearLayout.HORIZONTAL);
                        filaActual.setLayoutParams(new LinearLayout.LayoutParams(
                                LinearLayout.LayoutParams.MATCH_PARENT,
                                LinearLayout.LayoutParams.WRAP_CONTENT));
                        filaActual.setPadding(0, 0, 0, margenEntreImagenes);
                        contenedorVertical.addView(filaActual);
                        anchoOcupado = 0;
                    }

                    filaActual.addView(imagen);
                    anchoOcupado += sizeImagen + margenEntreImagenes;
                }
            } else if (Character.isSpaceChar(c)) {

                TextView guion = new TextView(this);
                guion.setText("-");
                guion.setTextSize(24);
                guion.setPadding(margenEntreImagenes / 2, 0, margenEntreImagenes / 2, 0);
                guion.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        sizeImagen
                ));


                int anchoGuion = (int) guion.getPaint().measureText("-") + margenEntreImagenes;
                if (anchoOcupado + anchoGuion > maxAnchoFila) {
                    filaActual = new LinearLayout(this);
                    filaActual.setOrientation(LinearLayout.HORIZONTAL);
                    filaActual.setLayoutParams(new LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT));
                    filaActual.setPadding(0, 0, 0, margenEntreImagenes);
                    contenedorVertical.addView(filaActual);
                    anchoOcupado = 0;
                }

                filaActual.addView(guion);
                anchoOcupado += anchoGuion;
            }
        }
    }
    private String normalizarTildes(String texto) {
        return texto
                .replace('á', 'a')
                .replace('é', 'e')
                .replace('í', 'i')
                .replace('ó', 'o')
                .replace('ú', 'u')
                .replace('ü', 'u');
    }


}
