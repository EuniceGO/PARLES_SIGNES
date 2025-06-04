package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Nivel_4Activity extends AppCompatActivity {

    private FirebaseFirestore db;
    private FirebaseAuth auth;
    private String userId;

    private String nivelActual = "meses";
    private int fraseIndex = 0;
    private List<HashMap<String, Object>> frases = new ArrayList<>();

    private TextView txtFrase, txtOpcion1, txtOpcion2, txtOpcion3, txtOpcion4;
    private ImageView imageFrase;
    private Button btnCheck, btncerrar;
    private LinearLayout layoutImagenesLetras;

    private String opcionSeleccionada = "";
    private String respuestaCorrecta = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nivel4);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        userId = auth.getCurrentUser().getUid();

        // Vistas
        txtFrase = findViewById(R.id.txtFrase);
        txtOpcion1 = findViewById(R.id.txt_opcion1);
        txtOpcion2 = findViewById(R.id.txt_opcion2);
        txtOpcion3 = findViewById(R.id.txt_opcion3);
        txtOpcion4 = findViewById(R.id.txt_opcion4);
        btnCheck = findViewById(R.id.btnCheck);
        btncerrar=findViewById(R.id.btnCerrar);
        layoutImagenesLetras = findViewById(R.id.layoutImagenesLetras);

        View.OnClickListener optionListener = v -> {
            opcionSeleccionada = ((TextView) v).getText().toString();
            resetTextViewColors();
            v.setBackgroundColor(getResources().getColor(R.color.celeste));
        };

        txtOpcion1.setOnClickListener(optionListener);
        txtOpcion2.setOnClickListener(optionListener);
        txtOpcion3.setOnClickListener(optionListener);
        txtOpcion4.setOnClickListener(optionListener);


        btncerrar.setOnClickListener(v -> {
            Intent intent = new Intent(this, NivelesActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        btnCheck.setOnClickListener(v -> verificarRespuesta());

        cargarFrasesDelNivel();
    }
    private void resetTextViewColors() {
        int defaultColor = getResources().getColor(R.color.white);
        txtOpcion1.setBackgroundColor(defaultColor);
        txtOpcion2.setBackgroundColor(defaultColor);
        txtOpcion3.setBackgroundColor(defaultColor);
        txtOpcion4.setBackgroundColor(defaultColor);
    }

    private void cargarFrasesDelNivel() {
        frases.clear();
        fraseIndex = 0;

        db.collection("usuario")
                .document(userId)
                .collection("niveles")
                .document(nivelActual)
                .collection("frases")
                .get()
                .addOnSuccessListener(querySnapshot -> {
                    List<HashMap<String, Object>> frasesTemporales = new ArrayList<>();

                    for (QueryDocumentSnapshot doc : querySnapshot) {
                        String texto = doc.getString("texto");
                        if (texto != null) {
                            HashMap<String, Object> fraseData = new HashMap<>();
                            fraseData.put("id", doc.getId());
                            fraseData.put("texto", texto);
                            fraseData.put("respuesta", doc.getBoolean("respuesta") != null ? doc.getBoolean("respuesta") : false);
                            fraseData.put("respondido", doc.getBoolean("respondido") != null ? doc.getBoolean("respondido") : false);
                            frasesTemporales.add(fraseData);
                        }
                    }

                    if (frasesTemporales.isEmpty()) {
                        Toast.makeText(this, "No hay frases para este nivel.", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    Collections.shuffle(frasesTemporales);
                    int total = Math.min(8, frasesTemporales.size());
                    for (int i = 0; i < total; i++) {
                        frases.add(frasesTemporales.get(i));
                    }

                    mostrarFrase();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al cargar frases", Toast.LENGTH_SHORT).show();
                    Log.e("Firestore", "Error", e);
                });
    }


    private void mostrarFrase() {
        if (fraseIndex >= frases.size()) {
            verificarNivelCompletado();
            return;
        }

        HashMap<String, Object> fraseActual = frases.get(fraseIndex);
        respuestaCorrecta = (String) fraseActual.get("texto");

        List<String> opciones = new ArrayList<>();
        opciones.add(respuestaCorrecta);

        List<String> opcionesFalsas = new ArrayList<>();
        for (HashMap<String, Object> f : frases) {
            String texto = (String) f.get("texto");
            if (!texto.equals(respuestaCorrecta)) {
                opcionesFalsas.add(texto);
            }
        }

        Collections.shuffle(opcionesFalsas);
        for (int i = 0; i < 3 && i < opcionesFalsas.size(); i++) {
            opciones.add(opcionesFalsas.get(i));
        }

        Collections.shuffle(opciones);

        txtFrase.setText("Selecciona la opción correcta");
        txtOpcion1.setText(opciones.get(0));
        txtOpcion2.setText(opciones.get(1));
        txtOpcion3.setText(opciones.get(2));
        txtOpcion4.setText(opciones.get(3));

        // Mostrar imagenes de letras
        layoutImagenesLetras.removeAllViews();


        int imgId = getResources().getIdentifier("letra_" + respuestaCorrecta, "drawable", getPackageName());
        if (imgId != 0) {
            ImageView iv = new ImageView(this);
            iv.setImageResource(imgId);
            int size = (int) getResources().getDimension(R.dimen.letra_image_size);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(size, size);
            params.setMargins(4, 0, 4, 0);
            iv.setLayoutParams(params);
            layoutImagenesLetras.addView(iv);
        }

        opcionSeleccionada = "";
        resetTextViewColors();
    }

    private void verificarRespuesta() {
        if (opcionSeleccionada.isEmpty()) {
            Toast.makeText(this, "Selecciona una opción", Toast.LENGTH_SHORT).show();
            return;
        }

        HashMap<String, Object> fraseActual = frases.get(fraseIndex);
        String fraseId = (String) fraseActual.get("id");
        boolean esCorrecta = opcionSeleccionada.equals(fraseActual.get("texto"));

        db.collection("usuario")
                .document(userId)
                .collection("niveles")
                .document(nivelActual)
                .collection("frases")
                .document(fraseId)
                .update("respondido", esCorrecta)
                .addOnSuccessListener(unused -> {
                    frases.get(fraseIndex).put("respondido", esCorrecta);

                    if (esCorrecta) {
                        Toast.makeText(this, "¡Correcto!", Toast.LENGTH_SHORT).show();
                        fraseIndex++;
                        mostrarFrase();
                    } else {
                        Toast.makeText(this, "Incorrecto. Intenta de nuevo.", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(this, "Error al actualizar respuesta", Toast.LENGTH_SHORT).show();
                    Log.e("Firestore", "Error al actualizar respondido", e);
                });
    }

    private void verificarNivelCompletado() {
        boolean completado = true;
        for (HashMap<String, Object> frase : frases) {
            if (!Boolean.TRUE.equals(frase.get("respondido"))) {
                completado = false;
                break;
            }
        }

        if (completado) {
            db.collection("usuario")
                    .document(userId)
                    .collection("niveles")
                    .document(nivelActual)
                    .update("Completado", true)
                    .addOnSuccessListener(unused -> {
                        Toast.makeText(this, "¡Nivel completado!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Nivel_4Activity.this, Nivel_completado.class);
                        intent.putExtra("nivel", nivelActual);
                        startActivity(intent);
                        finish();
                    });
        } else {
            Toast.makeText(this, "Debes responder bien todas las frases para completar el nivel.", Toast.LENGTH_LONG).show();
        }
    }
}