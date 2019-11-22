package com.example.jarvis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import static com.example.jarvis.BlueToothActivity.bt;
import android.os.Bundle;
import android.widget.Toast;

import com.yinglan.alphatabs.AlphaTabsIndicator;

import app.akexorcist.bluetotohspp.library.BluetoothSPP;


public class MainActivity extends AppCompatActivity
{
    ViewPager vp;
    private AlphaTabsIndicator alphaTabsIndicator;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager)findViewById(R.id.mViewPager);

        vp.setAdapter(new pagerAdapter(getSupportFragmentManager()));
        vp.setCurrentItem(0);

        alphaTabsIndicator = (AlphaTabsIndicator) findViewById(R.id.alphaIndicator);
        alphaTabsIndicator.setViewPager(vp);


    }


    private class pagerAdapter extends FragmentStatePagerAdapter
    {
        public pagerAdapter(FragmentManager fm)
        {
            super(fm);
        }
        @Override
        public Fragment getItem(int position)
        {
            switch(position)
            {
                case 0:
                    return new FragColor();
                case 1:
                    return new FragDust();
                case 2:
                    return new FragMode();
                default:
                    return null;
            }
        }
        @Override
        public int getCount()
        {
            return 3;
        }
    }
}
