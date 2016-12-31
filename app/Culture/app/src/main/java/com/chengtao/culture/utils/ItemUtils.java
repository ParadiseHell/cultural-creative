package com.chengtao.culture.utils;

import android.graphics.Color;
import android.view.ViewGroup;

import com.chengtao.culture.App;
import com.chengtao.culture.R;
import com.chengtao.culture.adapter.NewsAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;

/**
 * Created by ChengTao on 2017-01-01.
 */

public class ItemUtils {
    public static SwipeMenuCreator getDeleteCollectMenuCreator(){
        return new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
                int width = App.getContext().getResources().getDimensionPixelSize(R.dimen.item_height);

                // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
                int height = ViewGroup.LayoutParams.MATCH_PARENT;
                switch (viewType){
                    case NewsAdapter.COLLECT:
                        SwipeMenuItem closeItem = new SwipeMenuItem(App.getContext())
                                .setBackgroundDrawable(R.drawable.selector_purple)
                                .setImage(R.drawable.collect)
                                .setText("收藏") // 文字，还可以设置文字颜色，大小等。。
                                .setTextColor(Color.WHITE)
                                .setWidth(width)
                                .setHeight(height);
                        swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。
                        break;
                    case NewsAdapter.DELETE:
                        SwipeMenuItem deleteItem = new SwipeMenuItem(App.getContext())
                                .setBackgroundDrawable(R.drawable.selector_red)
                                .setImage(R.drawable.delete)
                                .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                                .setTextColor(Color.WHITE)
                                .setWidth(width)
                                .setHeight(height);
                        swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。
                        break;
                }
            }
        };
    }
}
