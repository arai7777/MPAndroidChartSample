package jp.team.e_works.mpandroidchartsample.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

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

import jp.team.e_works.mpandroidchartsample.R;

public class StaticMultiLineGraphActivity extends AppCompatActivity {
    private LineChart mLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_multi_line_graph);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_staticMultiLineGraph);
        setSupportActionBar(toolbar);

        // グラフViewの初期化をする
        initChart();

        // グラフに値をセットする
        setData(100, 1);
    }

    // グラフViewの初期化
    private void initChart() {
        // 線グラフView
        mLineChart = (LineChart) findViewById(R.id.chart_StaticMultiLineGraph);

        // グラフ説明テキストを表示するか
        mLineChart.getDescription().setEnabled(true);
        // グラフ説明テキストのテキスト設定
        mLineChart.getDescription().setText(getResources().getString(R.string.StaticMultiLineGraph_Description));
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
        ArrayList<Entry> sinDatas = new ArrayList<>(itemCount);
        ArrayList<Entry> cosDatas = new ArrayList<>(itemCount);
        ArrayList<Entry> tanDatas = new ArrayList<>(itemCount);
        for(int i = 0; i < itemCount; i++) {
            // 数値を生成
            float sinVal = (float) (Math.sin((Math.PI / 10) * i) * range);
            float cosVal = (float) (Math.cos((Math.PI / 10) * i) * range);
            float tanVal = (float) (Math.tan((Math.PI / 10) * i) * range);
            // (x, y) = (i, val)として座標データをセット
            sinDatas.add(new Entry(i, sinVal));
            cosDatas.add(new Entry(i, cosVal));
            tanDatas.add(new Entry(i, tanVal));
        }

        // 第一引数にデータ、第二引数にラベル名を設定する
        LineDataSet sinLineDataSet = new LineDataSet(sinDatas, getResources().getString(R.string.sine_wave));
        LineDataSet cosLineDataSet = new LineDataSet(cosDatas, getResources().getString(R.string.cosine_wave));
        LineDataSet tanLineDataSet = new LineDataSet(tanDatas, getResources().getString(R.string.tangent_wave));

        // 値のプロット点を描かない
        sinLineDataSet.setDrawCircles(false);
        cosLineDataSet.setDrawCircles(false);
        tanLineDataSet.setDrawCircles(false);
        // 線の色設定
        sinLineDataSet.setColor(Color.rgb(255, 0, 0));
        cosLineDataSet.setColor(Color.rgb(0, 255, 0));
        tanLineDataSet.setColor(Color.rgb(0, 0, 255));

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(sinLineDataSet);
        dataSets.add(cosLineDataSet);
        dataSets.add(tanLineDataSet);
        LineData lineData = new LineData(dataSets);
        mLineChart.setData(lineData);
    }
}
