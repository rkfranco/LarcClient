package view;

public enum ConsoleColor {
    BLACK(0, "\u001B[30m"),
    RED(1, "\u001B[31m"),
    GREEN(2, "\u001B[32m"),
    YELLOW(3, "\u001B[33m"),
    BLUE(4, "\u001B[34m"),
    PURPLE(5, "\u001B[35m"),
    CYAN(6, "\u001B[36m"),
    WHITE(7, "\u001B[37m");

    private final int code;
    private final String color;

    ConsoleColor(int code, String color) {
        this.code = code;
        this.color = color;
    }

    public String getColor() {
        return color;
    }
}
