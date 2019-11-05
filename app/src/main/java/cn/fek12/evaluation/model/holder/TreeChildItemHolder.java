package cn.fek12.evaluation.model.holder;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import butterknife.BindView;
import cn.fek12.evaluation.R;

/**
 *
 */
public class TreeChildItemHolder extends TreeNode.BaseNodeViewHolder<TreeChildItemHolder.IconTreeItem> {
    public TextView tvValue;
    public ImageView arrowView;

    public TreeChildItemHolder(Context context) {
        super(context);
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_child_node, null, false);
        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        arrowView = (ImageView) view.findViewById(R.id.arrow_icon);

        return view;
    }



    @Override
    public void toggle(boolean active) {
        if (active) {
            arrowView.setImageResource(R.mipmap.check_icon);
            tvValue.setTextColor(Color.parseColor("#FEAE2D"));
        } else {
            //arrowView.setImageResource(R.mipmap.file_icon);
            //tvValue.setTextColor(Color.parseColor("#333333"));
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
