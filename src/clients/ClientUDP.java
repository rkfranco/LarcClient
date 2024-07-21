package clients;

import game.GameMsg;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class ClientUDP {
    private static final String SITE_IP = "201.54.201.18";
    private static final int UDP_PORT = 1011;
    private final int userId;
    private final String userPassword;
    private DatagramSocket socket;

    public ClientUDP(int userId, String userPassword) {
        this.userId = userId;
        this.userPassword = userPassword;

        try {
            socket = new DatagramSocket();
            socket.setSoTimeout(1000);
        } catch (IOException e) {
            System.out.println("Erro ao iniciar conexão UDP");
        }
    }

    public void sendMessage(int userIdDest, String msg) {
        sendPacket(ProtocolUtils.sendMessage(userId, userPassword, userIdDest, msg));
    }

    public void sendGame(GameMsg msg) {
        sendPacket(ProtocolUtils.sendGame(userId, userPassword, msg));
    }


    private void sendPacket(String dataString) {
        try {
            byte[] data = dataString.getBytes(StandardCharsets.UTF_8);
            DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByName(SITE_IP), UDP_PORT);
            socket.send(packet);
        } catch (IOException e) {
            System.out.println("Erro ao enviar datagrama UDP");
        }
    }
}
