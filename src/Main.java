import game.Card;
import game.GameManager;
import game.Suit;
import view.ConsoleColor;
import view.UserInterface;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        ui.changeColor(ConsoleColor.CYAN);
        ui.printLarc();

        try (Scanner sc = new Scanner(System.in)) {
            System.out.println("----------------------- LOGIN ------------------------");
            System.out.print("user: ");
            int userId = sc.nextInt();
            System.out.print("password: ");
            String userPassword = sc.next();


            // clients.ClientUDP udp = new clients.ClientUDP(userId, userPassword);
            // clients.ClientTCP tcp = new clients.ClientTCP(userId, userPassword);
            // tcp.initConnection();

            // ui.cleanConsole();
            // ui.printLarc();

            System.out.println("----------------------- MENU -------------------------");
            System.out.println("| 1. Mensagens ");
            System.out.println("| 2. BlackJack (21) ");
            System.out.println("------------------------------------------------------");
            System.out.print("Selecionar número: ");
            int function = sc.nextInt();

            if (function == 1) {
                System.out.println("TESTE");
            } else {
                GameManager gm = new GameManager();
                List<Card> cards = List.of(
                        new Card("1", Suit.CLUB.name()),
                        new Card("7", Suit.DIAMOND.name()),
                        new Card("9", Suit.HEART.name())
                );

                cards.forEach(gm::addCard);

                ui.printCards(gm);

            }


        }
        //clientTCP.closeConnection();
    }
}