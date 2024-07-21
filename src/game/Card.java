package game;

public class Card {
    private final int number;
    private final Suit suit;

    public Card(String number, String suit) {
        this.number = Integer.parseInt(number);
        this.suit = Suit.fromString(suit);
    }

    public int getNumber() {
        return number;
    }

    // public void setNumber(int number) {
    //     this.number = number;
    // }

    public Suit getSuit() {
        return suit;
    }

    public String getSymbol() {
        return suit.getSymbol();
    }

    // public void setSuit(String suit) {
    //     this.suit = game.Suit.fromString(suit);
    // }
}
