package rk.listenme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import rk.listenme.localdb.models.Track;

public class AddTrackActivity extends AppCompatActivity {

    private EditText titleInput;
    private EditText imageInput;
    private EditText trackInput;
    private Button confirmAddBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_track);

        titleInput = findViewById(R.id.titleInput);
        imageInput = findViewById(R.id.imageInput);
        trackInput = findViewById(R.id.trackUrlInput);
        confirmAddBtn = findViewById(R.id.confirmAddBtn);

        confirmAddBtn.setOnClickListener(v -> {
           String title = titleInput.getText().toString();
           String image = imageInput.getText().toString();
           String trackUrl = trackInput.getText().toString();

           if(!URLUtil.isValidUrl(trackUrl)) {
               Toast.makeText(this, "Track link is not valid", Toast.LENGTH_LONG).show();
               return;
           }

           if(!image.isEmpty() && !URLUtil.isValidUrl(image)) {
               Toast.makeText(this, "Image link is not valid", Toast.LENGTH_LONG).show();
               return;
           }

           Track track = new Track(title, trackUrl, image);

            Intent mainIntent = new Intent(this, MainActivity.class);
            mainIntent.putExtra("track", track);
            setResult(RESULT_OK, mainIntent);
            finish();
        });
    }
}