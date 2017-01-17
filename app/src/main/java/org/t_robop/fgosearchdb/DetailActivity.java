package org.t_robop.fgosearchdb;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ImageView servantImageView;
    TextView servantText;

    int servantPosition;
    String servantName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        servantName=intent.getStringExtra("servantName");

        servantImageView =(ImageView)findViewById(R.id.servant_image);
        servantText=(TextView)findViewById(R.id.servant_name);

        //csvデータの読み込み
        // TODO 今回は基本的に0(全部読み込み)
        CSVReader reader = new CSVReader("fgo_servantData.csv",0);

        //読みこんだデータを保持
        List<List<String>> csvData = reader.read(this);

        for (int i=1;i<csvData.size();i++){
            if(csvData.get(i).get(0).equals(servantName)){
                servantPosition=i;
                break;
            }
        }

        servantText.setText(servantName);

        //画像取得
        Uri uri = Uri.parse(csvData.get(servantPosition).get(17));
        Uri.Builder builder = uri.buildUpon();
        AsyncTaskRequest task = new AsyncTaskRequest(servantImageView);
        task.execute(builder);

        //画像取得スレッド起動
        //AsyncTaskRequest task = new AsyncTaskRequest(servantImageView);
        //task.execute("https://www.gstatic.com/android/market_images/web/play_logo_x2.png");

    }


}
