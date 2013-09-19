package app.weight.tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class WeightDBHelper extends SQLiteOpenHelper {

    public static final String KEY_DATE = "date";
    public static final String KEY_WEIGHT = "weight";

    private static final String DATABASE_NAME = "weighttracker.db";
    private static final int DATABASE_VERSION = 1;
    private static final String WEIGHT_TABLE_NAME = "weight";
    private static final String WEIGHT_TABLE_CREATE =
            "create table " + WEIGHT_TABLE_NAME + " (" +
            KEY_DATE + " integer, " +
            KEY_WEIGHT + " float);";

    public WeightDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(WEIGHT_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }

    public void insert(long dateInMilliseconds, float weight) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, dateInMilliseconds);
        values.put(KEY_WEIGHT, weight);

        db.insert(WEIGHT_TABLE_NAME, null, values);
        db.close();
    }

    public void update(long dateInMilliseconds, float weight) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_WEIGHT, weight);

        db.update(WEIGHT_TABLE_NAME, values, KEY_DATE + " = " + dateInMilliseconds, null);
        db.close();
    }

    public void delete(long dateInMilliseconds) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(WEIGHT_TABLE_NAME, KEY_DATE + " = " + dateInMilliseconds, null);
        db.close();
    }

    public ArrayList<Weight> getAllWeights() {
        ArrayList<Weight> weights = new ArrayList<Weight>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(WEIGHT_TABLE_NAME, null, null, null, null, null, KEY_DATE + " DESC");

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Weight weight = new Weight();
                weight.dateInMilliseconds = cursor.getLong(0);
                weight.weight = cursor.getInt(1);
                weights.add(weight);
            }
            cursor.close();
        }

        db.close();
        return weights;
    }

    public boolean exist(long dateInMilliseconds) {
        boolean result = false;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(WEIGHT_TABLE_NAME, null, KEY_DATE + " = " + dateInMilliseconds, null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            result = true;
        }

        db.close();
        return result;
    }
}
