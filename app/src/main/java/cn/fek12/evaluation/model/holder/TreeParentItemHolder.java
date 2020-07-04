package cn.fek12.evaluation.model.holder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.unnamed.b.atv.model.TreeNode;

import cn.fek12.evaluation.R;

/**
 */
public class TreeParentItemHolder extends TreeNode.BaseNodeViewHolder<TreeParentItemHolder.IconTreeItem> {
    private TextView tvValue;
    private ImageView arrowView;
    private int pageType;
    public static final int ORIGINAL = 0;
    public static final int NOW = 1;

    public TreeParentItemHolder(Context context, int pageType) {
        super(context);
        this.pageType = pageType;
    }

    @Override
    public View createNodeView(final TreeNode node, IconTreeItem value) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(R.layout.layout_icon_node, null, false);
        tvValue = (TextView) view.findViewById(R.id.node_value);
        tvValue.setText(value.text);

        arrowView = (ImageView) view.findViewById(R.id.arrow_icon);
        if(pageType == ORIGINAL){
            arrowView.setImageResource(R.mipmap.arrow_right);
        }else{
            arrowView.setImageResource(R.mipmap.bookmark_right);
        }
        return view;
    }

    @Override
    public void toggle(boolean active) {
        if(active){
            if(pageType == ORIGINAL){
                arrowView.setImageResource(R.mipmap.arrow_down);
            }else{
                arrowView.setImageResource(R.mipmap.bookmark_down);
            }
        }else{
            if(pageType == ORIGINAL){
                arrowView.setImageResource(R.mipmap.arrow_right);
            }else{
                arrowView.setImageResource(R.mipmap.bookmark_right);
            }
        }
    }

    public static class IconTreeItem {
        public String text;

        public IconTreeItem(String text) {
            this.text = text;
        }
    }
}
