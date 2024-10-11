package com.finalproject.travelya;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import org.tensorflow.lite.DataType;
import org.tensorflow.lite.support.tensorbuffer.TensorBuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Objects;

//import com.finalproject.travelya.ml.Detectionmodel;
import com.finalproject.travelya.ml.Detectionnew;

public class CameraGalleryActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST_CODE = 100;
    private static final int GALLERY_REQUEST_CODE = 101;
    private static final int CAMERA_PERMISSION_CODE = 102;

    private ImageView cameraImageView;
    private ImageView galleryImageView;
    private ImageView imageView;
    private TextView resultTextView;
    private Button hiddenButton;
    private Button suggestButton;
    private final int imageSize = 150; // Update this to match the model input size
    private static final String TAG = "CameraGalleryActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_gallery);

        cameraImageView = findViewById(R.id.cameraimage);
        galleryImageView = findViewById(R.id.galleryimage);
        resultTextView = findViewById(R.id.result);
        imageView = findViewById(R.id.detectimage);
        hiddenButton = findViewById(R.id.hiddenButton);
        suggestButton = findViewById(R.id.suggestButton);

        cameraImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(CameraGalleryActivity.this, android.Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    openCamera();
                } else {
                    ActivityCompat.requestPermissions(CameraGalleryActivity.this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
                }
            }
        });

        galleryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
    }

    private void openCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }

    private void classifyImage(Bitmap image) {
        try {
            Detectionnew model = Detectionnew.newInstance(getApplicationContext());

            TensorBuffer inputFeature0 = TensorBuffer.createFixedSize(new int[]{1, imageSize, imageSize, 3}, DataType.FLOAT32);
            ByteBuffer byteBuffer = ByteBuffer.allocateDirect(4 * imageSize * imageSize * 3);
            byteBuffer.order(ByteOrder.nativeOrder());

            int[] intValues = new int[imageSize * imageSize];
            image.getPixels(intValues, 0, image.getWidth(), 0, 0, image.getWidth(), image.getHeight());

            int pixel = 0;
            for (int i = 0; i < imageSize; i++) {
                for (int j = 0; j < imageSize; j++) {
                    int val = intValues[pixel++];
                    byteBuffer.putFloat(((val >> 16) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat(((val >> 8) & 0xFF) * (1.f / 255));
                    byteBuffer.putFloat((val & 0xFF) * (1.f / 255));
                }
            }

            inputFeature0.loadBuffer(byteBuffer);

            Detectionnew.Outputs outputs = model.process(inputFeature0);
            TensorBuffer outputFeature0 = outputs.getOutputFeature0AsTensorBuffer();

            float[] confidences = outputFeature0.getFloatArray();
            float sigiriyaConfidence = confidences[0];

            String result;
            if (sigiriyaConfidence >= 0.5) {
                result = "Sigiriya";
                hiddenButton.setVisibility(View.VISIBLE);
                suggestButton.setVisibility(View.VISIBLE);

            } else {
                result = "Unknown";
                hiddenButton.setVisibility(View.GONE);
                suggestButton.setVisibility(View.GONE);
            }

            resultTextView.setText(result);

            model.close();
        } catch (IOException e) {
            Log.e(TAG, "Error reading model", e);
            Toast.makeText(this, "Error reading model", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e(TAG, "Error during classification", e);
            Toast.makeText(this, "Error during classification", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            try {
                Bitmap image = null;
                if (requestCode == CAMERA_REQUEST_CODE) {
                    image = (Bitmap) Objects.requireNonNull(data.getExtras()).get("data");
                } else if (requestCode == GALLERY_REQUEST_CODE) {
                    Uri imageUri = data.getData();
                    image = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                }

                if (image != null) {
                    int dimension = Math.min(image.getWidth(), image.getHeight());
                    image = ThumbnailUtils.extractThumbnail(image, dimension, dimension);
                    imageView.setImageBitmap(image);

                    image = Bitmap.createScaledBitmap(image, imageSize, imageSize, false);
                    classifyImage(image);
                }
            } catch (IOException e) {
                Log.e(TAG, "Error loading image", e);
                Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Log.e(TAG, "Unexpected error", e);
                Toast.makeText(this, "Unexpected error", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openCamera();
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void goHome(View view) {

        startActivity(new Intent(this,HomeActivity.class));
    }

    public void godescription1(View view) {
        startActivity(new Intent(this,Description1Activity.class));
    }

    public void gosuggestion(View view) {
        startActivity(new Intent(this,SuggestActivity.class));
    }
}