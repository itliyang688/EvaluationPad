package cn.fek12.evaluation.view.widget;

import android.os.CountDownTimer;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * @Package: cn.fek12.evaluate.view.widget
 * @CreateDate: 2019/10/18 16:27
 * @Description:
 * @Author: liyang
 * @Version: 1.0
 */
public class PeterTimeCountRefresh extends CountDownTimer {
    private TextView textView;
    private long millisUntilFinished;
    public PeterTimeCountRefresh(long millisInFuture, long countDownInterval,TextView textView) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        this.millisUntilFinished = millisUntilFinished;
        DecimalFormat dec = new DecimalFormat("##.##");
        textView.setText("0" + (int) Math.floor(millisUntilFinished / 60000) + ":" + dec.format((millisUntilFinished % 60000) / 1000));
    }

    @Override
    public void onFinish() {

    }
}
