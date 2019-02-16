package com.example.eden.tacotrondemo2;

import android.content.Context;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.os.Environment.DIRECTORY_MUSIC;

public class sendReq {

public static AudioTrack speech (String text, final Context context, String url) {
     final MediaPlayer mediaPlayer = new MediaPlayer();

    String mUrl = "http://"+url+":5000?text=" + text;
    InputStreamVolleyRequest request = new InputStreamVolleyRequest(Request.Method.GET, mUrl,
            new Response.Listener<byte[]>() {
                @Override
                public void onResponse(byte[] response) {
                    MediaPlayer mp = new MediaPlayer();

                    // TODO handle the response
                    try {
                        if (response != null) {

                            Toast.makeText(context, "response is not null ", Toast.LENGTH_LONG).show();
                            File tempMp3 = File.createTempFile("speech", "wav", Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC));
                            Toast.makeText(context, "created file "+tempMp3.getAbsolutePath(), Toast.LENGTH_LONG).show();
                            FileOutputStream fos = new FileOutputStream(tempMp3);
                            fos.write(response);
                            Toast.makeText(context, "audio written to  "+tempMp3.getAbsolutePath(), Toast.LENGTH_LONG).show();
                            fos.close();

                            FileInputStream fis = new FileInputStream(tempMp3);
                            mediaPlayer.setDataSource(fis.getFD());

                            mediaPlayer.prepare();
                            mediaPlayer.start();
                            FileOutputStream outputStream;
                            File file = File.createTempFile("speech", "wav", Environment.getExternalStoragePublicDirectory(DIRECTORY_MUSIC));
                            //String name = "audio.wav";
                            try {
                                outputStream =new FileOutputStream(file);
                                outputStream.write(response);
                                outputStream.close();
                                Toast.makeText(context,"Download complete.", Toast.LENGTH_LONG).show();
                            } catch (FileNotFoundException e) {
                                e.printStackTrace();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                             Toast.makeText(context, "Download complete.", Toast.LENGTH_LONG).show();
                        }
                        else {

                            Toast.makeText(context, "response is NULL", Toast.LENGTH_LONG).show();
                        }
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        Log.d("KEY_ERROR", "UNABLE TO DOWNLOAD FILE");
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
            // TODO handle the error
            error.printStackTrace();
        }
    }, null);

    RequestQueue mRequestQueue = Volley.newRequestQueue(context, new HurlStack());
    int socketTimeout = 3000000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
    request.setRetryPolicy(policy);
    Toast.makeText(context, "sending request to server "+mUrl, Toast.LENGTH_LONG).show();

    mRequestQueue.add(request);
    Toast.makeText(context, "connection sucessful"+mUrl, Toast.LENGTH_LONG).show();

    return null;
}

}


