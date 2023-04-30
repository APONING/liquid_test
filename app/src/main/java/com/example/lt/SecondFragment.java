package com.example.lt;

import android.media.MediaRecorder;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class SecondFragment extends Fragment {

    private TextView recordButton;
    private TextView timerText;

    
    private MediaRecorder mRecorder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        super.onViewCreated(view, savedInstanceState);

        recordButton = view.findViewById(R.id.record_button);
        timerText = view.findViewById(R.id.timer_text);

        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerText.setText("0s");
                startRecording(0);
                for (int i = 0; i < 10; i++) {
                    final int seconds = i + 1;
                    timerText.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            timerText.setText(seconds + "s");
                            if (seconds == 10) {
                                if (mRecorder != null) {
                                    mRecorder.stop();
                                    mRecorder.release();
                                    mRecorder = null;
                                }
                            }
                        }
                    }, 1000 * seconds);
                }
            }
        });
    }



    private void startRecording(int i) {
        try {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
            mRecorder.setAudioEncodingBitRate(16 * 44100);
            mRecorder.setAudioSamplingRate(44100);
            String mFilePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath() + "/test"+i+".wav";
            mRecorder.setOutputFile(mFilePath);
            mRecorder.prepare();
            mRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
