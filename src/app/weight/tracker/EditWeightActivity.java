package app.weight.tracker;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class EditWeightActivity extends Activity {

    public static final String KEY_WEIGHT_OPERATION = "key_weight_operation";
    public static final int VALUE_ADD_WEIGHT = 0;
    public static final int VALUE_EDIT_WEIGHT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_weight);

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
        finish();
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_weight_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
