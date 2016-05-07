package mx.edu.utng.jsp_y_servlet;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by NORMA on 31/03/2016.
 */
public class VideoIntroduccionActivity extends Activity {

    private VideoView video;
    private MediaController mediaController;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_introduccion_layout);
        initComponent();

    }

    private void initComponent(){
       video = (VideoView) findViewById(R.id.video_introduccion);

        mediaController = new MediaController(this);
        mediaController.setAnchorView(video);
        video.setMediaController(mediaController);//Se le asignan los controles
        uri = Uri.parse("rtsp://r7---sn-a5m7ln7z.googlevideo.com/Cj0LENy73wIaNAk38cbxjTtb8RMYDSANFC2V1NdWMOCoAUIASARgwsbSpvX38rZWigELY1JxdncxM2RGUWsM/374D9C32A4744F5EB6B1DCF859902412D1A3F8F7.8C761827F53AA0ED6CC8BC9D9307DDEC024A5772/yt6/1/video.3gp");

        video.setVideoURI(uri);
        video.requestFocus();


    }
}
