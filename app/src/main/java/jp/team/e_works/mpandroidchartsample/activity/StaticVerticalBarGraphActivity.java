package jp.team.e_works.mpandroidchartsample.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

import jp.team.e_works.mpandroidchartsample.R;

public class StaticVerticalBarGraphActivity extends AppCompatActivity {
    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_vertical_bar_graph);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_dynamicLineGraph);
        setSupportActionBar(toolbar);

        // グラフの初期化
        initChart();

        // 値のセット
        setData(12);
    }

    /**
     * グラフViewの初期化
     */
    private void initChart() {
        mBarChart = (BarChart) findViewById(R.id.chart_StaticVerticalBarGraph);

        // 最大値までの間にシャドウを入れるかどうか
        mBarChart.setDrawBarShadow(false);
        // 値のテキストをバーの外に出すかどうか
        mBarChart.setDrawValueAboveBar(true);
        // グラフの背景色
        mBarChart.setBackgroundColor(Color.WHITE);

        // グラフの説明テキストを表示するかどうか
        mBarChart.getDescription().setEnabled(true);
        // グラフの説明テキスト設定
        mBarChart.getDescription().setText(getResources().getString(R.string.StaticVerticalBarGraph_Description));

        // X軸の設定
        XAxis xAxis = mBarChart.getXAxis();
        // X軸の表示位置設定
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // グリッド線を描画するかどうか
        xAxis.setDrawGridLines(false);
        // X軸のラベル数
        xAxis.setLabelCount(12, true);
        // X軸のラベル設定
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                if((int)value > -1 && (int)value < 12) {
                    String[] month = getResources().getStringArray(R.array.Month);
                    return month[(int)value];
                }
                return "";
            }
        });

        // Y軸（左）の設定
        YAxis leftYAxis = mBarChart.getAxisLeft();
        // グリッド線を描画するかどうか
        leftYAxis.setDrawGridLines(true);
        // ラベル数
        leftYAxis.setLabelCount(6, true);
        // 最大値
        leftYAxis.setAxisMaximum(1f);
        // 最小値
        leftYAxis.setAxisMinimum(0f);

        // Y軸（左）の設定
        YAxis rightYAxis = mBarChart.getAxisRight();
        // グリッド線を描画するかどうか
        rightYAxis.setDrawGridLines(false);
        // ラベル数
        rightYAxis.setLabelCount(6, true);
        // 最大値
        rightYAxis.setAxisMaximum(1f);
        // 最小値
        rightYAxis.setAxisMinimum(0f);
    }

    /**
     * グラフに描画するデータを設定
     */
    private void setData(int itemCount) {
        ArrayList<BarEntry> yVals = new ArrayList<>();
        for(int i = 0; i < itemCount; i++) {
            // 適当に値を生成
            float val = (float) Math.random();
            // データを追加
            yVals.add(new BarEntry(i, val));
        }

        // データと凡例を引数にDataSetを生成
        BarDataSet dataSet = new BarDataSet(yVals, getResources().getString(R.string.temp_text));

        ArrayList<IBarDataSet> dataSetList = new ArrayList<>();
        dataSetList.add(dataSet);
        BarData barData = new BarData(dataSetList);

        // グラフにデータをセット
        mBarChart.setData(barData);
    }
}
