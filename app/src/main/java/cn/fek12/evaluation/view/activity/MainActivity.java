package cn.fek12.evaluation.view.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.fek12.basic.base.BackFragmentInterface;
import com.fek12.basic.base.BaseActivity;
import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.utils.toast.ToastUtils;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.flyco.tablayout.utils.UnreadMsgUtils;
import com.flyco.tablayout.widget.MsgView;
import com.future_education.module_login.IUserData;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.application.MyApplication;
import cn.fek12.evaluation.impl.IMain;
import cn.fek12.evaluation.model.entity.TabEntity;
import cn.fek12.evaluation.model.entity.UpdateApkEntity;
import cn.fek12.evaluation.model.sharedPreferences.PrefUtilsData;
import cn.fek12.evaluation.presenter.MainPresenter;
import cn.fek12.evaluation.utils.AppUtils;
import cn.fek12.evaluation.utils.DialogUtils;
import cn.fek12.evaluation.utils.DisUtil;
import cn.fek12.evaluation.utils.LoadViewUtils;
import cn.fek12.evaluation.utils.download.DownloadUtils;
import cn.fek12.evaluation.view.dialog.ProgressDialog;
import cn.fek12.evaluation.view.dialog.UpgradeDialog;
import cn.fek12.evaluation.view.fragment.EvaluationContainerFragment;
import cn.fek12.evaluation.view.fragment.MicroLessonContainerFragment;
import cn.fek12.evaluation.view.fragment.ReportFragment;
import cn.fek12.evaluation.view.fragment.PromoteFragment;
import cn.fek12.evaluation.view.fragment.RecordFragment;
import cn.fek12.evaluation.view.widget.NumberProgressBar;

public class MainActivity extends BaseActivity<MainPresenter> implements BackFragmentInterface, IMain.View {
    private static boolean isExit = false;
    private static final String TAG = "AIDL_Log";
    private IUserData iUserData;
    private boolean connected;
    private BaseFragment baseFragment;
    private String[] mTitles = {"测评", "报告", "微课", "提升", "记录"};
    @BindView(R.id.viewPage)
    ViewPager viewPage;
    @BindView(R.id.commonTabLayout)
    CommonTabLayout commonTabLayout;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private int[] mIconUnselectIds = {
            R.mipmap.ic_bottom_evaluation_normal, R.mipmap.ic_bottom_report_normal,
            R.mipmap.ic_bottom_micro_class_normal, R.mipmap.ic_bottom_advance_normal, R.mipmap.ic_bottom_record_normal};
    private int[] mIconSelectIds = {
            R.mipmap.ic_bottom_evaluation_checked, R.mipmap.ic_bottom_report_checked,
            R.mipmap.ic_bottom_micro_class_checked, R.mipmap.ic_bottom_advance_checked, R.mipmap.ic_bottom_record_checked};

    private static MainActivity mainActivity;
    public static MainActivity get() {
        return mainActivity != null ? mainActivity : null;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        MyApplication.getMyApp().bindService();
    }

    @Override
    protected void onInitView() {
        mainActivity = MainActivity.this;
        //initView();
        //String apkUrl = "http://cdn.llsapp.com/android/LLS-v4.0-595-20160908-143200.apk";
        //showUpgradeDialog(0,apkUrl);
    }

    @Override
    protected void onLoadData() {
        DialogUtils.showDialog(LoadViewUtils.getLoadingView(MainActivity.this));
        int versionCode = AppUtils.getVersionCode(MainActivity.this);
        mPresenter.uauth(MainActivity.this,MyApplication.getMyApp().getUserId());
        mPresenter.chechUpdate(MainActivity.this,String.valueOf(versionCode));
    }

    /**
     * 用户认证成功
     */
    @Override
    public void loadSuc(String token) {
        DialogUtils.removeDialog(MainActivity.this);
        PrefUtilsData.setToken(token);
        initView();
    }

    /**
     * 用户认证失败
     */
    @Override
    public void loadFail(String msg) {
        DialogUtils.removeDialog(MainActivity.this);
        ToastUtils.popUpToast("用户认证失败");
        initView();
    }

    @Override
    public void checkUpdateSuc(UpdateApkEntity entity) {
        if(entity != null && entity.getData() != null){
            showUpgradeDialog(entity);
        }
    }

    @Override
    public void checkUpdateFail() {

    }

