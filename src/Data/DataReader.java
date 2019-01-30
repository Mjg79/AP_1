package Data;

import java.awt.*;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class DataReader extends Thread{
    private Socket socket;
    private String input;


    public DataReader(Socket socket) {
        this.socket = socket;
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
}
