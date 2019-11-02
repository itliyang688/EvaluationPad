package cn.fek12.evaluation.utils;

import android.annotation.SuppressLint;
import android.os.CountDownTimer;
import android.widget.Button;

public class CountDownTimerUtils {

    public static void contDownTimer(long millisInFuture,long countDownInterval,Button button){
        CountDownTimer timer = new CountDownTimer(millisInFuture, countDownInterval) {
            @SuppressLint("DefaultLocale")
            @Override
            public void onTick(long millisUntilFinished) {
                button.setEnabled(false);
                button.setText(String.format("已发送(%d)",millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                button.setEnabled(true);
                button.setText("重新获取");
            }
        };
        timer.start();
    }
}
