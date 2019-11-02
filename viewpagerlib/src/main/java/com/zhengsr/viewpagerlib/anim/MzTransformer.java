package com.zhengsr.viewpagerlib.anim;

import android.view.View;

import androidx.viewpager.widget.ViewPager;

import com.zhengsr.viewpagerlib.view.BannerViewPager;

/**
 * Created by Administrator on 2017/11/8.
 */

public class MzTransformer implements ViewPager.PageTransformer {
  private static final float MAX_SCALE = 1.0f;
  private static final float MIN_SCALE = 0.85f;

  private float mScaleMax = 1.0f;//最大的放大系数，建议是1.0
  private float mScaleMin = 0.85f;//最小的缩小系数，建议不要和mScaleMax 相差太多
  private float mCoverWidth = 550f;//中间的view覆盖两侧的view的大小

  private BannerViewPager bannerViewPager;

  public MzTransformer(BannerViewPager bannerViewPager) {
    this.bannerViewPager = bannerViewPager;
  }

  private float reduceX = 0.0f;
  private float itemWidth = 0;
  private float offsetPosition = 0f;

  @Override
  public void transformPage(View view, float position) {
    if (offsetPosition == 0f) {
      float paddingLeft = bannerViewPager.getPaddingLeft();
      float paddingRight = bannerViewPager.getPaddingRight();
      float width = bannerViewPager.getMeasuredWidth();
      offsetPosition = paddingLeft / (width - paddingLeft - paddingRight);
    }
    float currentPos = position - offsetPosition;
    if (itemWidth == 0) {
      itemWidth = view.getWidth();
      //由于左右边的view缩小而与原始的view宽度相比减小的宽度值的一半
      //当mScaleMax = 1.0f时，reduceX =（mScaleMax  - mScaleMin)) * itemWidth / 2.0f;
      reduceX = ((1.0f - mScaleMax) + (1.0f - mScaleMin)) * itemWidth / 2.0f;
    }
    if (currentPos <= -1.0f) {
      view.setTranslationX(reduceX + mCoverWidth);
      view.setScaleX(mScaleMin);
      view.setScaleY(mScaleMin);
    } else if (currentPos <= 1.0) {
      float scale = (mScaleMax - mScaleMin) * Math.abs(1.0f - Math.abs(currentPos));
      float translationX = currentPos * -reduceX;
      if (currentPos <= -0.5) {//两个view中间的临界，一般是从右到左滑动时，这时两个view在同一层，左侧View需要往X轴正方向移动覆盖的值(即：mCoverWidth )
        view.setTranslationX(translationX + mCoverWidth * Math.abs(Math.abs(currentPos) - 0.5f) / 0.5f);
      } else if (currentPos <= 0.0f) {
        view.setTranslationX(translationX);
      } else if (currentPos >= 0.5) {//两个view中间的临界，这时两个view在同一层，一般是从左向右滑动时
        view.setTranslationX(translationX - mCoverWidth * Math.abs(Math.abs(currentPos) - 0.5f) / 0.5f);
      } else {
        view.setTranslationX(translationX);
      }
      view.setScaleX(scale + mScaleMin);
      view.setScaleY(scale + mScaleMin);
    } else {
      view.setScaleX(mScaleMin);
      view.setScaleY(mScaleMin);
      view.setTranslationX(-reduceX - mCoverWidth);
    }

  }
}
