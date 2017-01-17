package org.t_robop.fgosearchdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TopActivity extends AppCompatActivity {

    Button servantBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        servantBtn=(Button)findViewById(R.id.servant);
    }

    public void servantIntent(View v){
        Intent intent =new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
