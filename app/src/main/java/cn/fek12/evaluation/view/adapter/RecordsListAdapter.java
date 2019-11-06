package cn.fek12.evaluation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.stx.xmarqueeview.XMarqueeView;
import com.stx.xmarqueeview.XMarqueeViewAdapter;

import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.model.entity.RecordsEntitiy;
public class RecordsListAdapter extends XMarqueeViewAdapter<RecordsEntitiy.DataBean> {
    List<RecordsEntitiy.DataBean> mList = null;
    private Context mContext;
    public RecordsListAdapter(List<RecordsEntitiy.DataBean> datas, Context context) {
        super(datas);
        mContext = context;
        mList = datas;
    }

    @Override
    public View onCreateView(XMarqueeView parent) {
        //跑马灯单个显示条目布局，自定义
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.records_item, null);
    }

    @Override
    public void onBindView(View parent, View view, final int position) {
        //布局内容填充
        TextView title = view.findViewById(R.id.title);
        TextView time = view.findViewById(R.id.time);
        title.setText(mList.get(position).getName());
        time.setText(mList.get(position).getCreateDate());
    }
}