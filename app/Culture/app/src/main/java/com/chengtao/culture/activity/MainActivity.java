package com.chengtao.culture.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import com.chengtao.culture.R;
import com.chengtao.culture.fragment.ExhibitionFragment;
import com.chengtao.culture.fragment.NewsFragment;
import com.chengtao.culture.fragment.SupplyDemandFragment;
import com.chengtao.library.activity.BaseActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener{
    //------------常量
    private final static String TAB_NEWS = "资讯";
    private final static String TAB_EXHIBITION = "展会";
    private final static String TAB_SUPPLY_DEMAND = "供求";
    private final static int VIEW_PAGER_OFF_SCREEN_PAGE_LIMIT = 2;
    //-----------控件
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private ViewPager viewPager;
    private SmartTabLayout tabLayout;
    private FragmentPagerItemAdapter adapter;
    private FragmentPagerItems items;
    //退出
    private final static long EXIT_TIME = 1500;
    private long currentTime = System.currentTimeMillis();
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        //toolbar
        toolbar = getView(R.id.toolbar);
        //抽屉
        drawer = getView(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        //
        viewPager = getView(R.id.vp);
        viewPager.setOffscreenPageLimit(VIEW_PAGER_OFF_SCREEN_PAGE_LIMIT);
        tabLayout = getView(R.id.smart_tab);
        //界面
        items = new FragmentPagerItems(mContext);
        items.add(FragmentPagerItem.of(TAB_NEWS,NewsFragment.class));
        items.add(FragmentPagerItem.of(TAB_EXHIBITION,ExhibitionFragment.class));
        items.add(FragmentPagerItem.of(TAB_SUPPLY_DEMAND,SupplyDemandFragment.class));
        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),items);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
    }

    @Override
    protected void setListener() {
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setElevation(0);
        drawer.addDrawerListener(toggle);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 本界面跳转
     * @param activity Activity
     */
    public static void invoke(Activity activity){
        Intent intent = new Intent(activity,MainActivity.class);
        activity.startActivity(intent);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (System.currentTimeMillis() - currentTime <= EXIT_TIME){
            finish();
        }else {
            currentTime = System.currentTimeMillis();
            showToast("再按一次退出"+getString(R.string.app_name));
        }
        return true;
    }
}
