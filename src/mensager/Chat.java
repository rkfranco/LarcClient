package mensager;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Chat {
    private final List<Message> messages;
    private final User user;

    public Chat(User user) {
        this.messages = new ArrayList<>();
        this.user = user;
    }

    public List<Message> getMessages() {
        return this.messages;
    }

    public void addMessage(Message msg) {
        Optional.ofNullable(msg).ifPresent(this.messages::add);
    }

    public User getUser() {
        return user;
    }
}
