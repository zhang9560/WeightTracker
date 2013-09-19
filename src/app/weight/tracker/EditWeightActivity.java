package app.weight.tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class EditWeightActivity extends Activity {

    public static final String KEY_WEIGHT_OPERATION = "key_weight_operation";
    public static final int VALUE_ADD_WEIGHT = 0;
    public static final int VALUE_EDIT_WEIGHT = 1;

    public static final int RESULT_DATE_EXIST = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_weight);

        mDatePicker = (DatePicker)findViewById(R.id.date_picker);
        mEditText = (EditText)findViewById(R.id.edit_text);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        mAlertDialog = builder.setTitle(R.string.error).setMessage(R.string.must_input_weight)
                .setCancelable(true).setNegativeButton(R.string.done, null).create();

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(false);

        Intent intent = getIntent();
        switch (intent.getIntExtra(KEY_WEIGHT_OPERATION, 0)) {
            case VALUE_ADD_WEIGHT:
                actionBar.setTitle(R.string.add_weight);
                break;
            case VALUE_EDIT_WEIGHT:
                actionBar.setTitle(R.string.edit_weight);
                long dateInMilliseconds = intent.getLongExtra(WeightDBHelper.KEY_DATE, 0);
                Calendar calendar = toCalendar(dateInMilliseconds);
                mDatePicker.updateDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH));
                mDatePicker.setEnabled(false);
                mEditText.setText(intent.getStringExtra(WeightDBHelper.KEY_WEIGHT));
                break;
        }
    }

    @Override
    public boolean onNavigateUp() {
        setResult(RESULT_CANCELED);
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_weight_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done:
                String strWeight = mEditText.getText().toString();

                if (!strWeight.isEmpty()) {
                    float weight = Float.valueOf(strWeight);
                    long date = getMilliseconds(mDatePicker.getYear(), mDatePicker.getMonth(),
                            mDatePicker.getDayOfMonth());
                    WeightDBHelper dbHelper = new WeightDBHelper(this);

                    Intent intent = new Intent();
                    intent.putExtra(WeightDBHelper.KEY_DATE, date);
                    intent.putExtra(WeightDBHelper.KEY_WEIGHT, weight);

                    if (!dbHelper.exist(date)) {
                        dbHelper.insert(date, weight);
                        setResult(RESULT_OK, intent);
                    } else {
                        dbHelper.update(date, weight);
                        setResult(RESULT_DATE_EXIST, intent);
                    }

                    finish();
                } else {
                    mAlertDialog.show();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private long getMilliseconds(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTimeInMillis();
    }

    private Calendar toCalendar(long dateInMilliseconds) {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTimeInMillis(dateInMilliseconds);
        return calendar;
    }

    private DatePicker mDatePicker;
    private EditText mEditText;
    private AlertDialog mAlertDialog;
}
