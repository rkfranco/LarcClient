package mensager;

public record User(int id, String name, int qdtWins) {
    public static User fromRequisition(String requisition) {
        String[] data = requisition.split(":");

        if (data.length != 3) {
            throw new IllegalArgumentException();
        }

        int id = Integer.parseInt(data[0]);
        int qdtWins = Integer.parseInt(data[2]);
        String name = data[1];

        return new User(id, name, qdtWins);
    }
}
