package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Documentos extends AppCompatActivity {

    Button btnDoc1, btnDoc2, btnDoc3, btnDoc4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_documentos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnDoc1 = findViewById(R.id.btnView1);
        btnDoc2= findViewById(R.id.btnView2);
        btnDoc3= findViewById(R.id.btnView3);
        btnDoc4= findViewById(R.id.btnView4);

        btnDoc1.setOnClickListener(v -> abrirDocumento("https://www.handyhandouts.com/pdf/584_American_SIgn_Language_SPANISH.pdf"));
        btnDoc2.setOnClickListener(v -> abrirDocumento("https://www.colombiaaprende.edu.co/sites/default/files/files_public/2022-04/Diccionario-lengua-de-senas.pdf"));
        btnDoc3.setOnClickListener(v -> abrirDocumento("https://pdh.cdmx.gob.mx/storage/app/media/banner/Dic_LSM%202.pdf"));
        btnDoc4.setOnClickListener(v -> abrirDocumento("https://especial.mineduc.cl/wp-content/uploads/sites/31/2018/07/Diccionario_LSCh_A-H.pdf"));

    }
    private void abrirDocumento(String urlDocumento) {
        String url = "https://docs.google.com/gview?embedded=true&url=" + urlDocumento;
        Intent intent = new Intent(this, DocumentoWebView.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    public void Retroceder(View view) {
        Intent intent = new Intent(this, MaterialApoyoActivity.class);
        startActivity(intent);
    }
}