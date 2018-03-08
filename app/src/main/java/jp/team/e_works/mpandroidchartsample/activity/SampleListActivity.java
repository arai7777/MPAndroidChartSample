package jp.team.e_works.mpandroidchartsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import jp.team.e_works.mpandroidchartsample.R;
import jp.team.e_works.mpandroidchartsample.util.Log;

public class SampleListActivity extends AppCompatActivity {

    String[] mSampleLists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_list);

        mSampleLists = getResources().getStringArray(R.array.SampleLists);

        ListView sampleListView = (ListView) findViewById(R.id.list_samples);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mSampleLists);
        sampleListView.setAdapter(adapter);
        sampleListView.setOnItemClickListener(onItemClickListener);
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0:     // StaticLineGraph
                {
                    Log.d("go to StaticLineGraphActivity");
                    Intent intent = new Intent(SampleListActivity.this, StaticLineGraphActivity.class);
                    startActivity(intent);
                    break;
                }
                default:
                    // pass
                    break;
            }
        }
    };
}
