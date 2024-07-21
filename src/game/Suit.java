package game;

public enum Suit {
    CLUB(0, "\u2663"),
    HEART(1, "\u2665"),
    DIAMOND(2, "\u2666"),
    SPADE(3, "\u2660");

    private final int code;
    private final String symbol;

    Suit(int code, String symbol) {
        this.code = code;
        this.symbol = symbol;
    }

    public static Suit fromString(String suit) {
        return switch (suit) {
            case "CLUB" -> CLUB;
            case "HEART" -> HEART;
            case "DIAMOND" -> DIAMOND;
            case "SPADE" -> SPADE;
            default -> null;
        };
    }

    public String getSymbol() {
        return symbol;
    }
}
