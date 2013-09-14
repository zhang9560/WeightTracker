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

public class WeightListFragment extends Fragment implements View.OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardsAdapter = new CardAdapter<Card>(getActivity());
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
            long date = data.getLongExtra(WeightDBHelper.KEY_DATE, 0);
            int weight = data.getIntExtra(WeightDBHelper.KEY_WEIGHT, 0);
        }
    }

    private CardAdapter mCardsAdapter;
}
