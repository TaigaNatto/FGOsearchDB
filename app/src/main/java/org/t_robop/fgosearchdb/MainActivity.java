package org.t_robop.fgosearchdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayAdapter<String> adapter;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView=(ListView)findViewById(R.id.list);

        //csvデータの読み込み
        // TODO 今回は基本的に0(全部読み込み)
        CSVReader reader = new CSVReader("fgo_servantData.csv",0);

        //読みこんだデータを保持
        List<List<String>> csvData = reader.read(this);

        adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        for(int i=1;i<csvData.size();i++) {
            adapter.add(csvData.get(i).get(0));
        }

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("servantName",adapter.getItem(position));
                startActivity(intent);

            }
        });

    }
}
