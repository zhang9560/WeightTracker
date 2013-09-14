package app.weight.tracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeightDBHelper extends SQLiteOpenHelper {

    public static final String KEY_DATE = "date";
    public static final String KEY_WEIGHT = "weight";

    private static final String DATABASE_NAME = "weighttracker.db";
    private static final int DATABASE_VERSION = 1;
    private static final String WEIGHT_TABLE_NAME = "weight";
    private static final String WEIGHT_TABLE_CREATE =
            "CREATE TABLE " + WEIGHT_TABLE_NAME + " (" +
            KEY_DATE + " integer, " +
            KEY_WEIGHT + " integer);";

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

    public void insert(long dateInMilliseconds, int weight) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_DATE, dateInMilliseconds);
        values.put(KEY_WEIGHT, weight);

        db.insert(WEIGHT_TABLE_NAME, null, values);
        db.close();
    }
}
