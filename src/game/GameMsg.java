package game;

public enum GameMsg {
    ENTER(0), //  solicita a participação no Jogo de Carta 21
    STOP(1), // avisa que não quer mais nenhuma carta
    QUIT(2); // solicita sair do Jogo de Carta 21

    private final int code;

    GameMsg(int code) {
        this.code = code;
    }
}
