package org.ei.opensrp.mcare;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import org.ei.opensrp.mcare.R;

public class ANCRegisterFormActivity extends ActionBarActivity {

    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ancregister_form);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
    }
}
