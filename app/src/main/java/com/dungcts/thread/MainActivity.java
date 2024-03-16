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

public class MainActivity extends AppCompatActivity {

    String url = "https://images.viblo.asia/e35f2ed2-d017-4aab-b2fc-a4f6b5acb4df.png";

    Button button2;
    ImageView imageView2;

    public void Loading(View view) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = downloadImage(url);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView2.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }

    private Bitmap downloadImage(String urlString) {
        Bitmap bitmap = null;
        try {
            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream in = httpURLConnection.getInputStream();
            bitmap = BitmapFactory.decodeStream(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button2 = findViewById(R.id.button2);
        imageView2 = findViewById(R.id.imageView2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading(v);
            }
        });
    }
}