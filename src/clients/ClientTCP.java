package clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientTCP {
    private static final String SITE_IP = "201.54.201.18";
    private static final int TCP_PORT = 1012;
    private final int userId;
    private final String userPassword;

    private Socket socket;
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private Thread keepAlive;

    public ClientTCP(int userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;
    }

    public String getUsers() {
        return sendRequest(ProtocolUtils.getUsers(userId, userPassword));
    }

    public String getMessage() {
        return sendRequest(ProtocolUtils.getMessage(userId, userPassword));
    }

    public String getPlayers() {
        return sendRequest(ProtocolUtils.getPlayers(userId, userPassword));
    }

    public String getCard() {
        return sendRequest(ProtocolUtils.getCard(userId, userPassword));
    }

    public String sendRequest(String request) {
        try {
            dataOut.write(request.getBytes(StandardCharsets.UTF_8));
            return dataIn.readLine();
        } catch (IOException e) {
            System.out.println("Erro ao enviar requisição: ".concat(e.getMessage()));
        }
        return null;
    }

    public void initConnection() {
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress(SITE_IP, TCP_PORT), 1000);
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            beginKeepAlive();
        } catch (IOException e) {
            System.out.println("Erro ao iniciar conexão TCP");
        }
    }

    public void closeConnection() {
        try {
            keepAlive.interrupt();
            dataIn.close();
            dataOut.close();
            socket.close();
        } catch (IOException e) {
            System.out.println("Erro ao finalizar conexão TCP");
        }
    }

    private void beginKeepAlive() {
        String request = ProtocolUtils.getUsers(userId, userPassword);

        keepAlive = new Thread(() -> {
            while (true) {
                try {
                    String serverMessage = sendRequest(request);
                    System.out.println(serverMessage);
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        keepAlive.start();
    }
}
