package app.weight.tracker;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeightListFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardsAdapter = new CardAdapter<Card>(getActivity());

        GregorianCalendar calendar = new GregorianCalendar();
        ArrayList<Weight> weights = new WeightDBHelper(getActivity()).getAllWeights();
        for (Weight weight : weights) {
            calendar.setTimeInMillis(weight.dateInMilliseconds);
            String date = String.format("%d-%d-%d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            mCardsAdapter.add(new Card(date, String.format("Weight : %dkg", weight.weight)));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View)inflater.inflate(R.layout.weight_list, container, false);
        CardListView cardList = (CardListView)rootView.findViewById(R.id.card_list);
        cardList.setAdapter(mCardsAdapter);

        ImageButton addButton = (ImageButton)rootView.findViewById(R.id.add_button);
        addButton.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), EditWeightActivity.class);
        intent.putExtra(EditWeightActivity.KEY_WEIGHT_OPERATION, EditWeightActivity.VALUE_ADD_WEIGHT);
        startActivityForResult(intent, 0);
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
        }
    }

    private CardAdapter mCardsAdapter;
}
