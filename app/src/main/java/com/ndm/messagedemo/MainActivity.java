package com.ndm.messagedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;
import com.ndm.messagedemo.Fragment.HomeFragment;
import com.ndm.messagedemo.ViewPager.ViewPagerAdapter;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mnavigationView;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mnavigationView = findViewById(R.id.bottom_nav);
        mViewPager = findViewById(R.id.viewpager);


        setupViewPager();
        mnavigationView.setOnItemSelectedListener(item -> {
            int i = item.getItemId();
            if (i == R.id.chat) {
                mViewPager.setCurrentItem(0);
                Toast.makeText(MainActivity.this, "Chat", Toast.LENGTH_SHORT).show();
            }
            if (i == R.id.cam) {
                mViewPager.setCurrentItem(1);
                Toast.makeText(MainActivity.this, "Cuộc gọi", Toast.LENGTH_SHORT).show();
            }
            if (i == R.id.phonebook) {
                mViewPager.setCurrentItem(2);
                Toast.makeText(MainActivity.this, "Danh bạ", Toast.LENGTH_SHORT).show();
            }
            if (i == R.id.story) {
                mViewPager.setCurrentItem(3);
                Toast.makeText(MainActivity.this, "Tin", Toast.LENGTH_SHORT).show();
            }

            return true;
        });
    }

    private void setupViewPager() {
        ViewPagerAdapter viewPagerAdapter;
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mViewPager.setAdapter(viewPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mnavigationView.getMenu().findItem(R.id.chat).setChecked(true);
                        break;
                    case 1:
                        mnavigationView.getMenu().findItem(R.id.cam).setChecked(true);
                        break;
                    case 2:
                        mnavigationView.getMenu().findItem(R.id.phonebook).setChecked(true);
                        break;
                    case 3:
                        mnavigationView.getMenu().findItem(R.id.story).setChecked(true);
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        int currentItem = mViewPager.getCurrentItem();
        if (currentItem == 0) {
            // Hiển thị lại các thành phần đã bị ẩn trong HomeFragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            HomeFragment homeFragment = (HomeFragment) fragmentManager.findFragmentById(R.id.containerProductDetail);
            if (homeFragment != null && homeFragment.isVisible()) {
                BottomNavigationView bottomNav = findViewById(R.id.bottom_nav);
                bottomNav.setVisibility(View.VISIBLE);

                // Hiển thị các view đã bị ẩn
                homeFragment.showHiddenComponents();

                return;
            }
        }

        super.onBackPressed();
    }




}