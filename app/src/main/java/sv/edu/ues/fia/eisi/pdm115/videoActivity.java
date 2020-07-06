package sv.edu.ues.fia.eisi.pdm115;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class videoActivity extends AppCompatActivity {
    Button Play;
    Button Stop;
    VideoView video;
    MediaController mediacontrol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        video=(VideoView) findViewById(R.id.video);
        video.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.f2));
        mediacontrol = new MediaController(videoActivity.this);
        video.setMediaController(mediacontrol);
        video.start();
    }
}
