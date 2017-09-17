package org.ei.opensrp.drishti.Fragments;


import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import org.ei.opensrp.drishti.DataModels.PregnantMom;
import org.ei.opensrp.drishti.R;
import org.ei.opensrp.drishti.util.DatesHelper;
import org.ei.opensrp.view.activity.SecuredNativeSmartRegisterActivity;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * A simple {@link Fragment} subclass.
 */
public class CHWPreRegisterFormFragment extends Fragment {
    private static final String TAG = AncRegisterFormFragment.class.getSimpleName();
    public static TextView textDate, textPhone, textDateLNMP, textEDD;
    LinearLayout layoutDatePick, layoutEditPhone;
    CardView cardDatePickLNMP;
    public static EditText editTextMotherName,editTextClinicName, editTextMotherId, editTextMotherAge,
            editTextHeight, editTextPregCount, editTextBirthCount, editTextChildrenCount,
            editTextDiscountId, editTextMotherOccupation, editTextPhysicalAddress,
            editTextHusbandName, editTextHusbandOccupation;
    public static Button button;
    public static RadioGroup radioGroupPregnancyAge;
    public static MaterialSpinner spinnerMotherEducation, spinnerHusbandEducation;
    private ArrayAdapter<String> educationAdapter;

    private Calendar today;
    private List<String> educationList = new ArrayList<>();
    public String message = "";
    public static long edd, lnmp;
    public static Context context;
    public static int motherEduSelection = -1, husbandEduSelection = -1;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
    private String formName = "pregnant_mothers_pre_registration";
    private String recordId;
    private PregnantMom pregnantMom;
    private Gson gson = new Gson();
    private JSONObject fieldOverides = new JSONObject();

    public CHWPreRegisterFormFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        today = Calendar.getInstance();

        educationList.add("Primary Education");
        educationList.add("Ordinary Secondary Education");
        educationList.add("Advanced Secondary Education");
        educationList.add("College Education");
        educationList.add("Higher Education");

