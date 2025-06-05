package sv.edu.catolica.parlessignes;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroActivity extends AppCompatActivity {
    ImageView imageView;
    EditText nombre_user;
    EditText correo_user;
    EditText contra_user;

    Button btn_regis;

    FirebaseAuth auth;
    FirebaseFirestore miFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView = findViewById(R.id.imageView);
        nombre_user = findViewById(R.id.nombre_user);
        correo_user = findViewById(R.id.correo_user);
        contra_user = findViewById(R.id.contra_user);
        btn_regis = findViewById(R.id.btn_regis);
        auth = FirebaseAuth.getInstance();
        miFirestore = FirebaseFirestore.getInstance();
        btn_regis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();

            }
        });
    }

    public void Login(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
    private void registrarUsuario() {
        String nombre = nombre_user.getText().toString();
        String correo = correo_user.getText().toString();
        String contra = contra_user.getText().toString();
        if(!nombre.isEmpty() && !correo.isEmpty() && !contra.isEmpty()){
            if(isEmailAddress(correo)) {
                if (contra.length() >= 6) {
                    crearUsuario(nombre, correo, contra);

                } else {
                    Toast.makeText(this, R.string.mensaje_registro, Toast.LENGTH_SHORT).show();

                }
            }
            else{
                Toast.makeText(this, R.string.correo_v_registro, Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, R.string.complete_registro, Toast.LENGTH_SHORT).show();

        }
    }

    private void crearUsuario(String nombre, String correo, String contra) {
        auth.createUserWithEmailAndPassword(correo, contra).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String id = auth.getCurrentUser().getUid();
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("correo", correo);
                    map.put("contra", contra);
                    miFirestore.collection("usuario").document(id).set(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                agregarFrasesEjemplo(id);
                                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegistroActivity.this, R.string.usuario_registrado_correctamente, Toast.LENGTH_SHORT).show();
                                finish();
                            } else {
                                if (task.getException() != null) {
                                    String errorMessage = task.getException().getMessage();

                                    new AlertDialog.Builder(RegistroActivity.this)
                                            .setTitle("Error al registrar")
                                            .setMessage(errorMessage)
                                            .setPositiveButton("OK", null)
                                            .show();
                                }
                                //Toast.makeText(RegistroActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                                //String errorMessage = task.getException().getMessage();
                                // Toast.makeText(RegistroActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();

                            }
                        }
                    });

                }
                else{
                    //Toast.makeText(RegistroActivity.this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
                    // String errorMessage = task.getException().getMessage();
                    //Toast.makeText(RegistroActivity.this, "Error: " + errorMessage, Toast.LENGTH_LONG).show();
                    if (task.getException() != null) {
                        String errorMessage = task.getException().getMessage();

                        new AlertDialog.Builder(RegistroActivity.this)
                                .setTitle("Error al registrar")
                                .setMessage(errorMessage)
                                .setPositiveButton("OK", null)
                                .show();
                    }

                }
            }
        });
    }

    public boolean isEmailAddress(String email) {
        String emailRegex = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(emailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void agregarFrasesEjemplo(String userId) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Map<String, List<Map<String, Object>>> niveles = new HashMap<>();

        niveles.put("abecedario", List.of(
                frase("a", true),
                frase("b", true),
                frase("c", true),
                frase("d", true),
                frase("e", true),
                frase("f", true),
                frase("g", true),
                frase("h", true),
                frase("i", true),
                frase("j", true),
                frase("k", true),
                frase("l", true),
                frase("m", true),
                frase("n", true),
                frase("o", true),
                frase("p", true),
                frase("q", true),
                frase("r", true),
                frase("s", true),
                frase("t", true),
                frase("u", true),
                frase("v", true),
                frase("w", true),
                frase("x", true),
                frase("y", true),
                frase("z", true)

        ));

        niveles.put("animales", List.of(
                frase("pajaro", true),
                frase("oso", false),
                frase("buho", false),
                frase("gato", false),
                frase("leon", false),
                frase("burro", false),
                frase("lobo", false),
                frase("raton", false),
                frase("rana", false),
                frase("caballo", false)
        ));

        niveles.put("meses", List.of(
                frase("enero", true),
                frase("febrero", false),
                frase("marzo", false),
                frase("mayo", false),
                frase("abril", false),
                frase("junio", false),
                frase("julio", false),
                frase("agosto", false),
                frase("septiembre", false),
                frase("octubre", false),
                frase("noviembre", false),
                frase("diciembre", false)
        ));

        niveles.put("numeros", List.of(
                frase("1", true),
                frase("2", false),
                frase("3", false),
                frase("4", false),
                frase("5", false),
                frase("6", false),
                frase("7", false),
                frase("8", false),
                frase("9", false),
                frase("0", false)
        ));

        niveles.put("mixto", List.of(
                frase("febrero", true),
                frase("gato", false),
                frase("6", false),
                frase("j", false),
                frase("mayo", false),
                frase("lobo", false),
                frase("septiembre", false),
                frase("k", false)
        ));

        for (Map.Entry<String, List<Map<String, Object>>> entry : niveles.entrySet()) {
            String nivelNombre = entry.getKey();
            List<Map<String, Object>> frases = entry.getValue();

            // Crear documento del nivel
            db.collection("usuario").document(userId)
                    .collection("niveles").document(nivelNombre)
                    .set(Map.of("Completado", false));

            // Crear subcolección frases
            for (Map<String, Object> frase : frases) {
                db.collection("usuario").document(userId)
                        .collection("niveles").document(nivelNombre)
                        .collection("frases")
                        .add(frase);
            }
        }
    }

    private Map<String, Object> frase(String texto, boolean respuesta) {
        Map<String, Object> map = new HashMap<>();
        map.put("texto", texto);
        map.put("respuesta", respuesta);
        return map;
    }
}
