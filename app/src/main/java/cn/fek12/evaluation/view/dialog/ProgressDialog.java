package cn.fek12.evaluation.view.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.widget.NumberProgressBar;

public class ProgressDialog  extends Dialog {
    private Context mContext;
    private NumberProgressBar bnp;
    public ProgressDialog(@NonNull Context context) {
        super(context, R.style.dialog_anim);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = LayoutInflater.from(mContext).inflate(R.layout.progress_layout, null);
        TextView title = (TextView) view.findViewById(R.id.progress_text);
        bnp = (NumberProgressBar) view.findViewById(R.id.numberbar1);

        setContentView(view);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        setCancelable(false);
    }

    public NumberProgressBar getBnp(){
        return bnp;
    }

}
