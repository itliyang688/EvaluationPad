package cn.fek12.evaluation.view.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.utils.VideoUtils;
import cn.fek12.evaluation.view.widget.MyJzvdStd;
import cn.jzvd.Jzvd;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: FullScreenVideoPlayActivity
 * @Description:
 * @CreateDate: 2019/11/12 13:01
 */
public class FullScreenVideoPlayActivity extends BaseActivity {
    @BindView(R.id.jzVideo)
    MyJzvdStd myJzvdStd;

    @Override
    public int setLayoutResource() {
        return R.layout.video_play_activity;
    }

    @Override
    protected void onInitView() {
        String path = "http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4?token=2cc8cea563f06bc61576893cb5d8e542";
        String path1 = "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4";
        myJzvdStd.setUp(path,"数学课程");
        myJzvdStd.gotoScreenFullscreen();
        Bitmap bitmap = VideoUtils.getInstance().getNetVideoBitmap(path);
        myJzvdStd.thumbImageView.setImageBitmap(bitmap);
        myJzvdStd.ivExtend.setImageResource(R.mipmap.collection_video_check);
        //myJzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //myJzvdStd.textureView.setVideoSize(myJzvdStd.getWidth(e),myJzvdStd.getHeight());
        myJzvdStd.backButton.setOnClickListener(onClickListener);
        myJzvdStd.fullscreenButton.setOnClickListener(onClickListener);
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fullscreen:
                    break;
                case R.id.back:
                    finish();
                    break;
            }
        }
    };
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        Jzvd.releaseAllVideos();
        super.onDestroy();
    }


    @Override
    protected void onLoadData() {

    }
}
