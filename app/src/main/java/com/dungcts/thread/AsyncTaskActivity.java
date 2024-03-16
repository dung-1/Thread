package com.dungcts.thread;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;
public class AsyncTaskActivity extends AppCompatActivity {

    String url = "https://images.viblo.asia/e35f2ed2-d017-4aab-b2fc-a4f6b5acb4df.png";

    Button button;
    ImageView imageView;
    public void Loading (View view) {

        DownloadImage downloadImage = new DownloadImage();

        imageView = findViewById(R.id.imgView);

        try{

            Bitmap bitmap = downloadImage.execute(url).get();

            imageView.setImageBitmap(bitmap);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;

            URL url;

            HttpURLConnection httpURLConnection;

            InputStream in;

            try {
                url = new URL(strings[0]);

                httpURLConnection = (HttpURLConnection) url.openConnection();

                in = httpURLConnection.getInputStream();

                bitmap = BitmapFactory.decodeStream(in);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);
    }
    public void nextThread(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}