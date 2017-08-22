package org.ei.opensrp.mcare;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.ei.opensrp.mcare.R;
import org.ei.opensrp.mcare.adapter.ANCRegisterPagerAdapter;

public class ANCRegisterFormActivity extends ActionBarActivity {

    private ViewPager viewPager;

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ancregister_form);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        ANCRegisterPagerAdapter pagerAdapter
                = new ANCRegisterPagerAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerAdapter);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }
}
