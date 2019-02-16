package com.example.eden.tacotrondemo2;

import android.content.Context;
import android.media.AudioTrack;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        final TextView text = (TextView) findViewById(R.id.tv);
        final TextView ip = (TextView) findViewById(R.id.ip);


        final Context context=this.getBaseContext();
        View.OnClickListener listner=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String te= text.getText().toString();
                final String ipadd=ip.getText().toString();
                sendReq.speech(te,context,ipadd);



            }

            };


        Button b=(Button) findViewById(R.id.button);
        b.setOnClickListener(listner);



// ...

}

}