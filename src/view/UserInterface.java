package view;

import game.GameManager;
import mensager.Chat;
import mensager.User;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    public void printMenu() {
        System.out.println("----------------------- MENU -------------------------");
        System.out.println("| 0 - Sair da aplicação ");
        System.out.println("| 1 - Mensagens ");
        System.out.println("| 2 - BlackJack (21) Ainda não implementado!");
        System.out.println("|-----------------------------------------------------");
        System.out.print("| Selecionar opção: ");
    }

    public void printUsers(List<User> users) {
        System.out.println("----------------- USUARIOS ONLINE --------------------");

        System.out.println(IntStream.range(1, users.size())
                .mapToObj(i -> "| " + i + " - " + users.get(i).name())
                .collect(Collectors.joining("\n")));

        System.out.println("| Digite 0 para voltar");
        System.out.println("|-----------------------------------------------------");
        System.out.print("| Selecionar usuario: ");
    }

    public void printChat(Chat chat) {
        System.out.println("----------------------- CHAT -------------------------");
        chat.getMessages().forEach(m -> System.out.println(m.sender().name() + ": " + m.msg()));
    }

    public void printMessageHeader() {
        System.out.println("------------------------------------------------------");
        System.out.println("| DIGITE A MENSAGEM (0 para sair, 9 para atualizar)  |");
        System.out.println("------------------------------------------------------");
        System.out.print("Enviar: ");
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
