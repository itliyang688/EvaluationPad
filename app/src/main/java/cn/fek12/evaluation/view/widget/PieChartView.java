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

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

import org.xclcharts.chart.PieChart;
import org.xclcharts.chart.PieChart3D;
import org.xclcharts.chart.PieData;
import org.xclcharts.common.DensityUtil;
import org.xclcharts.event.click.ArcPosition;
import org.xclcharts.renderer.XEnum;
import org.xclcharts.view.ChartView;

import java.util.LinkedList;

import cn.fek12.evaluation.R;

/**
 * @ClassName Pie3DChart01View
 * @Description  3D饼图的例子
 * 问动画效果的人太多了，其实图表库就应当只管绘图，动画效果就交给View或SurfaceView吧,
 * 	看看我弄的效果有多靓. ~_~ 
 *  依这个例子发挥发挥，可以让图从屏幕各个方向出现.
 * @author XiongChuanLiang<br/>(xcl_168@aliyun.com)
 */
public class PieChartView extends ChartView{

	private String TAG = "Pie3DChart01View";
	private PieChart chart = new PieChart();
	//private PieChart3D chart = new PieChart3D();

	private LinkedList<PieData> chartData = new LinkedList<PieData>();
	//Paint mPaintToolTip = new Paint(Paint.ANTI_ALIAS_FLAG);

	public PieChartView(Context context) {
		super(context);
		initView();
	}

	public PieChartView(Context context, AttributeSet attrs){
        super(context, attrs);
        initView();
	 }

	 public PieChartView(Context context, AttributeSet attrs, int defStyle) {
			super(context, attrs, defStyle);
			initView();
	 }
	 
	 private void initView(){
		 	//chartDataSet();
			chartRender();
	 }
	 
	
	@Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {  
        super.onSizeChanged(w, h, oldw, oldh);  
       //图所占范围大小
        chart.setChartRange(w,h);
    }  		
	
	
	@SuppressLint("ResourceAsColor")
	private void chartRender()
	{
		try {
			//设置绘图区默认缩进px值
			int [] ltrb = getPieDefaultSpadding();
			chart.setPadding(ltrb[0], ltrb[1], ltrb[2], ltrb[3]);
			//不显示key
			chart.getPlotLegend().hide();

			chart.getLabelPaint().setColor(R.color.color_000F1D);
			chart.getLabelPaint().setTextSize(26f);
			
			
		
		} catch (Exception e) {
			Log.e(TAG, e.toString());
		}
	}

	protected int[] getPieDefaultSpadding() {
		int[] ltrb = new int[4];
		ltrb[0] = DensityUtil.dip2px(getContext(), 1); //left
		ltrb[1] = DensityUtil.dip2px(getContext(), 13); //top
		ltrb[2] = DensityUtil.dip2px(getContext(), 1); //right
		ltrb[3] = DensityUtil.dip2px(getContext(), 13); //bottom
		return ltrb;
	}

	public void chartDataSet(){

		chartData.add(new PieData("65%",65,Color.parseColor("#24C768"),false));//错误率

		chartData.add(new PieData("35%",35, Color.parseColor("#FDE151"),true));//正确率

		//设置数据源
		chart.setDataSource(chartData);
	}
	
	@Override
    public void render(Canvas canvas) {
        try{
           chart.render(canvas);       
        } catch (Exception e){
        	Log.e(TAG, e.toString());
        }
    }
}
