package org.ei.opensrp.mcare;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import org.ei.opensrp.mcare.household.HouseHoldRegisterFormActivity;

public class ChooseFormActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_form);


        findViewById(R.id.buttonHHRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseFormActivity.this,
                        HouseHoldRegisterFormActivity.class));
            }
        });

        findViewById(R.id.buttonANCRegister).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChooseFormActivity.this,
                        ANCRegisterFormActivity.class));
            }
        });
    }
}
