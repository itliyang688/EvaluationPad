package cn.fek12.evaluation.view.PopupWindow;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.fek12.evaluation.R;
import cn.fek12.evaluation.base.BaseObserver;
import cn.fek12.evaluation.ent.ApiRetrofit;
import cn.fek12.evaluation.ent.RxHelper;
import cn.fek12.evaluation.model.entity.ChildSectionEntity;
import cn.fek12.evaluation.model.entity.DictionaryListResp;
import cn.fek12.evaluation.model.entity.GradeDictionaryListEntity;
import cn.fek12.evaluation.model.entity.SemesterEntity;
import cn.fek12.evaluation.model.entity.SubjectEntity;
import cn.fek12.evaluation.model.entity.TextbookChildEntity;
import cn.fek12.evaluation.model.entity.TextbookEntity;
import cn.fek12.evaluation.view.adapter.DictionaryChildSection;
import cn.fek12.evaluation.view.adapter.DictionaryParentSection;
import cn.fek12.evaluation.view.adapter.DictionarySubjectSection;
import cn.fek12.evaluation.view.adapter.DictionaryTagChildSection;
import cn.fek12.evaluation.view.widget.MultipleStatusView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;


public class MenuPopupWindow extends PopupWindow {
	private View mMenuView;
	private RecyclerView recyclerView;
	private MultipleStatusView multipleStatusView;
	private TextView tvSubmit;
	private TextView tvCancel;
	private SectionedRecyclerViewAdapter leftAdapter;
	private Context mContext;
	private int tagPos;
	private int parentPos;
    private OnSelectItmeListener mOnSelectItmeListener = null;
    public interface OnSelectItmeListener {
        void onSelectItme(String gradeId,String semesterId,String subjectId,String textbookId,String userType);
    }
    private String gradeId;
    private String subjectId;
    private String semesterId = "";
    private String textbookId;
    private String evaluationId;

	private List<ChildSectionEntity> gradeList;
	private List<SubjectEntity.DataBean> subjectList;
	private List<TextbookChildEntity> textBookList;
	private List<ChildSectionEntity> semesterList;
	private List<ChildSectionEntity> evaluationList;

	@SuppressLint("InflateParams")
	public MenuPopupWindow(Context context,OnSelectItmeListener onSelectItmeListener) {
		super(context);
		this.mContext = context;
		this.mOnSelectItmeListener = onSelectItmeListener;
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mMenuView = inflater.inflate(R.layout.menu_dialog_activity, null);
		multipleStatusView = mMenuView.findViewById(R.id.load_view);
        recyclerView = mMenuView.findViewById(R.id.recyclerView);
        tvSubmit = mMenuView.findViewById(R.id.tvSubmit);
        tvCancel = mMenuView.findViewById(R.id.tvCancel);

		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.MATCH_PARENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		this.setAnimationStyle(R.style.PopupAnimation);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(0x80000000);
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
		mMenuView.setOnClickListener(clickListener);
        tvSubmit.setOnClickListener(clickListener);
        tvCancel.setOnClickListener(clickListener);
		multipleStatusView.setOnClickListener(clickListener);

        initLeftRecycler();
        initAdapter();
		queryGradeDictionaryList(mContext);
	}

