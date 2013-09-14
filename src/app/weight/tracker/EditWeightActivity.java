package app.weight.tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.GregorianCalendar;

public class EditWeightActivity extends Activity {

    public static final String KEY_WEIGHT_OPERATION = "key_weight_operation";
    public static final int VALUE_ADD_WEIGHT = 0;
    public static final int VALUE_EDIT_WEIGHT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_weight);

        mDatePicker = (DatePicker)findViewById(R.id.date_picker);
        mEditText = (EditText)findViewById(R.id.edit_text);

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
                long date = getMilliseconds(mDatePicker.getYear(), mDatePicker.getMonth(), mDatePicker.getDayOfMonth());
                int weight = Integer.valueOf(mEditText.getText().toString());

                new WeightDBHelper(this).insert(date, weight);

                Intent intent = new Intent();
                intent.putExtra(WeightDBHelper.KEY_DATE, date);
                intent.putExtra(WeightDBHelper.KEY_WEIGHT, weight);
                setResult(RESULT_OK, intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private long getMilliseconds(int year, int month, int day) {
        GregorianCalendar calendar = new GregorianCalendar(year, month, day);
        return calendar.getTimeInMillis();
    }

    private DatePicker mDatePicker;
    private EditText mEditText;
}
