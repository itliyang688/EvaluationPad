package cn.fek12.evaluation.view.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fek12.basic.base.BaseFragment;
import com.fek12.basic.base.BasePresenter;

import butterknife.BindView;
import cn.fek12.evaluation.R;
import cn.fek12.evaluation.view.activity.FullScreenVideoPlayActivity;
import cn.fek12.evaluation.view.activity.SpecialVideoActivity;
import cn.fek12.evaluation.view.adapter.VideoItemSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;

/**
 * @ProjectName: EvaluationPad
 * @Package: cn.fek12.evaluation.view.fragment
 * @ClassName: MicroLessonPageFragment
 * @Description:
 * @CreateDate: 2019/11/8 11:20
 */
public class MicroLessonPageFragment extends BaseFragment {
    @BindView(R.id.contentView)
    RecyclerView contentView;
    @BindView(R.id.load_view)
    MultipleStatusView loadView;
    private SectionedRecyclerViewAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.view_common_list;
    }

    @Override
    protected void onInitView(Bundle savedInstanceState) {
        GridLayoutManager manager = new GridLayoutManager(getContext(), 5);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
                    return 5;
                } else {
                    return 1;
                }
            }
        });
        contentView.setLayoutManager(manager);
        adapter = new SectionedRecyclerViewAdapter();
        contentView.setAdapter(adapter);

        adapter.addSection(new VideoItemSection(null, 1, new VideoItemSection.OnSelectItmeListener() {
            @Override
            public void onSelectItme(int pos) {
                //Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                Intent intent = new Intent(getContext(), SpecialVideoActivity.class);
                startActivity(intent);
            }

            @Override
            public void onMore() {/**热门测评查看更多*/
                Intent intent = new Intent(getContext(), FullScreenVideoPlayActivity.class);
                startActivity(intent);
            }
        }));
    }

    @Override
    protected void onLoadDataRemote() {

    }

    @Override
    protected BasePresenter onInitLogicImpl() {
        return null;
    }

    @Override
    public boolean onBackPressed() {
        return false;
    }
}
