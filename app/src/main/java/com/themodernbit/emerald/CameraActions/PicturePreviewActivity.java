package com.themodernbit.emerald.CameraActions;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.AnalysisResult;
import com.microsoft.projectoxford.vision.contract.Caption;
import com.otaliastudios.cameraview.AspectRatio;
import com.otaliastudios.cameraview.CameraUtils;
import com.themodernbit.emerald.CapturedWordActivity;
import com.themodernbit.emerald.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;


public class PicturePreviewActivity extends Activity {

    private static String KEY_LIST_TAGS = "Tags";


    private Bitmap ImageToAnalyze;

    private String subscriptKey = "1fbcf22ea45b421bb30acac8664f5252";
    private String apiRoot = "https://westcentralus.api.cognitive.microsoft.com/vision/v1.0";
    public VisionServiceRestClient theVisionService = new VisionServiceRestClient( subscriptKey, apiRoot );


    private String theMessage;
    private String theTags;
    private MessageView nativeCaptureResolution;
    private MessageView captureDescription;

    private Button AnalyzeButton;
    private Button ShowListButton;

    private static WeakReference<byte[]> image;

    public static void setImage(@Nullable byte[] im) {
        image = im != null ? new WeakReference<>(im) : null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_preview);
        final ImageView imageView = findViewById(R.id.image);
        nativeCaptureResolution = findViewById(R.id.nativeCaptureResolution);
        captureDescription = findViewById(R.id.captureDescription);

        captureDescription.setTitle("Picture Description");
        captureDescription.setMessage("Not Analyzed Yet!");
        // final MessageView actualResolution = findViewById(R.id.actualResolution);
        // final MessageView approxUncompressedSize = findViewById(R.id.approxUncompressedSize);

        AnalyzeButton = (Button) findViewById(R.id.btnAnalyze);
        ShowListButton = (Button) findViewById(R.id.btnShowItems);


        final MessageView captureLatency = findViewById(R.id.captureLatency);

        final long delay = getIntent().getLongExtra("delay", 0);
        final int nativeWidth = getIntent().getIntExtra("nativeWidth", 0);
        final int nativeHeight = getIntent().getIntExtra("nativeHeight", 0);
        byte[] b = image == null ? null : image.get();
        if (b == null) {
            finish();
            return;
        }

        CameraUtils.decodeBitmap(b, 1000, 1000, new CameraUtils.BitmapCallback() {
            @Override
            public void onBitmapReady(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
                ImageToAnalyze = bitmap;



                // approxUncompressedSize.setTitle("Approx. uncompressed size");
                // approxUncompressedSize.setMessage(getApproximateFileMegabytes(bitmap) + "MB");

                captureLatency.setTitle("Approx. capture latency");
                captureLatency.setMessage(delay + " milliseconds");

                // ncr and ar might be different when cropOutput is true.
                AspectRatio nativeRatio = AspectRatio.of(nativeWidth, nativeHeight);
                nativeCaptureResolution.setTitle("Native capture resolution");
                nativeCaptureResolution.setMessage(nativeWidth + "x" + nativeHeight + " (" + nativeRatio + ")");

                // AspectRatio finalRatio = AspectRatio.of(bitmap.getWidth(), bitmap.getHeight());
                // actualResolution.setTitle("Actual resolution");
                // actualResolution.setMessage(bitmap.getWidth() + "x" + bitmap.getHeight() + " (" + finalRatio + ")");
            }
        });

    }

    private static float getApproximateFileMegabytes(Bitmap bitmap) {
        return (bitmap.getRowBytes() * bitmap.getHeight()) / 1024 / 1024;
    }

    public void onClickAnalyze(View view){
        AnalyzeButton.setVisibility(View.GONE);
        ShowListButton.setVisibility(View.VISIBLE);

        final ByteArrayOutputStream OutputStream = new ByteArrayOutputStream();
        ImageToAnalyze.compress(Bitmap.CompressFormat.JPEG,100,OutputStream);
        final ByteArrayInputStream inputStream = new ByteArrayInputStream(OutputStream.toByteArray());

        final AsyncTask<InputStream,String,String> visionTask = new AsyncTask<InputStream, String, String>() {

            ProgressDialog mDialog = new ProgressDialog(PicturePreviewActivity.this);

            @Override
            protected String doInBackground(InputStream... inputStreams) {
                try {
                    publishProgress("Analyzing Picture");
                    final String[] theFeatures = {"Description"};
                    final String[] theDetails = {};

                    AnalysisResult theResult = theVisionService.analyzeImage(inputStreams[0], theFeatures, theDetails);
                    String AnalyzedResult = new Gson().toJson(theResult);
                    return AnalyzedResult;




                } catch (Exception e) {
                    return null;
                }
            }

        @Override
            protected void onPreExecute(){

                mDialog.show();
        }

        @Override
            protected void onPostExecute(String s){

                mDialog.dismiss();

                AnalysisResult theFinalResult = new Gson().fromJson(s, AnalysisResult.class);
                StringBuilder strDescriptionBuilder = new StringBuilder();
                StringBuilder strTagsBuilder = new StringBuilder();

                //Loop to actually get the description
                for(Caption caption:theFinalResult.description.captions){
                    strDescriptionBuilder.append(caption.text);
                }

                //Loop to fetch the tags
                for(String string:theFinalResult.description.tags) {
                    strTagsBuilder.append(string);
                    strTagsBuilder.append(" ");
                }

                theMessage = strDescriptionBuilder.toString();
                theTags = strTagsBuilder.toString();
                captureDescription.setMessage(theMessage);
        }

        @Override
            protected void onProgressUpdate(String... values){
                mDialog.setMessage(values[0]);
        }


        };

        visionTask.execute(inputStream);

    }



    public void onClickShow(View v){

            Intent theIntent = new Intent(this, CapturedWordActivity.class);
            theIntent.putExtra(KEY_LIST_TAGS, theTags);
            startActivity(theIntent);

    }

}
