package com.honey.camerapreview;

import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CameraPreviewActivity extends AppCompatActivity {

    public static final String TAG = CameraPreviewActivity.class.getSimpleName();

    private static final int CAMERA_ID = 0;
    private Camera mCamera;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCamera = getCameraInstance(CAMERA_ID);
        Camera.CameraInfo cameraInfo = new Camera.CameraInfo();
        Camera.getCameraInfo(CAMERA_ID, cameraInfo);

        if (mCamera == null) {
            setContentView(R.layout.activity_camera_unavailable);
        } else {
            setContentView(R.layout.activity_camera);
            FrameLayout cameraPreviewLayout = findViewById(R.id.camera_preview);

            int rotation = getWindowManager().getDefaultDisplay().getRotation();
            CameraPreview cameraPreview = new CameraPreview(this, null, 0, mCamera, cameraInfo, rotation);
            cameraPreviewLayout.addView(cameraPreview);
        }
    }

    private Camera getCameraInstance(int cameraId) {
        Camera c = null;
        try {
            c = Camera.open(cameraId);
        } catch (Exception e) {
            Log.d(TAG, "getCameraInstance: " + e.getMessage());
            e.printStackTrace();
        }
        return c;
    }
}
