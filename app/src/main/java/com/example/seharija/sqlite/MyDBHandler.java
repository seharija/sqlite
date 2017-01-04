package com.example.seharija.sqlite;

// This class handles all the database activities
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "patientDB.db";
    public static final String TABLE_PATIENTS = "patients";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PATIENTNAME = "patientname";

    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PATIENTS + "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PATIENTNAME + " TEXT " +
                ");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PATIENTS);
        onCreate(db);
    }

    //Add a new row to the database
    public void addPatient(Patients patient){
        ContentValues values = new ContentValues();
        values.put(COLUMN_PATIENTNAME, patient.get_patientname());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_PATIENTS, null, values);
        db.close();
    }

    //Delete a patient from the database
    public void deletePatient(String patientName){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_PATIENTS + " WHERE " + COLUMN_PATIENTNAME + "=\"" + patientName + "\";");
    }

    // this is goint in record_TextView in the Main activity.
    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_PATIENTS + " WHERE 1";

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex("patientname")) != null) {
                dbString += recordSet.getString(recordSet.getColumnIndex("patientname"));
                dbString += "\n";
            }
            recordSet.moveToNext();
        }
        db.close();
        return dbString;
    }

}