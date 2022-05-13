package com.reactnativek4lvideotrimmer;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.ActivityEventListener;
import com.facebook.react.bridge.ReactApplicationContext;
import com.gowtham.library.ui.ActVideoTrimmer;
import com.gowtham.library.utils.TrimType;
import com.gowtham.library.utils.TrimVideo;

//import life.knowledge4.videotrimmer.K4LVideoTrimmer;
//import life.knowledge4.videotrimmer.interfaces.OnTrimVideoListener;

public class TrimmerActivity extends AppCompatActivity {

  String videoUri;

  ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    result -> {
      if (result.getResultCode() == Activity.RESULT_OK &&
        result.getData() != null) {
        String uri = TrimVideo.getTrimmedVideoPath(result.getData());
        Intent returnIntent = new Intent();
        returnIntent.putExtra("URI", uri);
        setResult(1, returnIntent);
        finish();
      } else {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("error", 1);
        returnIntent.putExtra("URI", "NULL");
        setResult(2, returnIntent);
        finish();
      }
    });

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trimmer);

    Intent extraIntent = getIntent();
    Uri path = Uri.parse(extraIntent.getStringExtra("K4L_VIDEO_PATH"));
    this.videoUri = String.valueOf(path);
    openTrimActivity(
        this.videoUri,
        extraIntent.getBooleanExtra("K4L_SET_ACCURATE_CUT", false),
        extraIntent.getBooleanExtra("K4L_SET_HIDE_SEEK_BAR", false),
        extraIntent.getLongExtra("K4L_MIN_DURATION", 0L),
        extraIntent.getLongExtra("K4L_MAX_DURATION", 1L),
        extraIntent.getStringExtra("K4L_TITLE"),
        extraIntent.getStringExtra("K4L_MESSAGE")
    );
  }

  private void openTrimActivity(
      String path,
      boolean accurateCut,
      boolean hideSeekBar,
      long minDuration,
      long maxDuration,
      String title,
      String message
  ) {
     Toast.makeText(TrimmerActivity.this, message, Toast.LENGTH_LONG).show();

     TrimVideo.activity(String.valueOf(path))
      .setTrimType(TrimType.MIN_MAX_DURATION)
      .setAccurateCut(accurateCut)
      .setHideSeekBar(hideSeekBar)
      .setMinToMax(minDuration, maxDuration)
      .setTitle(title)
      .start(TrimmerActivity.this, startForResult);
  }
}
