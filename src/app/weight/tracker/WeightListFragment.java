package app.weight.tracker;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.afollestad.cardsui.Card;
import com.afollestad.cardsui.CardAdapter;
import com.afollestad.cardsui.CardHeader;
import com.afollestad.cardsui.CardListView;

public class WeightListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        CardListView cardList = (CardListView)inflater.inflate(R.layout.weight_list, container, false);
        CardAdapter<Card> cardsAdapter = new CardAdapter<Card>(getActivity());
        cardList.setAdapter(cardsAdapter);
        cardsAdapter.add(new CardHeader("Week Days"));
        cardsAdapter.add(new Card("Hello", "World"));
        return cardList;
    }
}
