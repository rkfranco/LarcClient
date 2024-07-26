package mensager;

import java.util.List;

public record Message(User sender, String msg) {
    public static Message fromRequisition(String requisition, List<User> users) {
        String[] data = requisition.split(":");

        if (data.length != 2) throw new IllegalArgumentException();

        int userId = Integer.parseInt(data[0]);
        User user = users.stream().filter(u -> u.id() == userId).findAny().orElse(new User(0, null, 0));

        return new Message(user, data[1]);
    }
}
