package org.ei.opensrp.mcare.chw;

import android.app.AlertDialog;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.ei.opensrp.mcare.R;
import org.ei.opensrp.mcare.datamodels.ChwFollowUpMother;
import org.ei.opensrp.mcare.datamodels.PreRegisteredMother;
import org.ei.opensrp.mcare.datamodels.PregnantMom;
import org.ei.opensrp.mcare.pageradapter.CHWPagerAdapter;

public class CHWRegisterActivity extends AppCompatActivity {

    private TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chwregister);
        CHWPagerAdapter adapter = new CHWPagerAdapter(getSupportFragmentManager());

        ViewPager feeds = (ViewPager) findViewById(R.id.viewPager);
        feeds.setAdapter(adapter);

        feeds.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(feeds);

        ((TextView)findViewById(R.id.txt_title_label)).setText("Community HW");
        // tabs icons
        tabs.getTabAt(0).setIcon(R.drawable.ic_account_circle);
        tabs.getTabAt(1).setIcon(R.drawable.ic_message_bulleted);

        final int colorWhite = ContextCompat.getColor(getApplicationContext(), android.R.color.white);
        final int colorPrimaryLight = ContextCompat.getColor(getApplicationContext(), R.color.primary_light);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getIcon() != null)
                    tab.getIcon().setColorFilter(colorWhite, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getIcon() != null)
                    tab.getIcon().setColorFilter(colorPrimaryLight, PorterDuff.Mode.SRC_IN);

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }


    public void showPreRegistrationDetailsDialog(PreRegisteredMother mother){

        final View dialogView = getLayoutInflater().inflate(R.layout.fragment_chwregistration_details, null);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CHWRegisterActivity.this);
        dialogBuilder.setView(dialogView)
                .setCancelable(false);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        ImageView cancel = (ImageView) dialogView.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // TODO: findviewbyid that are on the dialog layout
        // example
        TextView textName = (TextView) dialogView.findViewById(R.id.name);
        textName.setText(mother.getName());
    }

    public void showPreRegistrationVisitDialog(final PreRegisteredMother mother){

        final View dialogView = getLayoutInflater().inflate(R.layout.fragment_chwregistration_visit_details, null);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CHWRegisterActivity.this);
        dialogBuilder.setView(dialogView)
                .setCancelable(false);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();
        Button button_yes = (Button) dialogView.findViewById(R.id.button_yes);
        Button button_no = (Button) dialogView.findViewById(R.id.button_no);

        button_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CHWRegisterActivity.this, "Asante kwa kumtembelea tena "+ mother.getName(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        button_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // TODO: findviewbyid that are on the dialog layout
        // example
        TextView textName = (TextView) dialogView.findViewById(R.id.name);
        textName.setText(mother.getName());
    }

    public void showFollowUpDetailsDialog(ChwFollowUpMother mother){

        final View dialogView = getLayoutInflater().inflate(R.layout.fragment_chwfollow_details, null);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CHWRegisterActivity.this);
        dialogBuilder.setView(dialogView)
                .setCancelable(false);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        ImageView cancel = (ImageView) dialogView.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // TODO: findviewbyid that are on the dialog layout
        // example
        TextView textName = (TextView) dialogView.findViewById(R.id.name);
        textName.setText(mother.getName());
    }

    public void showFollowUpFormDialog(ChwFollowUpMother mother){

        final View dialogView = getLayoutInflater().inflate(R.layout.fragment_chwfollow_visit_details, null);

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(CHWRegisterActivity.this);
        dialogBuilder.setView(dialogView)
                .setCancelable(false);
        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        ImageView cancel = (ImageView) dialogView.findViewById(R.id.cancel_action);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        // TODO: findviewbyid that are on the dialog layout
        // example
        TextView textName = (TextView) dialogView.findViewById(R.id.name);
        textName.setText(mother.getName());
    }


}
