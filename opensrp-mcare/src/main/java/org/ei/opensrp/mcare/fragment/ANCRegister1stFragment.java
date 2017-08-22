package org.ei.opensrp.mcare.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.ei.opensrp.mcare.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 */
public class ANCRegister1stFragment extends Fragment {

    TextView textDate;
    LinearLayout layoutDatePick;

    private Calendar today;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());

    public ANCRegister1stFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        today = Calendar.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_ancregister1st, container, false);

        textDate = (TextView) fragmentView.findViewById(R.id.textDate);
        layoutDatePick = (LinearLayout) fragmentView.findViewById(R.id.layoutDatePick);

        // initialize date to today's date
        textDate.setText(dateFormat.format(today.getTimeInMillis()));

        layoutDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pick date
            }
        });


        return fragmentView;
    }

}
