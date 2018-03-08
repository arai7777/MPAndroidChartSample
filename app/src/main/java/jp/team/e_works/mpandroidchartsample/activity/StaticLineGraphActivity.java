package jp.team.e_works.mpandroidchartsample.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.ChartTouchListener;
import com.github.mikephil.charting.listener.OnChartGestureListener;

import java.util.ArrayList;

import jp.team.e_works.mpandroidchartsample.R;
import jp.team.e_works.mpandroidchartsample.util.Log;

public class StaticLineGraphActivity extends AppCompatActivity {
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_line_graph);

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

        setData(10, 5);

        mLineChart.setOnChartGestureListener(mChartGestureListener);
    }

    private OnChartGestureListener mChartGestureListener = new OnChartGestureListener() {
        @Override
        public void onChartGestureStart(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            Log.d("OnChartGestureListener#onChartGestureStart(" + me.toString()
                    + ", ChartGesture" + lastPerformedGesture.toString() + ")");
        }

        @Override
        public void onChartGestureEnd(MotionEvent me, ChartTouchListener.ChartGesture lastPerformedGesture) {
            Log.d("OnChartGestureListener#onChartGestureEnd(" + me.toString()
                    + ", ChartGesture" + lastPerformedGesture.toString() + ")");
        }

        @Override
        public void onChartLongPressed(MotionEvent me) {
            Log.d("OnChartGestureListener#onChartLongPressed(" + me.toString() + ")");
        }

        @Override
        public void onChartDoubleTapped(MotionEvent me) {
            Log.d("OnChartGestureListener#onChartDoubleTapped(" + me.toString() + ")");
        }

        @Override
        public void onChartSingleTapped(MotionEvent me) {
            Log.d("OnChartGestureListener#onChartSingleTapped(" + me.toString() + ")");
        }

        @Override
        public void onChartFling(MotionEvent me1, MotionEvent me2, float velocityX, float velocityY) {
            Log.d("OnChartGestureListener#onChartFling(" + me1.toString() + ", " + me2.toString()
                    + ", " + velocityX + ", " + velocityY + ")");
        }

        @Override
        public void onChartScale(MotionEvent me, float scaleX, float scaleY) {
            Log.d("OnChartGestureListener#onChartScale(" + me.toString() + ", " + scaleX + ", " + scaleY + ")");
        }

        @Override
        public void onChartTranslate(MotionEvent me, float dX, float dY) {
            Log.d("OnChartGestureListener#onChartTranslate(" + me.toString() + ", " + dX + ", " + dY + ")");
        }
    };

    private void setData(int itemCount, int range) {
        ArrayList<Entry> datas = new ArrayList<>(itemCount);
        for(int i = 0; i < itemCount; i++) {
            // 適当に数値を生成
            float val = (float) (Math.random() * range) - 5;
            // (x, y) = (i, val)として座標データをセット
            datas.add(new Entry(i, val));
        }

        // 第一引数にデータ、第二引数にラベル名を設定する
        LineDataSet lineDataSet = new LineDataSet(datas, "sample Data");

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet);
        LineData lineData = new LineData(dataSets);
        mLineChart.setData(lineData);
    }
}
