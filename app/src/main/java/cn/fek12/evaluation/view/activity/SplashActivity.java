package cn.fek12.evaluation.view.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: SplashActivity
 * @Description:
 * @CreateDate: 2019/10/29 9:48
 */
public class SplashActivity extends BaseActivity {

    @BindView(R.id.rl_root)
    RelativeLayout rlRoot;

    @Override
    public int setLayoutResource() {
        return R.layout.activity_splash;
    }

    @Override
    protected void onInitView() {
        //animationView(rlRoot);
        startActivity(MainActivity.class);
        finish();
    }

    @Override
    protected void onLoadData() {

    }

    public void animationView(View view) {
        // 动画集合
        AnimationSet set = new AnimationSet(false);

        // 旋转动画
        /*RotateAnimation rotate = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        rotate.setDuration(1000);// 动画时间
        rotate.setFillAfter(true);// 保持动画状态*/

        // 缩放动画
        ScaleAnimation scale = new ScaleAnimation(1, 1, 1, 1,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        scale.setDuration(2000);// 动画时间
        scale.setFillAfter(true);// 保持动画状态

        // 渐变动画
        /*AlphaAnimation alpha = new AlphaAnimation(0, 1);
        alpha.setDuration(1000);// 动画时间
        alpha.setFillAfter(true);// 保持动画状态*/

        //set.addAnimation(rotate);
        set.addAnimation(scale);
        //set.addAnimation(alpha);

        // 设置动画监听
        set.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }

            // 动画执行结束
            @Override
            public void onAnimationEnd(Animation animation) {
                startActivity(MainActivity.class);
                //检查升级
                //checkUpdate();
            }
        });

        view.startAnimation(set);
    }
}
