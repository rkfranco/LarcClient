package mensager;

import clients.ClientTCP;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MessageManager {
    private List<Chat> chats;
    private List<User> users;
    private final ClientTCP tcp;

    public MessageManager(ClientTCP tcp) {
        this.tcp = tcp;
    }

    public void updateMessages() {
        while (true) {
            String msgReceive = tcp.getMessage();
            if (msgReceive.equals(":")) break;
            Message msg = Message.fromRequisition(msgReceive, users);
            chats.stream().filter(c -> c.getUser().id() == msg.sender().id()).forEach(c -> c.addMessage(msg));
        }
    }

    public List<Chat> getChats() {
        return this.chats;
    }

    public Chat addChat(Chat chat) {
        this.chats.add(chat);
        return chat;
    }

    public Optional<Chat> getChatByUserId(int id) {
        return this.chats.stream().filter(c -> c.getUser().id() == id).findAny();
    }

    public List<User> getUsers() {
        updateUsers();

        users.stream()
                .filter(u -> chats.stream().noneMatch(c -> c.getUser().id() == u.id()))
                .forEach(u -> chats.add(new Chat(u)));

        return users;
    }

    public void updateUsers() {
        this.users = new ArrayList<>();
        String[] data = tcp.getUsers().split(":");
        for (int i = 0; i < data.length; i += 3) {
            this.users.add(User.fromRequisition(String.join(":", data[i], data[1 + i], data[2 + i])));
        }
    }
}
