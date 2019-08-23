package com.example.user.buttomnavigation_test.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.user.buttomnavigation_test.MyFragment;
import com.example.user.buttomnavigation_test.Page1Fragment;
import com.example.user.buttomnavigation_test.Page2Fragment;
import com.example.user.buttomnavigation_test.Page3Fragment;
import com.example.user.buttomnavigation_test.Page4Fragment;
import com.example.user.buttomnavigation_test.R;

public class TopActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener {

    private ViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        initView();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.page1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.page2:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.page3:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.page4:
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.page5:
                    viewPager.setCurrentItem(4);
                    break;
            }
            return false;
        }
    };

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        //点击BottomNavigationView的Item项，切换ViewPager页面
        //menu/navigation.xml里加的android:orderInCategory属性就是下面item.getOrder()取的值
        viewPager.setCurrentItem(menuItem.getOrder());
        return true;
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.my_search, menu);
//        return true;
//
//    }


    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        //页面滑动的时候，改变BottomNavigationView的Item高亮
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void initView() {
        //設定左上角的圖示
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        SearchView searchView = (SearchView) findViewById(R.id.searchView);
        //   searchView.setIconifiedByDefault(false); //直接定位到搜尋欄 + 右側放大鏡
        searchView.setIconified(false);      //直接定位到搜尋欄 + 沒有放大鏡
        //  searchView.onActionViewExpanded();

        searchView.setFocusable(false);
//        searchView.setFocusableInTouchMode(true);
        searchView.clearFocus();
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open_drewer, R.string.nav_close_drewer);
        // 設定圖示的監聽器
        drawerLayout.addDrawerListener(toggle);
        //同步圖示的狀態
        toggle.syncState();

        //設定側攔 Navigation 的監聽器
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.addOnPageChangeListener(this);
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return 5;
            }

            @Override
            public Fragment getItem(int position) {
                switch (position) {
                    case 0:
                        return new Page1Fragment();
                    case 1:
                        return new Page2Fragment();
                    case 2:
                        return new Page3Fragment();
                    case 3:
                        return new Page4Fragment();
                    case 4:
                        return new MyFragment();
                }
                return null;
            }
        });
    }

}
