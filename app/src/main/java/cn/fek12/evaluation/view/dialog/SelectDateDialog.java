package cn.fek12.evaluation.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import com.ldf.calendar.component.CalendarAttr;
import com.ldf.calendar.component.CalendarViewAdapter;
import com.ldf.calendar.interf.OnSelectDateListener;
import com.ldf.calendar.model.CalendarDate;
import com.ldf.calendar.view.Calendar;
import com.ldf.calendar.view.MonthPager;

import java.util.ArrayList;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.widget.CustomDayView;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.dialog
 * @ClassName: SelectDateDialog
 * @Description:
 * @CreateDate: 2019/11/22 11:16
 */
public class SelectDateDialog extends Dialog {
    MonthPager monthPager;
    private Context mContext;
    private CalendarViewAdapter calendarAdapter;
    private OnSelectDateListener onSelectDateListener;
    private TextView tvPageDate;
    private TextView tvCheckDate;
    private TextView btnConfirm;
    private CalendarDate currentDate;
    private CalendarDate selectDate;
    private ArrayList<Calendar> currentCalendars = new ArrayList<>();
    private String titleName;
    private OnSelectItemDateListener mOnSelectItemDateListener;

    public interface OnSelectItemDateListener {
        void onDateItme(String date);
    }

    public SelectDateDialog(@NonNull Context context,String title,OnSelectItemDateListener onSelectDateListener) {
        super(context, R.style.dialog_anim);
        mContext = context;
        titleName = title;
        mOnSelectItemDateListener = onSelectDateListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.date_select_dialog, null);
        monthPager = view.findViewById(R.id.calendar_view);
        tvPageDate = view.findViewById(R.id.tvPageDate);
        tvCheckDate = view.findViewById(R.id.tvCheckDate);
        btnConfirm = view.findViewById(R.id.btnConfirm);
        setContentView(view);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        tvCheckDate.setText(titleName);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mOnSelectItemDateListener != null){
                    mOnSelectItemDateListener.onDateItme(tvCheckDate.getText().toString());
                    dismiss();
                }
            }
        });
        btnConfirm.setClickable(false);
        btnConfirm.setTextColor(Color.parseColor("#d5d5d5"));

        currentDate = new CalendarDate();
        selectDate = new CalendarDate();
        String year = currentDate.getYear() + "年";
        String month = currentDate.getMonth() + "月";
        tvPageDate.setText(year+month);

        initCalendarView();

    }

    /**
     * 初始化CustomDayView，并作为CalendarViewAdapter的参数传入
     */
    private void initCalendarView() {
        initListener();
        CustomDayView customDayView = new CustomDayView(mContext, R.layout.custom_day);
        calendarAdapter = new CalendarViewAdapter(
                mContext,
                onSelectDateListener,
                CalendarAttr.WeekArrayType.Monday,
                customDayView);
        calendarAdapter.setOnCalendarTypeChangedListener(new CalendarViewAdapter.OnCalendarTypeChanged() {
            @Override
            public void onCalendarTypeChanged(CalendarAttr.CalendarType type) {

            }
        });
        initMonthPager();
    }


    private void initListener() {
        onSelectDateListener = new OnSelectDateListener() {
            @Override
            public void onSelectDate(CalendarDate date) {
                refreshClickDate(date);
            }

            @Override
            public void onSelectOtherMonth(int offset) {
                //偏移量 -1表示刷新成上一个月数据 ， 1表示刷新成下一个月数据
                monthPager.selectOtherMonth(offset);
            }
        };
    }

    /**
     * 初始化monthPager，MonthPager继承自ViewPager
     *
     * @return void
     */
    private void initMonthPager() {
        monthPager.setAdapter(calendarAdapter);
        monthPager.setCurrentItem(MonthPager.CURRENT_DAY_INDEX);
        monthPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                position = (float) Math.sqrt(1 - Math.abs(position));
                page.setAlpha(position);
            }
        });
        monthPager.addOnPageChangeListener(new MonthPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                currentCalendars = calendarAdapter.getPagers();
                if (currentCalendars.get(position % currentCalendars.size()) != null) {
                    CalendarDate date = currentCalendars.get(position % currentCalendars.size()).getSeedDate();
                    currentDate = date;
                    String year = currentDate.getYear() + "年";
                    String month = currentDate.getMonth() + "月";
                    tvPageDate.setText(year+month);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void refreshClickDate(CalendarDate date) {
        //long selectTime = AppUtils.dateToTime(date.toString());
        //long currentTime = AppUtils.dateToTime(currentDate.toString());

        currentDate = date;
        selectDate = date;
        String year = currentDate.getYear() + "年";
        String month = currentDate.getMonth() + "月";
        tvPageDate.setText(year+month);
        tvCheckDate.setText(selectDate.getYear()+"-"+selectDate.getMonth()+"-"+selectDate.getDay());
        btnConfirm.setClickable(true);
        btnConfirm.setTextColor(Color.parseColor("#FEAC2C"));
        /*if (selectTime <= currentTime) {
            currentDate = date;
            selectDate = date;
            String year = currentDate.getYear() + "年";
            String month = currentDate.getMonth() + "";
            tvPageDate.setText(year+"-"+month);
        }*/
    }
}