	private void initAdapter(){
		leftAdapter.addSection("parent", new DictionaryParentSection(gradeList, new DictionaryParentSection.OnSelectItmeListener() {
			@Override
			public void onSelectItme(int pos) {
				gradeId = String.valueOf(gradeList.get(pos).getId());
				leftAdapter.getAdapterForSection("parent").notifyAllItemsChanged("payloads");
				/**请求二三四级数据*/
				querySubjectList(mContext,gradeId);
			}
		}));

		leftAdapter.addSection("subject", new DictionarySubjectSection(subjectList, "学科", new DictionarySubjectSection.OnSelectItmeListener() {
			@Override
			public void onSelectItme(int pos) {
				subjectId = String.valueOf(subjectList.get(pos).getId());
				leftAdapter.getAdapterForSection("subject").notifyAllItemsChanged("payloads");

				queryTextBookList(mContext,gradeId,subjectId);
			}
		}));

		leftAdapter.addSection("textbook", new DictionaryTagChildSection(textBookList, "版本", new DictionaryTagChildSection.OnSelectItmeListener() {
			@Override
			public void onSelectItme(String Id) {
				textbookId = Id;

				/**点击版本去查询教材*/
				querySemesterList(mContext,gradeId,subjectId,textbookId);
			}
		}));

		leftAdapter.addSection("semester", new DictionaryChildSection(semesterList, "教材", new DictionaryChildSection.OnSelectItmeListener() {
			@Override
			public void onSelectItme(int pos) {
				leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
				semesterId = String.valueOf(semesterList.get(pos).getId());
			}
		}));

		evaluationList = new ArrayList<>();
		ChildSectionEntity sectionEntity = new ChildSectionEntity();
		sectionEntity.setId("1");
		sectionEntity.setName("全部");
		ChildSectionEntity sectionEntity1 = new ChildSectionEntity();
		sectionEntity1.setId("2");
		sectionEntity1.setName("自主测");
		evaluationList.add(sectionEntity);
		evaluationList.add(sectionEntity1);
		leftAdapter.addSection("evaluation", new DictionaryChildSection(evaluationList, "测评", new DictionaryChildSection.OnSelectItmeListener() {
			@Override
			public void onSelectItme(int pos) {
				leftAdapter.getAdapterForSection("evaluation").notifyAllItemsChanged("payloads");
				evaluationId = String.valueOf(evaluationList.get(pos).getId());
			}
		}));
    }
	private void initLeftRecycler() {
		leftAdapter = new SectionedRecyclerViewAdapter();
		GridLayoutManager manager = new GridLayoutManager(mContext, 3);
		manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
			@Override
			public int getSpanSize(int position) {
				if (leftAdapter.getSectionItemViewType(position) == SectionedRecyclerViewAdapter.VIEW_TYPE_HEADER) {
					return 3;
				} else if (position == tagPos + 3) {
					return 3;
				} else {
					return 1;
				}
			}
		});
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(leftAdapter);
	}

	public void queryGradeDictionaryList(Context context) {
		//multipleStatusView.showLoading();
		ApiRetrofit.getInstance().getApiService().queryGradeDictionaryList("0")
				.compose(RxHelper.observableIO2Main(context))
				.subscribe(new BaseObserver<GradeDictionaryListEntity>() {

					@Override
					public void onSuccess(GradeDictionaryListEntity entry) {
						if(entry.getState().equals("0")){
							gradeList = entry.getData();
							if(gradeList != null && gradeList.size() > 0){
								gradeId = String.valueOf(gradeList.get(0).getId());
								DictionaryParentSection parentSection = (DictionaryParentSection) leftAdapter.getSection("parent");
								parentSection.updateList(gradeList);

								leftAdapter.notifyDataSetChanged();
								//multipleStatusView.showContent();
								/**请求二三四级数据*/
								querySubjectList(mContext,gradeId);
							}else{
								multipleStatusView.showEmpty();
							}

						}else{
							multipleStatusView.showEmpty();
						}
					}

					@Override
					public void onError(String msg) {
						multipleStatusView.showEmpty();
					}
				});
	}

	public void querySubjectList(Context context, String grade) {
		ApiRetrofit.getInstance().getApiService().querySubject(grade)
				.compose(RxHelper.observableIO2Main(context))
				.subscribe(new BaseObserver<SubjectEntity>() {

					@Override
					public void onSuccess(SubjectEntity entry) {
						if(entry.getState().equals("0")){
							loadSubjectSuc(entry);
						}
					}

					@Override
					public void onError(String msg) {
					}
				});
	}

	public void loadSubjectSuc(SubjectEntity entry) {
		subjectList = entry.getData();
		if(subjectList != null && subjectList.size() > 0){
			tagPos = gradeList.size() + subjectList.size();
			subjectId = String.valueOf(subjectList.get(0).getId());

			DictionarySubjectSection subjectSection = (DictionarySubjectSection) leftAdapter.getSection("subject");
			subjectSection.updateList(subjectList);

			textBookList = subjectList.get(0).getTextbook();
			DictionaryTagChildSection tagChildSection = (DictionaryTagChildSection) leftAdapter.getSection("textbook");
			if(textBookList != null && textBookList.size() > 0){
				textbookId = String.valueOf(textBookList.get(0).getId());
				tagChildSection.updateList(textBookList);

				semesterList = textBookList.get(0).getSemester();
				DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
				if(semesterList != null && semesterList.size() > 0){
					semesterId = String.valueOf(semesterList.get(0).getId());
					semesterSection.updateList(semesterList);
				}else{
					semesterId = null;
					semesterSection.updateList(null);
				}

			}else{
				textbookId = null;
				tagChildSection.updateList(null);
			}

			leftAdapter.notifyDataSetChanged();
		}
	}

	public void queryTextBookList(Context context, String grade, String subject) {
		ApiRetrofit.getInstance().getApiService().queryTextbook(grade,subject)
				.compose(RxHelper.observableIO2Main(context))
				.subscribe(new BaseObserver<TextbookEntity>() {

					@Override
					public void onSuccess(TextbookEntity entry) {
						if(entry.getState().equals("0")){
							loadTextBookSuc(entry);
						}
					}

					@Override
					public void onError(String msg) {
					}
				});
	}

	public void loadTextBookSuc(TextbookEntity entry) {
		textBookList = entry.getData();
		if(textBookList != null && textBookList.size() > 0){
			textbookId = String.valueOf(textBookList.get(0).getId());

			DictionaryTagChildSection tagChildSection = (DictionaryTagChildSection) leftAdapter.getSection("textbook");
			tagChildSection.updateList(textBookList);

			semesterList = textBookList.get(0).getSemester();
			DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
			if(semesterList != null && semesterList.size() > 0){
				semesterId = String.valueOf(semesterList.get(0).getId());
				semesterSection.updateList(semesterList);
			}else{
				semesterId = null;
				semesterSection.updateList(null);
			}
			leftAdapter.notifyDataSetChanged();
		}
	}

	public void querySemesterList(Context context, String grade, String subject, String textbook) {
		ApiRetrofit.getInstance().getApiService().querySemester(grade,subject,textbook)
				.compose(RxHelper.observableIO2Main(context))
				.subscribe(new BaseObserver<SemesterEntity>() {

					@Override
					public void onSuccess(SemesterEntity entry) {
						if(entry.getState().equals("0")){
							loadSemesterSuc(entry);
						}
					}

					@Override
					public void onError(String msg) {

					}
				});
	}

	public void loadSemesterSuc(SemesterEntity entry) {
		semesterList = entry.getData();
		if(semesterList != null && semesterList.size() > 0){
			semesterId = String.valueOf(semesterList.get(0).getId());
			DictionaryChildSection semesterSection = (DictionaryChildSection) leftAdapter.getSection("semester");
			semesterSection.updateList(semesterList);

			leftAdapter.getAdapterForSection("semester").notifyAllItemsChanged("payloads");
		}
	}


	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
		    switch (view.getId()){
                case R.id.tvCancel:
						if(mOnSelectItmeListener != null){
							mOnSelectItmeListener.onSelectItme(null,null,null,null,null);
						}
						dismiss();
                    break;
                case R.id.pop_layout:
                        dismiss();
                    break;
                case R.id.tvSubmit:
                        if(mOnSelectItmeListener != null){
                        	if(evaluationId !=null && evaluationId.equals("1")){
								evaluationId = null;
							}
                            mOnSelectItmeListener.onSelectItme(gradeId,semesterId,subjectId,textbookId,evaluationId);
                        }
						dismiss();
                    break;
            }
		}
	};

}
