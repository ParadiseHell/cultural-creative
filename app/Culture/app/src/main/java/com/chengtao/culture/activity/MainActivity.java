package com.chengtao.culture.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chengtao.culture.R;
import com.chengtao.culture.activityimpl.IMain;
import com.chengtao.culture.entity.CompanyUser;
import com.chengtao.culture.entity.PersonUser;
import com.chengtao.culture.fragment.DemandFragment;
import com.chengtao.culture.fragment.ExhibitionFragment;
import com.chengtao.culture.fragment.NewsFragment;
import com.chengtao.culture.fragment.SupplyFragment;
import com.chengtao.culture.presenter.MainPresenter;
import com.chengtao.culture.request.LoginRequest;
import com.chengtao.culture.utils.SpUtils;
import com.chengtao.culture.utils.StringUtils;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import de.hdodenhof.circleimageview.CircleImageView;

@SuppressWarnings("FieldCanBeLocal")
public class MainActivity extends IActivity
        implements NavigationView.OnNavigationItemSelectedListener,IMain,View.OnClickListener{
    //------------常量
    private final static String TAB_NEWS = "资讯";
    private final static String TAB_EXHIBITION = "展会";
    private final static String TAB_SUPPLY = "供应";
    private final static String TAB_DEMAND = "需求";
    private final static int VIEW_PAGER_OFF_SCREEN_PAGE_LIMIT = 3;
    //-----------控件
    private Toolbar toolbar;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private Button btnLogin;
    private Button btnLogout;
    private ViewPager viewPager;
    private SmartTabLayout tabLayout;
    private FragmentPagerItemAdapter adapter;
    private FragmentPagerItems items;
    private View hearViewLayout;
    private CircleImageView avatar;
    private TextView tvUserName;
    //退出
    private final static long EXIT_TIME = 1500;
    private long currentTime = System.currentTimeMillis();
    //-----------presenter
    private MainPresenter mainPresenter;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        btnLogin = getView(R.id.btn_login);
        btnLogout = getView(R.id.btn_logout);
        if (StringUtils.isStrNull(SpUtils.getUserName(),SpUtils.getUserPassword())){
            btnLogin.setVisibility(View.VISIBLE);
            btnLogout.setVisibility(View.GONE);
        }
        //toolbar
        toolbar = getView(R.id.toolbar);
        //抽屉
        drawer = getView(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();
        navigationView = getView(R.id.nav_view);
        hearViewLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        //
        viewPager = getView(R.id.vp);
        viewPager.setOffscreenPageLimit(VIEW_PAGER_OFF_SCREEN_PAGE_LIMIT);
        tabLayout = getView(R.id.smart_tab);
        //界面
        items = new FragmentPagerItems(mContext);
        items.add(FragmentPagerItem.of(TAB_NEWS,NewsFragment.class));
        items.add(FragmentPagerItem.of(TAB_EXHIBITION,ExhibitionFragment.class));
        items.add(FragmentPagerItem.of(TAB_SUPPLY,SupplyFragment.class));
        items.add(FragmentPagerItem.of(TAB_DEMAND,DemandFragment.class));
        adapter = new FragmentPagerItemAdapter(getSupportFragmentManager(),items);
        viewPager.setAdapter(adapter);
        tabLayout.setViewPager(viewPager);
        avatar = (CircleImageView) hearViewLayout.findViewById(R.id.profile_image);
        tvUserName = (TextView) hearViewLayout.findViewById(R.id.tv_name);
    }

    @Override
    protected void setListener() {
        setSupportActionBar(toolbar);
        drawer.addDrawerListener(toggle);
        navigationView.setNavigationItemSelectedListener(this);
        btnLogin.setOnClickListener(this);
        btnLogout.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        mainPresenter.loadUserInfo();
    }

    @Override
    protected void initPresenter() {
        mainPresenter = new MainPresenter(mContext,this);
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            mainPresenter.setHost();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_person) {
            mainPresenter.toUserInfoActivity();
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                LoginActivity.invoke(mContext);
                finish();
                break;
            case R.id.btn_logout:
                SpUtils.saveUser(null);
                LoginActivity.invoke(mContext);
                finish();
                break;
        }
    }

    @Override
    public void initUserInfo(Object user) {
        if (user instanceof PersonUser){
            PersonUser personUser = (PersonUser) user;
            if (!StringUtils.isStrNull(personUser.getAvatar())){
                Log.e("TAG",SpUtils.getHost()+personUser.getAvatar().replace("\\","/"));
                loadImage(SpUtils.getHost()+personUser.getAvatar().replace("\\","/"),avatar);
            }
            if (!StringUtils.isStrNull(personUser.getName())){
                tvUserName.setText(personUser.getName());
            }
        }else if(user instanceof CompanyUser){
            CompanyUser companyUser = (CompanyUser) user;
            if (!StringUtils.isStrNull(companyUser.getAvatar())){
                loadImage(SpUtils.getHost()+companyUser.getAvatar().replace("\\","/"),avatar);
            }
            if (!StringUtils.isStrNull(companyUser.getName())){
                tvUserName.setText(companyUser.getName());
            }
        }

    }
}
