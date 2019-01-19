package com.example.addcarts.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.addcarts.R;
import com.example.addcarts.fragment.FragmentFive;
import com.example.addcarts.fragment.FragmentFour;
import com.example.addcarts.fragment.FragmentOne;
import com.example.addcarts.fragment.FragmentThree;
import com.example.addcarts.fragment.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    viewpager.setCurrentItem(0);
                    return true;
                case R.id.navigation_dashboar:
                    viewpager.setCurrentItem(1);
                    return true;
                case R.id.navigation_dashboa:
                    viewpager.setCurrentItem(2);
                    return true;
                case R.id.navigation_dashboard:
                    viewpager.setCurrentItem(3);
                    return true;
                case R.id.navigation_notifications:
                    viewpager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewpager =findViewById(R.id.viewpager);
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new FragmentOne();
                    case 1:
                        return new FragmentTwo();
                    case 2:
                        return new FragmentThree();
                    case 3:
                        return new FragmentFour();
                    case 4:
                        return new FragmentFive();
                }
                return null;
            }

            @Override
            public int getCount() {
                return 5;
            }
        });
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_dashboar);
                        break;
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_dashboa);
                        break;
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                        break;
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

}
