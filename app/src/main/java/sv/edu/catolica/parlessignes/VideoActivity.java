package sv.edu.catolica.parlessignes;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VideoActivity extends AppCompatActivity {

    private CardView cardVideo1, cardVideo2, cardVideo3, cardVideo4, cardVideo5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.acttivity_video);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        cardVideo1 = findViewById(R.id.cardVideo1);
        cardVideo2 = findViewById(R.id.cardVideo2);
        cardVideo3 = findViewById(R.id.cardVideo3);
        cardVideo4 = findViewById(R.id.cardVideo4);
        cardVideo5 = findViewById(R.id.cardVideo5);

        cardVideo1.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=g1Yxx1PzSjg"));
            startActivity(intent);
        });

        cardVideo2.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=YNBeHPcxlR0"));
            startActivity(intent);
        });

        cardVideo3.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Sn3maQElMMk"));
            startActivity(intent);
        });

        cardVideo4.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=qG1CQFiHX6c"));
            startActivity(intent);
        });

        cardVideo5.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=IqelM_tBHU0"));
            startActivity(intent);
        });


    }


}
