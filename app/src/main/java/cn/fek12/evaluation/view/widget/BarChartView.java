/**
 * Copyright 2014  XCL-Charts
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 	
 * @Project XCL-Charts 
 * @Description Android图表基类库
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package cn.fek12.evaluation.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

import org.xclcharts.chart.BarChart;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.view.ChartView;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName BarChart03View
 * @Description 用于展示定制线与明细刻度线
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 * MODIFIED    YYYY-MM-DD   REASON
 */
public class BarChartView extends ChartView{
	private Canvas canvas;
	private BarChart chart = new BarChart();
	//轴数据源

	private List<BarData> chartData = new LinkedList<BarData>();
	private List<CustomLineData> mCustomLineDataset = new LinkedList<CustomLineData>();

	public BarChartView(Context context) {
		super(context);
		initView();

	}

	public BarChartView(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
	 }

	 public BarChartView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView(){
			chartRender();
	 }
	 
	
	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
    }  
	
	
	private void chartRender(){
		try {
			
			//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
			int [] ltrb = getBarLnDefaultSpadding();
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);

			//chart.getDataAxis().setAxisMax(155);
			chart.getDataAxis().setAxisMin(0);
			chart.getDataAxis().setAxisSteps(5);
			chart.getDataAxis().getTickLabelPaint().setTextSize(22f);

			//指隔多少个轴刻度(即细刻度)后为主刻度
			chart.getDataAxis().setDetailModeSteps(5);
			//隐藏轴线和tick
			chart.getDataAxis().hideAxisLine();
			//隐藏刻度线
			chart.getDataAxis().hideTickMarks();
			chart.getPlotGrid().hideHorizontalLines();
			chart.getPlotGrid().hideVerticalLines();
			chart.getCategoryAxis().hideTickMarks();
			chart.getCategoryAxis().hideAxisLine();
			chart.getCategoryAxis().getTickLabelPaint().setTextSize(22f);
			//背景网格
			//chart.getPlotGrid().showHorizontalLines();


			//在柱形顶部显示值
			chart.getBar().setItemLabelVisible(true);
			chart.getBar().setBarStyle(XEnum.BarStyle.FILL);
			//chart.getBar().setBorderWidth(5);
			//chart.getBar().setOutlineAlpha(150);


			chart.disablePanMode();

			//隐藏Key
			chart.getPlotLegend().hide();
			chart.getClipExt().setExtTop(150.f);
			//柱形和标签居中方式
			chart.setBarCenterStyle(XEnum.BarCenterStyle.TICKMARKS);
			//定义数据轴标签显示格式
			chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){

				@Override
				public String textFormatter(String value) {
					Double tmp = Double.parseDouble(value);
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(tmp).toString();
					return (label);
				}
			});

			//设定格式
			chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
				@Override
				public String doubleFormatter(Double value) {
					DecimalFormat df=new DecimalFormat("#0");
					String label = df.format(value).toString();
					return label;
				}});


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int[] getBarLnDefaultSpadding(){
		int [] ltrb = new int[4];
		ltrb[0] = DensityUtil.dip2px(getContext(), 30); //left
		ltrb[1] = DensityUtil.dip2px(getContext(), 15); //top
		ltrb[2] = DensityUtil.dip2px(getContext(), 10); //right
		ltrb[3] = DensityUtil.dip2px(getContext(), 20); //bottom
		return ltrb;
	}

	public void chartDataSet(List<Double> dataSeriesA,List<String> chartLabels){
		//标签对应的柱形数据集


		//依数据值确定对应的柱形颜色.
		List<Integer> dataColorA= new LinkedList<Integer>();
		dataColorA.add(Color.parseColor("#6C6FBF"));
		dataColorA.add(Color.parseColor("#FFB11A"));
		dataColorA.add(Color.parseColor("#5ED8A9"));
		dataColorA.add(Color.parseColor("#4FC5EA"));
		chartData.clear();
		chartData.add(new BarData("",dataSeriesA,dataColorA, Color.rgb(53, 169, 239)));

		Double max = Collections.max(dataSeriesA);
		if(max < 100){
			max = 100d;
		}else{
			max += max + 10;
		}
		max = 143d;
		//数据轴
		chart.getDataAxis().setAxisMax(max);
        //数据源
        chart.setDataSource(chartData);
        chart.setCategories(chartLabels);
	}

	@Override
	public void render(Canvas canvas) {
		try{
			chart.render(canvas);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
