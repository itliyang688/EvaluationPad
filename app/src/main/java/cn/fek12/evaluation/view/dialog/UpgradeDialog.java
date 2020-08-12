package cn.fek12.evaluation.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import butterknife.BindView;
import butterknife.OnClick;
import cn.fek12.evaluation.R;

public class UpgradeDialog extends Dialog implements View.OnClickListener {
    TextView tvVersion;
    TextView tvExplain;
    LinearLayout llContain;
    TextView tvSubmit;
    TextView tvCancel;
    RelativeLayout colseDialog;
    private Context mContext;
    private OnSelectItemListener mOnSelectItemListener;
    private int isMust;

    public interface OnSelectItemListener {
        void onUpgrade();
        void finishActivity();
    }

    public UpgradeDialog(@NonNull Context context,int isMust, OnSelectItemListener onSelectItemListener) {
        super(context, R.style.dialog_anim);
        this.mOnSelectItemListener = onSelectItemListener;
        this.mContext = context;
        this.isMust = isMust;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.upgrade_dialog, null);
        tvVersion = view.findViewById(R.id.tvVersion);
        tvExplain = view.findViewById(R.id.tvExplain);
        llContain = view.findViewById(R.id.llContain);
        tvSubmit = view.findViewById(R.id.tvSubmit);
        tvCancel = view.findViewById(R.id.tvCancel);
        colseDialog = view.findViewById(R.id.colseDialog);

        colseDialog.setOnClickListener(this);
        tvSubmit.setOnClickListener(this);
        tvCancel.setOnClickListener(this);
        setContentView(view);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        if(isMust == 0){
            setCancelable(false);
        }else{
            setCancelable(true);
        }
    }

    public void setViewData(String versionName,String explain){
        tvVersion.setText("新版本 "+versionName);
        tvExplain.setText(explain);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvSubmit:
                if(mOnSelectItemListener != null){
                    mOnSelectItemListener.onUpgrade();
                }
                this.dismiss();
                break;
            case R.id.tvCancel:
            case R.id.colseDialog:
                this.dismiss();
                if(isMust == 0){
                    if(mOnSelectItemListener != null){
                        mOnSelectItemListener.finishActivity();
                    }
                }
                break;
        }
    }
}
