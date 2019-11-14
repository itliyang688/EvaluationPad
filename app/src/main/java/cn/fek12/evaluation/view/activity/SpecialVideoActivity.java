package cn.fek12.evaluation.view.activity;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.fek12.basic.base.BaseActivity;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.utils.VideoUtils;
import cn.fek12.evaluation.view.widget.MyJzvdStd;
import cn.jzvd.Jzvd;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.activity
 * @ClassName: SpecialVideoActivity
 * @Description:
 * @CreateDate: 2019/11/12 15:23
 */
public class SpecialVideoActivity extends BaseActivity {
    @BindView(R.id.jzVideo)
    MyJzvdStd myJzvdStd;

    @Override
    public int setLayoutResource() {
        return R.layout.specia_video_activity;
    }

    @Override
    protected void onInitView() {
        setEmptyTitle();
        String path = "http://192.168.0.46/group1/M00/00/00/wKgALl23kwyAMozOAOBSBYftCKo270.mp4?token=2cc8cea563f06bc61576893cb5d8e542";
        String path1 = "http://vfx.mtime.cn/Video/2019/03/19/mp4/190319222227698228.mp4";
        myJzvdStd.setUp(path, "数学课程");
        //myJzvdStd.gotoScreenFullscreen();
        myJzvdStd.seekToInAdvance = 11000;
        //myJzvdStd.setState(4,0,50000);
        Bitmap bitmap = VideoUtils.getInstance().getNetVideoBitmap(path);
        myJzvdStd.thumbImageView.setImageBitmap(bitmap);
        myJzvdStd.ivExtend.setImageResource(R.mipmap.collection_video_check);
        //myJzvdStd.thumbImageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //myJzvdStd.textureView.setVideoSize(myJzvdStd.getWidth(),myJzvdStd.getHeight());
        myJzvdStd.backButton.setOnClickListener(onClickListener);
        myJzvdStd.startVideo();
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.back:
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        Jzvd.releaseAllVideos();
        super.onDestroy();
    }

    @Override
    protected void onLoadData() {

    }
}
