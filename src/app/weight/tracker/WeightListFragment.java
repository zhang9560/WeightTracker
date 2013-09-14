package app.weight.tracker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;
import android.widget.Toast;
import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeightListFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCardsAdapter = new CardAdapter<Card>(getActivity());
        mDBHelper = new WeightDBHelper(getActivity());

        GregorianCalendar calendar = new GregorianCalendar();
        ArrayList<Weight> weights = mDBHelper.getAllWeights();
        for (Weight weight : weights) {
            calendar.setTimeInMillis(weight.dateInMilliseconds);
            String date = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            mCardsAdapter.add(new Card(date, String.format("Weight : %dkg", weight.weight)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CardListView cardList = (CardListView)inflater.inflate(R.layout.weight_list, container, false);
        cardList.setAdapter(mCardsAdapter);

        return cardList;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            long dateInMilliseconds = data.getLongExtra(WeightDBHelper.KEY_DATE, 0);
            int weight = data.getIntExtra(WeightDBHelper.KEY_WEIGHT, 0);
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTimeInMillis(dateInMilliseconds);
            String date = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            mCardsAdapter.add(0, new Card(date, String.format("Weight : %dkg", weight)));
        } else if (resultCode == EditWeightActivity.RESULT_DATE_EXIST) {
            Toast.makeText(getActivity(), "Do not add", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_weight_actions, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(getActivity(), EditWeightActivity.class);
                intent.putExtra(EditWeightActivity.KEY_WEIGHT_OPERATION, EditWeightActivity.VALUE_ADD_WEIGHT);
                startActivityForResult(intent, 0);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private CardAdapter mCardsAdapter;
    private WeightDBHelper mDBHelper;
}
