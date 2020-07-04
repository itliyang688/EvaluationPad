package cn.fek12.evaluation.model.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import cn.fek12.evaluation.R;

/**
 *
 */
public class AutoTreeChildItemHolder extends TreeNode.BaseNodeViewHolder<AutoTreeChildItemHolder.IconTreeItem> {
    public TextView tvValue;
    public ImageView arrowView;
    public boolean isCheck = false;
    private int pageType;
    public static final int ORIGINAL = 0;
    public static final int NOW = 1;

    public AutoTreeChildItemHolder(Context context, int pageType) {
        super(context);
        this.pageType = pageType;
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_child_node, null, false);
        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        arrowView = (ImageView) view.findViewById(R.id.arrow_icon);
        if(pageType == ORIGINAL){
            arrowView.setImageResource(R.mipmap.file_icon);
        }else{
            arrowView.setImageResource(R.mipmap.check_box_normal);
        }
        return view;
    }



    @Override
    public void toggle(boolean active) {
        //arrowView.setImageResource(R.mipmap.check_icon);
        //tvValue.setTextColor(Color.parseColor("#FEAE2D"));
        if (active && !isCheck) {
            if(pageType == ORIGINAL){
                arrowView.setImageResource(R.mipmap.check_icon);
                tvValue.setTextColor(Color.parseColor("#FEAE2D"));
            }else{
                arrowView.setImageResource(R.mipmap.check_box_check);
                tvValue.setTextColor(Color.parseColor("#309BFF"));
            }
        }
    }

    public static class IconTreeItem {
        public String text;
        public String id;
        public String parentId;

        public IconTreeItem(String text,String id,String parentId) {
            this.text = text;
            this.id = id;
            this.parentId = parentId;
        }
    }
}
