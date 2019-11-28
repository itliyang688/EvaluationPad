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
 * @Copyright Copyright (c) 2014 XCL-Charts (www.xclcharts.com)
 * @license http://www.apache.org/licenses/  Apache v2 License
 * @version 1.0
 */
package cn.fek12.evaluation.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import org.xclcharts.chart.AreaChart;
import org.xclcharts.chart.AreaData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.renderer.info.AnchorDataPoint;
import org.xclcharts.view.ChartView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * @ClassName AreaChart01View
 * @Description  面积图例子
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */

public class AreaChartView extends ChartView {

	private AreaChart chart = new AreaChart();
	//标签集合
	private LinkedList<String> mLabels = new LinkedList<String>();
	//数据集合
	private LinkedList<AreaData> mDataset = new LinkedList<AreaData>();

	private Paint mPaintTooltips = new Paint(Paint.ANTI_ALIAS_FLAG);


	public AreaChartView(Context context) {
		super(context);
		initView();
	}

	public AreaChartView(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
	 }

	 public AreaChartView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView() {
		chartRender();
	 }
	
	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
    }  
			 
	private void chartRender(){
		try{												 
				//设置绘图区默认缩进px值,留置空间显示Axis,Axistitle....		
				int [] ltrb = getBarLnDefaultSpadding();
				chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);
				
				chart.setCrurveLineStyle(XEnum.CrurveLineStyle.BEELINE);
							
				//数据轴最大值
				chart.getDataAxis().setAxisMax(100);
				//数据轴刻度间隔
				chart.getDataAxis().setAxisSteps(10);
				
				//网格
				chart.getPlotGrid().showHorizontalLines();
				chart.getPlotGrid().showVerticalLines();
				chart.getPlotGrid().getHorizontalLinePaint();
				chart.getPlotGrid().getVerticalLinePaint();
				chart.getPlotGrid().getHorizontalLinePaint().setStrokeWidth(0.1f);
				chart.getPlotGrid().getVerticalLinePaint().setStrokeWidth(0.1f);
				//把顶轴和右轴隐藏
				//chart.hideTopAxis();
				//chart.hideRightAxis();
				//把轴线和刻度线给隐藏起来
				chart.getDataAxis().hideAxisLine();
				chart.getDataAxis().hideTickMarks();			
				chart.getCategoryAxis().hideAxisLine();
				chart.getCategoryAxis().hideTickMarks();				

				//透明度
				chart.setAreaAlpha(200);

				chart.disablePanMode();
                chart.getPlotLegend().hide();
                 postInvalidate();

				//定义数据轴标签显示格式
				chart.getDataAxis().setLabelFormatter(new IFormatterTextCallBack(){
		
					@Override
					public String textFormatter(String value) {
						Double tmp = Double.parseDouble(value);
						DecimalFormat df=new DecimalFormat("#0");
						String label = df.format(tmp).toString()+"%";
						return (label);
					}
					
				});
				
				//设定交叉点标签显示格式
				chart.setItemLabelFormatter(new IFormatterDoubleCallBack() {
					@Override
					public String doubleFormatter(Double value) {
						DecimalFormat df=new DecimalFormat("#0");
						String label = df.format(value).toString()+"%";
						return label;
					}});


		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected int[] getBarLnDefaultSpadding(){
		int [] ltrb = new int[4];
		ltrb[0] = DensityUtil.dip2px(getContext(), 35); //left
		ltrb[1] = DensityUtil.dip2px(getContext(), 15); //top
		ltrb[2] = DensityUtil.dip2px(getContext(), 20); //right
		ltrb[3] = DensityUtil.dip2px(getContext(), 20); //bottom
		return ltrb;
	}
	
	public void chartDataSet(){
		mLabels.add("2019-11-01");
		mLabels.add("2019-11-07");
		mLabels.add("2019-11-15");
		mLabels.add("本周");

		List<Double> dataSeries3 = new LinkedList<Double>();
		dataSeries3.add((double)35);
		dataSeries3.add((double)45);
		dataSeries3.add((double)65);
		dataSeries3.add((double)75);


		AreaData line3 = new AreaData("",dataSeries3,
				Color.rgb(246, 134, 31),Color.rgb(213, 198, 126));

		//设置线上每点对应标签的颜色
		line3.getDotLabelPaint().setColor(Color.RED);
		//设置点标签
		line3.setLabelVisible(true);
		//设置线上每点对应标签的颜色
		//line3.getDotLabelPaint().setColor(Color.YELLOW);
		line3.setLineStyle(XEnum.LineStyle.DOT);

		mDataset.add(line3);

		//轴数据源
		//标签轴
		chart.setCategories(mLabels);
		//数据轴
		chart.setDataSource(mDataset);
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
