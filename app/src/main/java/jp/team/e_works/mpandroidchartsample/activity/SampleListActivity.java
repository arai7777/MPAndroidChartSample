package jp.team.e_works.mpandroidchartsample.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_sampleList);
        setSupportActionBar(toolbar);

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
                    Intent intent = new Intent(getApplication(), StaticLineGraphActivity.class);
                    startActivity(intent);
                    break;
                }
                case 1:     // StaticMultiLineGraph
                {
                    Log.d("go to StaticMultiLineGraphActivity");
                    Intent intent = new Intent(getApplication(), StaticMultiLineGraphActivity.class);
                    startActivity(intent);
                    break;
                }
                case 2:     // DynamicLineGraph
                {
                    Log.d("go to DynamicLineGraphActivity");
                    Intent intent = new Intent(getApplication(), DynamicLineGraphActivity.class);
                    startActivity(intent);
                    break;
                }
                case 3:     // DynamicMultiLineGraph
                {
                    Log.d("go to DynamicMultiLineGraphActivity");
                    Intent intent = new Intent(getApplication(), DynamicMultiLineGraphActivity.class);
                    startActivity(intent);
                    break;
                }
                case 4:     // StaticVerticalBarGraph
                {
                    Log.d("go to StaticVerticalBarGraph");
                    Intent intent = new Intent(getApplication(), StaticVerticalBarGraphActivity.class);
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
