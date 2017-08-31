package org.ei.opensrp.mcare;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;

public class MaternityRegisterActivity extends AppCompatActivity {

    CardView cardDatePickDelivery, cardDatePickAdmission;
    TextView textDateDelivery, textDateAdmission;
    MaterialSpinner spinnerDeliveryMethod;
    ArrayAdapter<String> methodsAdapter;
    EditText editTextIdNo, editTextMotherName, editTextAge,
            editTextPara, editTextDeliveryProblems;
    CheckBox checkBoxBBA;


    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private Calendar today;
    private static final String TAG = MaternityRegisterActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maternity_register);

        cardDatePickAdmission = (CardView) findViewById(R.id.cardPickDateAdmission);
        cardDatePickDelivery = (CardView) findViewById(R.id.cardPickDateDelivery);
        textDateDelivery = (TextView) findViewById(R.id.textDateDelivery);
        textDateAdmission = (TextView) findViewById(R.id.textDateAdmission);
        editTextIdNo = (EditText) findViewById(R.id.editTextIdNo);
        editTextMotherName = (EditText) findViewById(R.id.editTextMotherName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextPara = (EditText) findViewById(R.id.editTextPara);
        editTextDeliveryProblems = (EditText) findViewById(R.id.editTextDeliveryProblems);
        checkBoxBBA = (CheckBox) findViewById(R.id.checkboxBBA);
        spinnerDeliveryMethod = (MaterialSpinner) findViewById(R.id.spinnerDeliveryMethod);
        // spinner adapter
        methodsAdapter = new ArrayAdapter<>(
                MaternityRegisterActivity.this,
                android.R.layout.simple_spinner_dropdown_item,
                getDeliveryMethods());
        methodsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDeliveryMethod.setAdapter(methodsAdapter);

        today = Calendar.getInstance();
        textDateDelivery.setText(dateFormat.format(today.getTimeInMillis()));

        cardDatePickDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do magic
                pickDate(R.id.textDateDelivery);
            }
        });

        cardDatePickAdmission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do magic
                pickDate(R.id.textDateAdmission);
            }
        });

        spinnerDeliveryMethod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemSelected:position=" + i +
                        ", value=" + adapterView.getSelectedItem());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        checkBoxBBA.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Log.d(TAG, "onCheckedChanged: checked=" + isChecked);
            }
        });
    }


    private void pickDate(final int id) {
        // listener
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                // get picked date
                // update view
                GregorianCalendar pickedDate = new GregorianCalendar(year, monthOfYear, dayOfMonth);
                if (id == R.id.textDateAdmission)
                    textDateAdmission.setText(dateFormat.format(pickedDate.getTimeInMillis()));

                else if (id == R.id.textDateDelivery)
                    textDateDelivery.setText(dateFormat.format(pickedDate.getTimeInMillis()));
            }
        };

        // dialog
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                onDateSetListener);

        datePickerDialog.setOkColor(ContextCompat.getColor(getApplicationContext(),
                android.R.color.holo_blue_light));
        datePickerDialog.setCancelColor(ContextCompat.getColor(getApplicationContext(),
                android.R.color.holo_red_light));

        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_2);
        datePickerDialog.setAccentColor(ContextCompat.getColor(getApplicationContext(), R.color.primary));

        // show dialog
        datePickerDialog.show(getFragmentManager(), "DatePickerDialog");
    }

    private List<String> getDeliveryMethods() {
        List<String> methodList = new ArrayList<>();
        methodList.add("Method 1");
        methodList.add("Method 2");
        methodList.add("Method 3");

        return methodList;
    }
}
