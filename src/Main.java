import clients.ClientTCP;
import clients.ClientUDP;
import mensager.Chat;
import mensager.Message;
import mensager.MessageManager;
import mensager.User;
import view.ConsoleColor;
import view.UserInterface;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.changeColor(ConsoleColor.YELLOW);
        ui.printLarc();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("----------------------- LOGIN ------------------------");
            System.out.print("user: ");
            int userId = sc.nextInt();
            System.out.print("password: ");
            String userPassword = sc.next();

            ClientUDP udp = new ClientUDP(userId, userPassword);
            ClientTCP tcp = new ClientTCP(userId, userPassword);

            if (tcp.initConnection()) {
                while (true) {
                    ui.cleanConsole();
                    ui.printLarc();
                    ui.printMenu();

                    int function = sc.nextInt();

                    if (function == 0) break;

                    if (function == 1) {
                        MessageManager msgManager = new MessageManager(tcp);


                        int userIndex = 0;

                        do {
                            List<User> users = msgManager.getUsers();

                            ui.printUsers(users);
                            userIndex = sc.nextInt();

                            if (userIndex == 0) continue;

                            User selectedUser = users.get(userIndex - 1);

                            Chat chat = msgManager.getChatByUserId(selectedUser.id())
                                    .orElse(msgManager.addChat(new Chat(selectedUser)));

                            String msg;

                            do {
                                ui.cleanConsole();
                                ui.printChat(chat);
                                ui.printMessageHeader();

                                msg = sc.next();

                                if (msg.equals("9") || msg.equals("0")) continue;

                                chat.addMessage(new Message(tcp.getLoggedUser(), msg));
                                udp.sendMessage(selectedUser.id(), msg);

                                msgManager.updateMessages();

                            } while (msg.charAt(0) != '0');

                        } while (userIndex != 0);

                    } else {
                        System.out.println("Ainda não implementado!");
                    }
                }
            }
            tcp.closeConnection();
        }
    }
}