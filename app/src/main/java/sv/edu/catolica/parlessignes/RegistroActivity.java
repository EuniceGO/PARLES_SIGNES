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
                    Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();

                }
            }
            else{
                Toast.makeText(this, "Por favor, ingrese un correo válido", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();

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
                                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                                startActivity(intent);
                                Toast.makeText(RegistroActivity.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
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
}
