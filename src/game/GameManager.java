package game;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

public class GameManager {
    List<Card> cards = new ArrayList<>();

    public void addCard(Card card) {
        if (isNull(card)) throw new NullPointerException();
        this.cards.add(card);
    }

    public List<Card> getCards() {
        return this.cards;
    }

    public boolean is21() {
        return getTotalSum() == 21;
    }

    public int getTotalSum() {
        return cards.stream()
                .map(Card::getNumber)
                .reduce(0, Integer::sum);
    }
}
