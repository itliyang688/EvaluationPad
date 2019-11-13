package cn.fek12.evaluation.view.activity;

import com.fek12.basic.base.BaseActivity;

import cn.fek12.evaluation.R;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: VideoPlayListActivity
 * @Description:
 * @CreateDate: 2019/11/13 17:32
 */
public class VideoPlayListActivity extends BaseActivity {
    @Override
    public int setLayoutResource() {
        return R.layout.video_play_list_activity;
    }

    @Override
    protected void onInitView() {
        setDefaultTitle("").hideTitle();
    }

    @Override
    protected void onLoadData() {

    }
}