        context = getContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.activity_chwedit_pre_registration, container, false);


        textDate = (TextView) fragmentView.findViewById(R.id.textDate);
        textPhone = (TextView) fragmentView.findViewById(R.id.textPhone);
        textDateLNMP = (TextView) fragmentView.findViewById(R.id.textDateLNMP);
        textEDD = (TextView) fragmentView.findViewById(R.id.textEDD);
        layoutDatePick = (LinearLayout) fragmentView.findViewById(R.id.layoutDatePick);
        layoutEditPhone = (LinearLayout) fragmentView.findViewById(R.id.layoutEditPhone);
        cardDatePickLNMP = (CardView) fragmentView.findViewById(R.id.cardPickDateLNMP);


        editTextClinicName = (EditText) fragmentView.findViewById(R.id.editTextClinicName);
        editTextMotherName = (EditText) fragmentView.findViewById(R.id.editTextMotherName);
        editTextMotherId = (EditText) fragmentView.findViewById(R.id.editTextMotherId);
        editTextMotherAge = (EditText) fragmentView.findViewById(R.id.editTextMotherAge);
        editTextHeight = (EditText) fragmentView.findViewById(R.id.editTextHeight);
        editTextPregCount = (EditText) fragmentView.findViewById(R.id.editTextPregCount);
        editTextBirthCount = (EditText) fragmentView.findViewById(R.id.editTextBirthCount);
        editTextChildrenCount = (EditText) fragmentView.findViewById(R.id.editTextChildrenCount);
        editTextDiscountId = (EditText) fragmentView.findViewById(R.id.editTextDiscountId);
        editTextMotherOccupation = (EditText) fragmentView.findViewById(R.id.editTextMotherOccupation);
        editTextPhysicalAddress = (EditText) fragmentView.findViewById(R.id.editTextPhysicalAddress);
        editTextHusbandName = (EditText) fragmentView.findViewById(R.id.editTextHusbandName);
        editTextHusbandOccupation = (EditText) fragmentView.findViewById(R.id.editTextHusbandOccupation);

        button = (Button) fragmentView.findViewById(R.id.save);

        educationAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, educationList);
        educationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerMotherEducation = (MaterialSpinner) fragmentView.findViewById(R.id.spinnerMotherEducation);
        spinnerHusbandEducation = (MaterialSpinner) fragmentView.findViewById(R.id.spinnerHusbandEducation);
        spinnerMotherEducation.setAdapter(educationAdapter);
        spinnerHusbandEducation.setAdapter(educationAdapter);

        spinnerMotherEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    spinnerMotherEducation.setFloatingLabelText("Elimu Ya Mama");
                    motherEduSelection = i;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerHusbandEducation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i >= 0) {
                    spinnerHusbandEducation.setFloatingLabelText("Elimu Ya Mume/Mwenza");
                    husbandEduSelection = i;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        spinnerMotherEducation.setSelection(motherEduSelection);
        spinnerHusbandEducation.setSelection(husbandEduSelection);


        radioGroupPregnancyAge = (RadioGroup) fragmentView.findViewById(R.id.radioGroupPregnancyAge);

        // initialize date to today's date
        textDate.setText(dateFormat.format(today.getTimeInMillis()));

        layoutDatePick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // pick date
                pickDate(R.id.textDate);
            }
        });

        layoutEditPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // show edit phone dialog
                showEditPhoneDialog();
            }
        });

        cardDatePickLNMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDate(R.id.textDateLNMP);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // convert to json
                pregnantMom = getPregnantMom();
                String gsonMom = gson.toJson(pregnantMom);
                Log.d(TAG, "mom = " + gsonMom);

                // todo start form submission

                ((SecuredNativeSmartRegisterActivity) getActivity()).saveFormSubmission(gsonMom, recordId, formName, getFormFieldsOverrides());
                getActivity().finish();
            }
        });

        return fragmentView;
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
                if (id == R.id.textDate)
                    textDate.setText(dateFormat.format(pickedDate.getTimeInMillis()));

                else if (id == R.id.textDateLNMP) {
                    lnmp = pickedDate.getTimeInMillis();
                    textDateLNMP.setText(dateFormat.format(lnmp));
                    // update edd
                    edd = DatesHelper.calculateEDDFromLNMP(lnmp);
                    textEDD.setText(dateFormat.format(edd));
                }
            }
        };

        // dialog
        DatePickerDialog datePickerDialog = DatePickerDialog.newInstance(
                onDateSetListener);

        datePickerDialog.setOkColor(ContextCompat.getColor(getContext(), android.R.color.holo_blue_light));
        datePickerDialog.setCancelColor(ContextCompat.getColor(getContext(), android.R.color.holo_red_light));

        datePickerDialog.setVersion(DatePickerDialog.Version.VERSION_1);
        datePickerDialog.setAccentColor(ContextCompat.getColor(getContext(), R.color.primary));

        // show dialog
        datePickerDialog.show(getActivity().getFragmentManager(), "DatePickerDialog");
    }


    public void showEditPhoneDialog() {
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.layout_dialog_edit_phone, null);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        dialogBuilder.setView(dialogView);
        dialogBuilder.setCancelable(true);

        final AlertDialog dialog = dialogBuilder.create();
        dialog.show();

        final EditText editTextPhone = (EditText) dialogView.findViewById(R.id.editTextLocation);
        // get previously entered location
        if (textPhone.getText() != null)
            editTextPhone.setText(textPhone.getText());

        // positive button
        dialogView.findViewById(R.id.textOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone = editTextPhone.getText().toString();

                if (TextUtils.isEmpty(phone)) {
                    editTextPhone.setError("Please enter a valid phone number.");
                    return;
                }

                // update view
                textPhone.setText(phone);

                // close dialog
                dialog.dismiss();
            }
        });

        // negative button
        dialogView.findViewById(R.id.textCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // close dialog
                dialog.dismiss();
            }
        });
    }

    public boolean isFormSubmissionOk() {
        if (TextUtils.isEmpty(editTextMotherName.getText())
                || TextUtils.isEmpty(editTextMotherId.getText())
                || TextUtils.isEmpty(editTextMotherAge.getText())
                || TextUtils.isEmpty(editTextHeight.getText())
                || TextUtils.isEmpty(editTextPregCount.getText())
                || TextUtils.isEmpty(editTextBirthCount.getText())
                || TextUtils.isEmpty(editTextChildrenCount.getText())
                || TextUtils.isEmpty(textPhone.getText())
                || TextUtils.isEmpty(textDateLNMP.getText())
                || TextUtils.isEmpty(editTextDiscountId.getText())
                || TextUtils.isEmpty(editTextMotherOccupation.getText())
                || TextUtils.isEmpty(editTextPhysicalAddress.getText())
                || TextUtils.isEmpty(editTextHusbandName.getText())
                || TextUtils.isEmpty(editTextHusbandOccupation.getText())) {

            message = "Tafadhali jaza taarifa zote muhimu";
            makeToast();

            return false;

        } else if (radioGroupPregnancyAge.getCheckedRadioButtonId() == -1) {
            // no radio checked
            message = "Tafadhali chagua umri wa ujauzito.";
            makeToast();
            return false;

        } else if (spinnerMotherEducation.getSelectedItemPosition() < 0
                || spinnerHusbandEducation.getSelectedItemPosition() < 0) {

            message = "Tafadhali chagua elimu ya mama na mwenza.";
            makeToast();
            return false;

        } else if ((int) lnmp == 0) {
            message = "Tafadhali chagua tarehe ya LNMP.";
            makeToast();
            return false;

        } else
            // all good
            return true;
    }

    public PregnantMom getPregnantMom() {
        PregnantMom mom = new PregnantMom();

        mom.setFacilityId(editTextClinicName.getText().toString());
        mom.setName(editTextMotherName.getText().toString());
        mom.setId(editTextMotherId.getText().toString());
        mom.setPhone(textPhone.getText().toString());
        mom.setAge(Integer.valueOf(editTextMotherAge.getText().toString()));
        mom.setHeight(Integer.valueOf(editTextHeight.getText().toString()));
        mom.setPreviousFertilityCount(Integer.valueOf(editTextPregCount.getText().toString()));
        mom.setSuccessfulBirths(Integer.valueOf(editTextBirthCount.getText().toString()));
        mom.setLivingChildren(Integer.valueOf(editTextChildrenCount.getText().toString()));
        mom.setAbove20WeeksPregnant(radioGroupPregnancyAge.getCheckedRadioButtonId() == R.id.radioAbove20);
        mom.setDiscountId(editTextDiscountId.getText().toString());
        mom.setEducation(spinnerMotherEducation.getSelectedItem().toString());
        mom.setOccupation(editTextMotherOccupation.getText().toString());
        mom.setPhysicalAddress(editTextPhysicalAddress.getText().toString());
        mom.setHusbandName(editTextHusbandName.getText().toString());
        mom.setHusbandEducation(spinnerHusbandEducation.getSelectedItem().toString());
        mom.setHusbandOccupation(editTextHusbandOccupation.getText().toString());
        mom.setDateLNMP(lnmp);
        mom.setEdd(edd);

        return mom;
    }
    public JSONObject getFormFieldsOverrides() {
        return fieldOverides;
    }
    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }
    private void makeToast() {
        Toast.makeText(context,
                message,
                Toast.LENGTH_LONG).show();
    }
}
