package Model.Data;

import java.io.IOException;
import java.net.Socket;
import java.util.Formatter;

public class DataWriter extends Thread {
    private Socket socket;
    private String sentence;
    public DataWriter (Socket socket) {
        this.socket = socket;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public void run() {
        try {
            Formatter formatter = new Formatter(socket.getOutputStream());
            while (true) {
                if (!sentence.equals("")) {
                    formatter.format(sentence + "\n");
                    formatter.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
