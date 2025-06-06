package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    EditText correo;
    EditText contra;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        correo = findViewById(R.id.correo);
        contra = findViewById(R.id.contra);
        mAuth = FirebaseAuth.getInstance();

        // Verifica si ya hay un usuario autenticado en la base de datos
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        if (mAuth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void Registrarse(View view) {
        Intent intent = new Intent(this, RegistroActivity.class);
        startActivity(intent);
    }
    public void Inicio(View view) {
        String email = correo.getText().toString().trim();
        String password = contra.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, R.string.por_favor_complete_todos_los_campos, Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, R.string.inicio_de_sesi_n_exitoso, Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(this, R.string.usuario_o_contrase_a_incorrectos, Toast.LENGTH_SHORT).show();
                    }
                });
    }

}
