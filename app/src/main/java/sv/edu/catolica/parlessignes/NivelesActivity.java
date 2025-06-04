package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;


public class NivelesActivity extends AppCompatActivity {
    ImageView btnNivel2, btnNivel3, btnNivel4, btnNivel5, img_candado2, img_candado3, img_candado4, img_candado5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveles);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        btnNivel2 = findViewById(R.id.img_btn_next2);
        btnNivel3 = findViewById(R.id.img_btn_next3);
        btnNivel4 = findViewById(R.id.img_btn_next4);
        btnNivel5 = findViewById(R.id.img_btn_next5);

        img_candado2 = findViewById(R.id.img_candado2);
        img_candado3 = findViewById(R.id.img_candado3);
        img_candado4 = findViewById(R.id.img_candado4);
        img_candado5 = findViewById(R.id.img_candado5);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String userId = auth.getCurrentUser().getUid();

        db.collection("usuario").document(userId).collection("niveles").document("abecedario")
                .get().addOnSuccessListener(doc1 -> {
                    if (Boolean.TRUE.equals(doc1.getBoolean("Completado"))) {
                        btnNivel2.setEnabled(true);
                        img_candado2.setImageResource(R.drawable.candado_abierto);


                        db.collection("usuario").document(userId).collection("niveles").document("numeros")
                                .get().addOnSuccessListener(doc2 -> {
                                    if (Boolean.TRUE.equals(doc2.getBoolean("Completado"))) {
                                        btnNivel3.setEnabled(true);
                                        img_candado3.setImageResource(R.drawable.candado_abierto);

                                        db.collection("usuario").document(userId).collection("niveles").document("animales")
                                                .get().addOnSuccessListener(doc3 -> {
                                                    if (Boolean.TRUE.equals(doc3.getBoolean("Completado"))) {
                                                        btnNivel4.setEnabled(true);
                                                        img_candado4.setImageResource(R.drawable.candado_abierto);

                                                        db.collection("usuario").document(userId).collection("niveles").document("meses")
                                                                .get().addOnSuccessListener(doc4 -> {
                                                                    if (Boolean.TRUE.equals(doc4.getBoolean("Completado"))) {
                                                                        btnNivel5.setEnabled(true);
                                                                        img_candado5.setImageResource(R.drawable.candado_abierto);
                                                                    }
                                                                });
                                                    }
                                                });
                                    }
                                });
                    }
                });
    }

    public void vistaTraductor(MenuItem item) {
        startActivity(new Intent(this, TraduccionActivity.class));
        finish();
    }


    public void vistaMaterial(MenuItem item) {
        startActivity(new Intent(this, PerfilActivity.class));
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

    public void ver_nivel1(View view) {
        startActivity(new Intent(this, Nivel_1Activity.class));
        finish();
    }
    public void ver_nivel2(View view) {
        startActivity(new Intent(this, Nivel_2Activity.class));
        finish();
    }

    public void ver_nivel3(View view) {
        startActivity(new Intent(this, Nivel_3Activity.class));
        finish();
    }

    public void ver_nivel4(View view) {
        startActivity(new Intent(this, Nivel_4Activity.class));
        finish();
    }

    public void ver_nivel5(View view) {
        startActivity(new Intent(this, Nivel_5Activity.class));
        finish();
    }
}
