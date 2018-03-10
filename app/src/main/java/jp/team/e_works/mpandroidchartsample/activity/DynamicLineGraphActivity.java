package jp.team.e_works.mpandroidchartsample.activity;

import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.MPPointD;
import com.github.mikephil.charting.utils.Transformer;
import com.github.mikephil.charting.utils.ViewPortHandler;

import jp.team.e_works.mpandroidchartsample.R;
import jp.team.e_works.mpandroidchartsample.util.Log;

public class DynamicLineGraphActivity extends AppCompatActivity {
    private LineChart mLineChart;

    private float mX = 0;

    private boolean mIsLoop = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dynamic_line_graph);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dynamicLineGraph);
        setSupportActionBar(toolbar);

        // グラフViewを初期化する
        initChart();

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mIsLoop) {
                    updateGraph();
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {
                        Log.e(e);
                    }
                }
            }
        }).start();
    }

    @Override
    protected void onDestroy() {
        mIsLoop = false;
        super.onDestroy();
    }

    /**
     * グラフViewの初期化
     */
    private void initChart() {
        // 線グラフView
        mLineChart = (LineChart) findViewById(R.id.chart_DynamicLineGraph);

        // グラフ説明テキストを表示するか
        mLineChart.getDescription().setEnabled(true);
        // グラフ説明テキストのテキスト設定
        mLineChart.getDescription().setText(getResources().getString(R.string.DynamicLineGraph_Description));
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

        // 空のデータをセットする
        mLineChart.setData(new LineData());

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
    }

    private void updateGraph() {
        LineData lineData = mLineChart.getData();
        if(lineData == null) {
            return;
        }

        LineDataSet lineDataSet = (LineDataSet) lineData.getDataSetByIndex(0);
        if(lineDataSet == null) {
            lineDataSet = new LineDataSet(null, getResources().getString(R.string.sine_wave));
            lineDataSet.setColor(Color.rgb(0xb9, 0x40, 0x47));
            lineDataSet.setDrawCircles(false);
            lineData.addDataSet(lineDataSet);
        }

        float val = (float) Math.sin((Math.PI / 10) * mX);
        lineData.addEntry(new Entry(mX, val), 0);

        mLineChart.notifyDataSetChanged();

        mLineChart.moveViewTo(mX, getVisibleYCenterValue(mLineChart), YAxis.AxisDependency.LEFT);

        mX += 1;
    }

    private float getVisibleYCenterValue(LineChart lineChart) {
        Transformer transformer = lineChart.getTransformer(YAxis.AxisDependency.LEFT);
        ViewPortHandler viewPortHandler = lineChart.getViewPortHandler();

        float highestVisibleY = (float) transformer.getValuesByTouchPoint(viewPortHandler.contentLeft(),
                viewPortHandler.contentTop()).y;
        float highestY = Math.min(lineChart.getAxisLeft().mAxisMaximum, highestVisibleY);

        float lowestVisibleY = (float) transformer.getValuesByTouchPoint(viewPortHandler.contentLeft(),
                viewPortHandler.contentBottom()).y;
        float lowestY = Math.max(lineChart.getAxisLeft().mAxisMinimum, lowestVisibleY);

        return highestY - (Math.abs(highestY - lowestY) / 2);
    }
}