    private void initView() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mFragments.add(new EvaluationContainerFragment());
        mFragments.add(new ReportFragment());
        //mFragments.add(new MicroLessonFragment());
        mFragments.add(new MicroLessonContainerFragment());
        //mFragments.add(new PrimarySchoolVideoFragment());
        mFragments.add(new PromoteFragment());
        mFragments.add(new RecordFragment());
        initCommonTabLayout();
        viewPage.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPage.setOffscreenPageLimit(5);
    }

    private void showUpgradeDialog(UpdateApkEntity entity){
        String apkUrl = entity.getData().getResourceUrl();
        //String apkUrl = "http://cdn.llsapp.com/android/LLS-v4.0-595-20160908-143200.apk";
        int isMust = entity.getData().getIsMust();
        UpgradeDialog upgradeDialog = new UpgradeDialog(this, isMust,new UpgradeDialog.OnSelectItemListener() {
            @Override
            public void onUpgrade() {
                if (TextUtils.isEmpty(apkUrl) || !Patterns.WEB_URL.matcher(apkUrl.toString()).matches()) {
                    ToastUtils.popUpToast("下载失败..");
                    return;
                }
                uploadPictureProgress();
                String singleFileSaveName = "evaluation_pad.apk";
                String mSinglePath = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "Evaluation"
                        + File.separator + singleFileSaveName;
                String mSaveFolder = FileDownloadUtils.getDefaultSaveRootPath() + File.separator + "Evaluation";
                //AppUtils.installAPK(new File(mSinglePath),MainActivity.this);
                new DownloadUtils().startDownLoadFileSingle(apkUrl, mSinglePath, mSaveFolder, new DownloadUtils.FileDownLoaderCallBack() {
                    @Override
                    public void downLoadCompleted(BaseDownloadTask task) {
                        progressDialog.dismiss();
                        AppUtils.installAPK(new File(mSinglePath),MainActivity.this);
                    }

                    @Override
                    public void downLoadError(BaseDownloadTask task, Throwable e) {
                        progressDialog.dismiss();
                        ToastUtils.popUpToast("下载失败...");
                    }

                    @Override
                    public void downLoadProgress(String progress) {
                        bnp.setProgress(Integer.valueOf(progress));
                    }
                });
            }

            @Override
            public void finishActivity() {
                MainActivity.this.finish();
            }
        });
        upgradeDialog.show();
        upgradeDialog.setViewData(entity.getData().getVersionName(),entity.getData().getVersionContent());

    }

    private ProgressDialog progressDialog;
    private NumberProgressBar bnp;
    private void uploadPictureProgress(){
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        bnp = progressDialog.getBnp();
    }

    @Override
    public void onBackPressed() {
        //if判断里面就调用了来自Fragment的onBackPressed()
        //一样！！，如果onBackPressed是返回false，就会进入条件内进行默认的操作
        if (baseFragment == null || !baseFragment.onBackPressed()) {
            exit();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        for (int i = 0; i < event.getPointerCount(); i++) {
            float x = event.getX(i);
            float y = event.getY(i);

            if (!AppUtils.touchEventInView(MicroLessonContainerFragment.getFragment().getRightView(), x, y)) {
                MicroLessonContainerFragment.getFragment().colseFrame();
            }
        }

        return super.dispatchTouchEvent(event);
    }

    protected int dp2px(float dp) {
        final float scale = MainActivity.this.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void initCommonTabLayout() {
        commonTabLayout.setTabData(mTabEntities);
        commonTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPage.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {

                }
            }
        });

        viewPage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                commonTabLayout.setCurrentTab(position);
                if(position != 3){
                    setUnread(1);
                }else{
                    commonTabLayout.hideMsg(3);
                }
                //enlargeAndreduction(position,true);
                //enlargeAndreduction(previousPos,false);
                //previousPos = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPage.setCurrentItem(0);
        //enlargeAndreduction(previousPos,true);
    }

    //设置未读消息提示
    private void setUnread(int num){
        //设置红点
        //commonTabLayout.showDot(2);
        //设置未读消息背景
        commonTabLayout.showMsg(3, num);
        commonTabLayout.setMsgMargin(3, -28 , -2);
        if(num == 0){
            UnreadMsgUtils.setSize(commonTabLayout.getMsgView(3),20);
        }
    }


    @Override
    public void onSelectedFragment(BaseFragment baseFragment) {
        this.baseFragment = baseFragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    protected MainPresenter onInitLogicImpl() {
        return new MainPresenter(this);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            ToastUtils.popUpToast("在按一次退出程序");
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);
        } else {
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        /**退出清空及解绑服务*/
        MyApplication.getMyApp().clearData();
        try {
            MyApplication.getMyApp().unbindService();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }
}