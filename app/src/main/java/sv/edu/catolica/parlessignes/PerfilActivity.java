package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class PerfilActivity extends AppCompatActivity {
    EditText nombre_user;
    EditText correo_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.perfil);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nombre_user = findViewById(R.id.editTextText3);
        correo_user = findViewById(R.id.editTextText4);


        cargarDatosUsuario();
    }


    public void vistaTraductor(MenuItem item) {
        startActivity(new Intent(this, TraduccionActivity.class));
        finish();
    }


    public void vistaMaterial(MenuItem item) {
        startActivity(new Intent(this, MaterialApoyoActivity.class));
        finish();
    }

    public void vistaUsuario(MenuItem item) {
        startActivity(new Intent(this, PerfilActivity.class));
        finish();
    }

    public void vistaInicio(MenuItem item) {
        startActivity(new Intent(this, InicioActivity.class));
        finish();
    }
    private void cargarDatosUsuario() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            String uid = user.getUid();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("usuario").document(uid).get()
                    .addOnSuccessListener(documentSnapshot -> {
                        if (documentSnapshot.exists()) {
                            String nombre = documentSnapshot.getString("nombre");
                            String correo = documentSnapshot.getString("correo");

                            nombre_user.setText(nombre);
                            correo_user.setText(correo);
                        } else {
                            Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                    });
        } else {
            Toast.makeText(this, "No hay usuario autenticado", Toast.LENGTH_SHORT).show();
        }
    }
    public void cerrarsesion(View view) {
        FirebaseAuth.getInstance().signOut();

        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        startActivity(intent);
        finish();
    }


}
