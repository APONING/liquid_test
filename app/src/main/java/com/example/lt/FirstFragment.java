package com.example.lt;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FirstFragment extends Fragment {

    private TextView tvPermissionStatus1;
    private TextView tvPermissionStatus2;
    private TextView tvPermissionStatus3;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvPermissionStatus1 = view.findViewById(R.id.tv_permission_status1);
        tvPermissionStatus2 = view.findViewById(R.id.tv_permission_status2);
        tvPermissionStatus3 = view.findViewById(R.id.tv_permission_status3);

        if (checkPermissions1()) {
            tvPermissionStatus1.setText("有录音权限");
        } else {
            tvPermissionStatus1.setText("无录音权限");
        }
        if (checkPermissions2()) {
            tvPermissionStatus2.setText("有读写权限");
        } else {
            tvPermissionStatus2.setText("无读写权限");
        }
        tvPermissionStatus3.setText("有网络权限");
    }

    private boolean checkPermissions1() {
        int recordAudioPermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO);

        return recordAudioPermission == PackageManager.PERMISSION_GRANTED;
    }
    private boolean checkPermissions2(){
        int readExternalStoragePermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeExternalStoragePermission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return readExternalStoragePermission == PackageManager.PERMISSION_GRANTED
                && writeExternalStoragePermission == PackageManager.PERMISSION_GRANTED;
    }
}


