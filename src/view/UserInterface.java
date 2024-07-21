package view;

import game.GameManager;

import java.io.IOException;
import java.util.stream.Collectors;

public class UserInterface {

    public void printLarc() {
        System.out.println("██████████████████████████████████████████████████████");
        System.out.println("███─███────█────█────████────█─███───█───█─██─█───████");
        System.out.println("███─███─██─█─██─█─██─████─██─█─████─██─███──█─██─█████");
        System.out.println("███─███────█────█─███████─████─████─██───█─█──██─█████");
        System.out.println("███─███─██─█─█─██─██─████─██─█─████─██─███─██─██─█████");
        System.out.println("███───█─██─█─█─██────████────█───█───█───█─██─██─█████");
        System.out.println("██████████████████████████████████████████████████████");
    }

    public void printCards(GameManager gm) {
        System.out.println("----------------------- CARDS ------------------------");
        System.out.println(gm.getCards().stream()
                .map(c -> "|   " + c.getNumber() + " - " + c.getSymbol() + " - " + c.getSuit())
                .collect(Collectors.joining("\n")));
        System.out.println("------------------------------------------------------");
        System.out.println("| -------------- TOTAL: " + gm.getTotalSum());
        System.out.println("| -------------- GANHOU: " + (gm.is21() ? "SIM" : "NAO"));
        System.out.println("------------------------------------------------------");
    }

    public void changeColor(ConsoleColor color) {
        System.out.println(color.getColor());
    }

    public void cleanConsole() {
        //Limpa a tela no windows, no linux e no MacOS
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                Runtime.getRuntime().exec("clear");
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}
