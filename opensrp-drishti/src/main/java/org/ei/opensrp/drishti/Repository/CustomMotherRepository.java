package org.ei.opensrp.drishti.Repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.google.gson.Gson;

import net.sqlcipher.database.SQLiteDatabase;

import org.ei.opensrp.commonregistry.CommonFtsObject;
import org.ei.opensrp.commonregistry.CommonPersonObject;
import org.ei.opensrp.commonregistry.CommonRepository;
import org.ei.opensrp.drishti.DataModels.PregnantMom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Kency on 14/09/2017.
 */

public class CustomMotherRepository extends CommonRepository {

    public static final String MOTHERS_FIRST_NAME = "MOTHERS_FIRST_NAME";
    public static final String MOTHERS_LAST_NAME = "MOTHERS_LAST_NAME";
    public static final String MOTHERS_SORTVALUE = "MOTHERS_SORTVALUE";
    public static final String EXPECTED_DELIVERY_DATE = "EXPECTED_DELIVERY_DATE";
    public static final String MOTHERS_LAST_MENSTRUATION_DATE = "MOTHERS_LAST_MENSTRUATION_DATE";
    public static final String FACILITY_ID = "FACILITY_ID";
    public static final String IS_PNC = "IS_PNC";
    public static final String IS_VALID = "IS_VALID";
    public static final String PNC_STATUS = "PNC_STATUS";
    public static final String MOTHERS_ID = "MOTHERS_ID";

    public CustomMotherRepository(String tablename, String[] columns) {
        super(tablename, columns);
    }

    public CustomMotherRepository(CommonFtsObject commonFtsObject, String tablename, String[] columns) {
        super(commonFtsObject, tablename, columns);
    }

    public void add(MotherPersonObject motherPersonObject) {
        SQLiteDatabase database = masterRepository.getWritableDatabase();
        database.insert(TABLE_NAME, null, createValuesFor(motherPersonObject));
    }

    private ContentValues createValuesFor(MotherPersonObject motherPersonObject) {
        ContentValues values = new ContentValues();
        values.put(ID_COLUMN, motherPersonObject.getCaseId());
        values.put(Relational_ID, motherPersonObject.getRelationalId());
        values.put(MOTHERS_FIRST_NAME, motherPersonObject.getMOTHERS_FIRST_NAME());
        values.put(MOTHERS_LAST_NAME, motherPersonObject.getMOTHERS_LAST_NAME());
        values.put(MOTHERS_ID, motherPersonObject.getMOTHERS_ID());
        values.put(MOTHERS_SORTVALUE, motherPersonObject.getMOTHERS_SORTVALUE());
        values.put(MOTHERS_LAST_MENSTRUATION_DATE, motherPersonObject.getMOTHERS_LAST_MENSTRUATION_DATE());
        values.put(EXPECTED_DELIVERY_DATE, motherPersonObject.getEXPECTED_DELIVERY_DATE());
        values.put(IS_PNC, motherPersonObject.getIS_PNC());
        values.put(PNC_STATUS, motherPersonObject.getPNC_STATUS());
        values.put(IS_VALID, motherPersonObject.getIS_VALID());
        values.put(FACILITY_ID, motherPersonObject.getFACILITY_ID());
        values.put(DETAILS_COLUMN, new Gson().toJson(motherPersonObject.getDetails()));
        return values;
    }

//    @Override
//    public Cursor RawCustomQueryForAdapter(String query) {
////        return super.RawCustomQueryForAdapter(query);
//        Log.d(getClass().getSimpleName(), query);
//        SQLiteDatabase database = masterRepository.getReadableDatabase();
//        // get cursor
//        return database.rawQuery(query, null);
//    }


    public List<MotherPersonObject> readAllMotherForField(Cursor cursor, String tableName) {
        List<MotherPersonObject> mothers = new ArrayList<>();
        try {
            cursor.moveToFirst();

            while (!cursor.isAfterLast()) {
                int columnCount = cursor.getColumnCount();
                HashMap<String, String> columns = new HashMap<>();
                for (int i = 0; i < columnCount; i++) {
                    columns.put(cursor.getColumnName(i), String.valueOf(cursor.getInt(i)));
                }

                MotherPersonObject motherPersonObject = new MotherPersonObject(
                        columns.get("caseId"),
                        columns.get("relationalId"),
                        new Gson().fromJson(columns.get("details"), PregnantMom.class));

                motherPersonObject.setcolumnMap(columns);

                mothers.add(motherPersonObject);
                cursor.moveToNext();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cursor.close();
        }

        return mothers;
    }

}