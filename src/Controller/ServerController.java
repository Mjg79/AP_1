package Controller;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

public class ServerController extends Controller {
    private ServerSocket server;
    private final static String ip = "localhost";
    private HashMap<Profile, Socket> clients = new HashMap<>();
    private ArrayList<Profile> profiles  = new ArrayList<>();
    private Profile profile;
    private boolean stop = false;

    public ServerController() throws IOException {
    }


    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public String getIp() {
        return ip;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }


    public void joinToServer() throws IOException, ClassNotFoundException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
                    System.out.println("server waited...");
                    Socket client = null;
                    try {
                        client = server.accept();
                        System.out.println("connected...");
                        ObjectInputStream objectReader = new ObjectInputStream(client.getInputStream());
                        Profile profile = (Profile) objectReader.readObject();
                        profiles.add(profile);
                        clients.put(profile, client);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }


                }
            }
        });
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
    }


    public HashMap<Profile, Socket> getClients() {
        return clients;
    }

    public ArrayList<Profile> getProfiles() {
        return profiles;
    }


    public void startGame() throws IOException {

        for (Profile profile: getClients().keySet()) {
            Formatter formatter = new Formatter(getClients().get(profile).getOutputStream());
            formatter.format("true\n");
            formatter.flush();
        }
    }
}
