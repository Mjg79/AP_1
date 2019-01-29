package Model.Profile;

public class Profile {
    private String name;
    private String username;
    private String IP;
    private int numberOftransaction;
    private int numberOfGames;



    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public void setNumberOftransaction(int numberOftransaction) {
        this.numberOftransaction = numberOftransaction;
    }

    public void setNumberOfGames(int numberOfGames) {
        this.numberOfGames = numberOfGames;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getIP() {
        return IP;
    }

    public int getNumberOftransaction() {
        return numberOftransaction;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }
}
