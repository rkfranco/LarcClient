package clients;

import game.GameMsg;

public class ProtocolUtils {
    private static final String LINE_END = "\r\n";

    public static String getUsers(int userId, String password) {
        return "GET USERS " + userId + ":" + password + LINE_END;
    }

    public static String getMessage(int userId, String password) {
        return "GET MESSAGE " + userId + ":" + password + LINE_END;
    }

    public static String sendMessage(int userIdOne, String passwordOne, int userIdTwo, String msg) {
        return "SEND MESSAGE " + userIdOne + ":" + passwordOne + ":" + userIdTwo + ":" + msg + LINE_END;
    }

    public static String getPlayers(int userId, String password) {
        return "GET PLAYERS " + userId + ":" + password + LINE_END;
    }

    public static String getCard(int userId, String password) {
        return "“GET CARD " + userId + ":" + password + LINE_END;
    }

    public static String sendGame(int userId, String password, GameMsg msg) {
        return "GET GAME " + userId + ":" + password + ":" + msg + LINE_END;
    }
}
