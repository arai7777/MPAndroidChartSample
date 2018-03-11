package jp.team.e_works.mpandroidchartsample.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.Date;

import jp.team.e_works.mpandroidchartsample.R;
import jp.team.e_works.mpandroidchartsample.util.Log;

public class StaticLineGraphActivity extends AppCompatActivity {
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_line_graph);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_staticLineGraph);
        setSupportActionBar(toolbar);

        // グラフViewを初期化する
        initChart();

        // グラフに値をセットする
        setData(100, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.static_line_graph_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.staticLineGraphMenu_save:
                // グラフを画像ファイルとして保存する
                if(mLineChart != null) {
                    String saveFileName = getSaveFileName();
                    mLineChart.saveToGallery(saveFileName, 100);
                    Toast.makeText(StaticLineGraphActivity.this, "save : " + saveFileName, Toast.LENGTH_LONG).show();
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * グラフViewの初期化
     */
    private void initChart() {
        // 線グラフView
        mLineChart = (LineChart) findViewById(R.id.chart_StaticLineGraph);

        // グラフ説明テキストを表示するか
        mLineChart.getDescription().setEnabled(true);
        // グラフ説明テキストのテキスト設定
        mLineChart.getDescription().setText(getResources().getString(R.string.StaticLineGraph_Description));
        // グラフ説明テキストの文字色設定
        mLineChart.getDescription().setTextColor(Color.BLACK);
        // グラフ説明テキストの文字サイズ設定
        mLineChart.getDescription().setTextSize(10f);
        // グラフ説明テキストの表示位置設定
        mLineChart.getDescription().setPosition(0, 0);

        // グラフへのタッチジェスチャーを有効にするか
        mLineChart.setTouchEnabled(true);

        // グラフのスケーリングを有効にするか
        mLineChart.setScaleEnabled(true);
        //mLineChart.setScaleXEnabled(true);     // X軸のみに対しての設定
        //mLineChart.setScaleYEnabled(true);     // Y軸のみに対しての設定

        // グラフのドラッギングを有効にするか
        mLineChart.setDragEnabled(true);

        // グラフのピンチ/ズームを有効にするか
        mLineChart.setPinchZoom(true);

        // グラフの背景色設定
        mLineChart.setBackgroundColor(Color.WHITE);

        // Y軸(左)の設定
        // Y軸(左)の取得
        YAxis leftYAxis = mLineChart.getAxisLeft();
        // Y軸(左)の最大値設定
        leftYAxis.setAxisMaximum(2f);
        // Y軸(左)の最小値設定
        leftYAxis.setAxisMinimum(-2f);

        // Y軸(右)の設定
        // Y軸(右)は表示しない
        mLineChart.getAxisRight().setEnabled(false);

        // X軸の設定
        XAxis xAxis = mLineChart.getXAxis();
        // X軸の最大値設定
        xAxis.setAxisMaximum(100f);
        // X軸の最小値設定
        xAxis.setAxisMinimum(0f);
        // X軸の値表示設定
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if(value >= 10) {
                    // (n * 2)π
                    if (((int) value % 20) == 0) {
                        return getResources().getString(R.string.pi_label, ((int) value / 20) * 2);
                    }
                    // nπ
                    else if (((int) value % 10) == 0) {
                        return getResources().getString(R.string.pi_label, (int) value / 10);
                    }
                }
                // nullを返すと落ちるので、値を書かない場合は空文字を返す
                return "";
            }
        });
    }

    /**
     * グラフに描画するデータを設定
     */
    private void setData(int itemCount, int range) {
        ArrayList<Entry> datas = new ArrayList<>(itemCount);
        for(int i = 0; i < itemCount; i++) {
            // 数値を生成
            float val = (float) (Math.sin((Math.PI / 10) * i) * range);
            // (x, y) = (i, val)として座標データをセット
            datas.add(new Entry(i, val));
        }

        // 第一引数にデータ、第二引数にラベル名を設定する
        LineDataSet lineDataSet = new LineDataSet(datas, getResources().getString(R.string.sine_wave));

        // 値のプロット点を描かない
        lineDataSet.setDrawCircles(false);
        // 線の色設定
        lineDataSet.setColor(Color.rgb(0xb9, 0x40, 0x47));

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData lineData = new LineData(dataSets);
        mLineChart.setData(lineData);
    }

    private String getSaveFileName() {
        Date d = new Date();
        return String.format("StaticLineGraph_%tY%tm%td%tH%tM%tS.jpg", d, d, d, d, d, d);
    }
}
