package Model.Data;

import Controller.Profile;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;

public class DataWriter extends Thread {
    private Socket socket;
    private String sentence;
    private Profile profile;
    public DataWriter (Socket socket, Profile profile) {
        this.socket = socket;
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public void run() {
        try {
            Formatter formatter = new Formatter(socket.getOutputStream());
            while (true) {
                    formatter.format(sentence + "\n");
                    formatter.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
