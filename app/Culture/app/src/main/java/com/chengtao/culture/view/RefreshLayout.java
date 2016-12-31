package com.chengtao.culture.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.chengtao.culture.R;
import com.lcodecore.tkrefreshlayout.Footer.LoadingView;
import com.lcodecore.tkrefreshlayout.IBottomView;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;

/**
 * Created by ChengTao on 2016-12-31.
 */

public class RefreshLayout extends TwinklingRefreshLayout{
    private OnRefreshListener listener;
    public RefreshLayout(Context context) {
        this(context,null);
    }

    public RefreshLayout(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        ProgressLayout header = new ProgressLayout(context);
        this.setHeaderView(header);
        this.setFloatRefresh(true);
        this.setEnableLoadmore(true);
        this.setOverScrollBottomShow(false);
        this.setBottomView(new MLoadingView(context));
        this.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                if (listener != null){
                    listener.onRefresh();
                }
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                if (listener != null){
                    listener.onLoadMore();
                }
            }
        });
    }

    private class MLoadingView extends View implements IBottomView{
        private Context context;
        public MLoadingView(Context context) {
            this(context,null);
        }

        public MLoadingView(Context context, AttributeSet attrs) {
            this(context, attrs,0);
        }

        public MLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            this.context = context;
        }

        @Override
        public View getView() {
            return LayoutInflater.from(context).inflate(R.layout.loading_view,null);
        }

        @Override
        public void onPullingUp(float fraction, float maxHeadHeight, float headHeight) {

        }

        @Override
        public void startAnim(float maxHeadHeight, float headHeight) {

        }

        @Override
        public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {

        }

        @Override
        public void onFinish() {

        }

        @Override
        public void reset() {

        }
    }

    /**
     * 接口
     */
    public interface OnRefreshListener{
        /**
         * 刷新
         */
        void onRefresh();

        /**
         * 加载
         */
        void onLoadMore();
    }

    public void setListener(OnRefreshListener listener) {
        this.listener = listener;
    }
}
