package Model.Data;

import Controller.Profile;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DataReader extends Thread{
    private Socket socket;
    private String input = "";
    private Profile profile;

    public DataReader(Socket socket, Profile profile) {
        this.socket = socket;
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    @Override
    public void run() {
        try {
            Scanner scanner = new Scanner(socket.getInputStream());
            while (true) {
                input = scanner.nextLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getInput() {
        return input;
    }

    public Socket getSocket() {
        return socket;
    }
}
